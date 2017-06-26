package gq.wh;


/**
 * Created by mr-guo on 2017/6/26.
 */
public class WBMulti {
    public final static int ADDTIMESTIMES = 4;
    public final static int MULTIPLYTIMES = 4;
    Box10to5[] partialAdd; //abb 4 bit with 4 bit ,one bit for carry
    Box8to8[] partialMulti;//multiply by 4 * 4

    public WBMulti(){
        partialAdd = new Box10to5[ADDTIMESTIMES];
        partialMulti = new Box8to8[MULTIPLYTIMES];
    }
}
