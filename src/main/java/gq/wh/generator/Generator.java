package gq.wh.generator;

import gq.wh.WBMulti;

import java.security.SecureRandom;

/**
 * Created by mr-guo on 2017/6/26.
 * set up tables and connect them with coding
 */
public class Generator {
    final static int BYTES = 8;
    final static int outbijecton = 4;
    final static int internalbijection = 16;
    final static int codingsize = 5;
    final static int[] type1num = new int[]{5,6,9,1,8,3,13,10};//两个一起来看,低位相加
    final static int[] type1output = new int[]{8,10,12,14};
    final static int[] type2num = new int[]{8,4,10,0,12,2,14,11};//两个一起来看，carry位和高位进位相加
    final static int[] type2output = new int[]{9,11,13,15};
    final static int[] highCode = new int[]{0,2,0,3,1,2,1,3};//4 bit multiply 4 bit high sequence
    final static int[] lowCode = new int[]{0,2,0,3,1,2,1,3};//4 bit multiply 4 bit low sequence
    private WBMulti multi ;
    private Bijection5x5[] initinput;
    private Bijection5x5[] multibijections;
    private SecureRandom random;

    //TODO

    //generate  bijections
    public void generatebijections(){
        //allocate
        initinput = new Bijection5x5[outbijecton];//对A,B,C,D进行双射
        multibijections = new Bijection5x5[internalbijection];//对16张表添加双射
        GenUtils utils = new GenUtils();
        //assign value
        for (int i = 0; i < outbijecton; i++) {
            utils.generate5x5bijection(initinput[i].getCoding(),initinput[i].getInvcoding(),codingsize,true,random);
        }
        for (int i = 0; i < internalbijection; i++) {
            utils.generate5x5bijection(multibijections[i].getCoding(),multibijections[i].getInvcoding(),codingsize,true,random);
        }
    }

    //encode input method
    public short encodeinputHL(Bijection5x5[] inputbijection ,int high , int low ,short tempcode){
        byte highbyte = (byte)((tempcode >> 5) & 0x0000001f);
        byte lowbyte = (byte)( tempcode & 0x0000001f);
        highbyte = inputbijection[high].getCoding()[highbyte];
        lowbyte = inputbijection[low].getCoding()[lowbyte];
        short encodedinput = (short)((highbyte << 5) & (lowbyte & 0x0000001f));
        return encodedinput;
    }
    //set up tables
    public void generate(){
        multi = new WBMulti();
        short encodedinput;
        short tempb = 0;
        byte result = 0;
        //生成编码
        generatebijections();
        //set up Partial Multiply HIGH tables the form is 0xxxx0yyyy
        for (int i = 0; i < multi.MULTIPLYHIGH; i++) {
            // store the temp byte
            for (int j = 0; j < 1024; j++) {
                //todo 万一两个输入不同输出相同，会对结果产生影响吗
                 encodedinput = encodeinputHL(initinput,highCode[2*i],highCode[2*i+1],(short)j);
                int mula = (j >> 5) & 0x0000000f;
                int mulb = j & 0x0000000f;
                int tempmul = mula * mulb;//multiply the first 4 bits with last 4 bits
                tempb = (short)((tempmul >> 4) & 0x0000000f);//the result is right but reserved in complement form
                // and then encode it accordingly
                result = multibijections[2*i].getCoding()[tempb];
                //todo 这里的值不一定赋值成功了
                multi.getWBmultiply()[i].setValue(result,encodedinput);
            }


        }

        //set up Partial Multiply LOW tables the form is 0xxxx0yyyy
        for (int i = 0; i < multi.MULTIPLYLOW; i++) {
            for (int j = 0; j < 1024; j++) {
                //do the decode correspondingly
                encodedinput = encodeinputHL(initinput,lowCode[2*i],lowCode[2*i+1],(short)j);
                int mula = (j >> 5) & 0x0000000f;
                int mulb = j & 0x0000000f;
                int tempmul = mula * mulb;//multiply the first 4 bits with last 4 bits
                tempb = (short)(tempmul & 0x0000000f);//the result is right but reserved in complement form
                //and then encode it accordingly
                result = multibijections[2*i+1].getCoding()[tempb];
                multi.getWBmultiply()[i+multi.MULTIPLYHIGH].setValue(result,encodedinput);
            }
        }

        //set up Partial Add tables the form is 0xxxx0yyyy 最精简的是8进5出
        for (int i = 0; i < multi.ADDTIMESTIMES; i++) {
            for (int j = 0; j < 1024; j++) {//多算了512位
                // do the decode correspondingly
                encodedinput = encodeinputHL(multibijections,type1num[2*i],type1num[2*i+1],(short)j);
                int adda = (j >> 5) & 0x0000000f;
                int addb = j & 0x0000000f;
                int tempadd = adda + addb;
                tempb = (short)(tempadd & 0x0000001f);
                result = multibijections[type1output[i]].getCoding()[tempb];
                multi.getWBmultiply()[i+multi.MULTIPLYHIGH + multi.MULTIPLYLOW].setValue(result,encodedinput);
            }
        }

        //set up carry add tables 形式为0xxxxCyyyy 最精简的应该是9进4出
        for (int i = 0; i < multi.CARRYADDTIMES; i++) {
            for (int j = 0; j < 1024; j++) {
                // do the decode correspondingly
                encodedinput = encodeinputHL(multibijections,type2num[2*i],type2num[2*i+1],(short)j);
                int carrybit = (j >> 4) & 0x00000001;
                int addend = (j >> 5) & 0x0000000f;
                int tempadd = addend + carrybit;
                tempb = (byte)(tempadd & 0x0000001f);
                result = multibijections[type2output[i]].getCoding()[tempb];
                multi.getWBmultiply()[i+multi.MULTIPLYHIGH + multi.MULTIPLYLOW+multi.ADDTIMESTIMES].setValue(result,encodedinput);
            }
        }
    }
}
