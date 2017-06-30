package gq.wh;


/**
 * Created by mr-guo on 2017/6/26.
 */
public class WBMulti {
    public final static int ADDTIMESTIMES = 4;
    public final static int MULTIPLYHIGH = 4;
    public final static int MULTIPLYLOW =4;
    public final static int CARRYADDTIMES = 4;
    protected Box10to5[] WBmultiply; //abb 4 bit with 4 bit ,one bit for carry

    public WBMulti(){
        WBmultiply = new Box10to5[ADDTIMESTIMES + CARRYADDTIMES + MULTIPLYHIGH + MULTIPLYLOW];
    }

    public Box10to5[] getWBmultiply() {
        return WBmultiply;
    }

    public void setWBmultiply(Box10to5[] WBmultiply) {
        this.WBmultiply = WBmultiply;
    }

    public void wbMultiply(){

    }
}
