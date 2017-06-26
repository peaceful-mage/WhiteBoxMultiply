package gq.wh.generator;

import gq.wh.WBMulti;

/**
 * Created by mr-guo on 2017/6/26.
 * set up tables and connect them with coding
 */
public class Generator {
    final static int BYTES = 8;
    private WBMulti multi ;
    //TODO

    //set up tables
    public void generate(){
        multi = new WBMulti();
        //set up Partial Multiply tables
        for (int i = 0; i < multi.MULTIPLYTIMES; i++) {
            byte tempb;
            //TODO do the decode correspondingly
            for (int j = 0; j < (2^BYTES); j++) {
                int a = j >> 4;
                int b = j % 16;
                int tempmul = a*b;
//                tempb = tempmul %256;

            }
        }
    }
    

}
