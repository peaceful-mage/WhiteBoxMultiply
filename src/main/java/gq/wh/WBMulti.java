package gq.wh;


/**
 * Created by mr-guo on 2017/6/26.
 */
public class WBMulti {
    final static int ADDTIMES = 4;
    final static int MULTIPLY = 4;
    Box10to5[] partialAdd; //abb 4 bit with 4 bit ,one bit for carry
    Box8to8[] partialMulti;//multiply by 4 * 4

    public WBMulti(){
        partialAdd = new Box10to5[ADDTIMES];
        partialMulti = new Box8to8[MULTIPLY];
    }
}
