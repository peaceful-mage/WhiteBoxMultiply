package gq.wh;


/**
 * Created by mr-guo on 2017/6/26.
 */
public class WBMulti {
    public final static int ADDTIMESTIMES = 4;
    public final static int MULTIPLYHIGH = 4;
    public final static int MULTIPLYLOW =4;
    public final static int CARRYADDTIMES = 4;
    public static int TABLES = ADDTIMESTIMES + CARRYADDTIMES + MULTIPLYHIGH + MULTIPLYLOW;
    protected Box10to5[] WBmultiply; //abb 4 bit with 4 bit ,one bit for carry

    public WBMulti(){
        WBmultiply = new Box10to5[TABLES];
        for (int i = 0; i < TABLES; i++) {
            WBmultiply[i] = new Box10to5();
        }
    }

    public Box10to5[] getWBmultiply() {
        return WBmultiply;
    }

    public void setWBmultiply(Box10to5[] WBmultiply) {
        this.WBmultiply = WBmultiply;
    }

    /**
     *combine two byte to HL structure
     * @param X
     * @param Y
     * @return xy
     */
    public short combine (byte X,byte Y){
        return (short)((X << 5)| (Y & 0x1f));
    }

    /**
     * A,B,C,D are all have been decoded
     * @param A
     * @param B
     * @param C
     * @param D
     */
    public byte[] wbMultiply(byte A , byte B, byte C, byte D){
        byte[] WBtables = new byte[16];//һ��Ҫ����16������һ����16���м�Ľ��
        short[] inputOrder ;
        byte[] type1Order = new byte[]{6,5,1,9,3,8,10,13};
        byte[] type2Order = new byte[]{4,8,0,10,2,12,11,14};
        byte[] WBresult = new byte[4];
        //combine the input
        short AC = combine(A, C);
        short AD = combine(A, D);
        short BC = combine(B, C);
        short BD = combine(B, D);
        inputOrder = new short[]{AC, AD, BC, BD};
        //search for tables with order
        //and save the first 8 bits into the WBtables
        for (int i = 0; i < 4; i++) {
            WBtables[2*i]= WBmultiply[i].getOutputData()[inputOrder[i]];
            WBtables[2*i + 1]= WBmultiply[i + 4].getOutputData()[inputOrder[i]];
        }

        //use the temp result and save the latter 8 bits into the WBtables
        //��type1��type2�ı���н�����
        for (int i = 0; i < 4; i++) {
            WBtables[8 + 2 * i] = WBmultiply[8 + i].getOutputData()[combine(WBtables[type1Order[2 * i]],WBtables[type1Order[2 * i + 1]] )];//WBmultiply[8+i]ָ���ǲ��ҵ�type1�ı�
            WBtables[8 + 2*i + 1] = WBmultiply[12 + i].getOutputData()[combine(WBtables[type2Order[2*i]],WBtables[type2Order[2*i + 1]])];//WBmultiply[12+i]ָ���ǲ��ҵ�type2�ı�
        }

        WBresult[0]=WBtables[15];//�׺л����A
        WBresult[1]=WBtables[14];//�׺л����B
        WBresult[2]=WBtables[12];//�׺л����C
        WBresult[3]=WBtables[7];//�׺л����D
        return  WBresult;
    }
}
