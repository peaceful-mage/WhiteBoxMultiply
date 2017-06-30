package gq.wh;

/**
 * Created by mr-guo on 2017/6/26.
 */
public class Box10to5 {
    private int inputindex = 1024;
    byte[] outputData;
    //以字节码的形式进行存储


    public Box10to5(){
        outputData = new byte[1024];
    }

    public byte[] getOutputData() {
        return outputData;
    }

    public void setOutputData(byte[] outputData) {
        this.outputData = outputData;
    }

    //assign the value for BOX
    //assign the value for BOX
    public void setValue(byte x , int index){
        outputData[index] = x;
    }
}
