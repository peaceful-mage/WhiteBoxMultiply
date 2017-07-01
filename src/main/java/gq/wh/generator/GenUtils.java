package gq.wh.generator;

import java.security.SecureRandom;

/**
 * Created by mr-guo on 2017/6/29.
 */
public class GenUtils {

    //Éú³É5to5coding
    public void generate5x5bijection(byte[] coding , byte[] invcoding ,int size,boolean init, SecureRandom secureRandom){
        if (init){
            for (int i = 0; i < coding.length; i++) {
                coding[i] = (byte)i;
            }
            for (int i = 0; i < invcoding.length; i++) {
                invcoding[i]= (byte)i;
            }
        }

        for (int i = 0; i < size; i++) {
            byte temp;
            int tempindex = secureRandom.nextInt(i+1);

            //change the value according to position
            temp = coding[i];
            coding[i] = coding[tempindex];
            coding[tempindex] = temp;

            //change the position according to the value
            temp = invcoding[coding[i]];
            invcoding[coding[i]] = invcoding[coding[tempindex]];
            invcoding[coding[tempindex]] = temp;

        }
    }
}
