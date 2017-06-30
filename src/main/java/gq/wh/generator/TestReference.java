package gq.wh.generator;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by mr-guo on 2017/6/29.
 */
public class TestReference {
    void foo(int num){
        num = 20;
        System.out.println("num is " + num);
    }

    void testRandom(){
        int[] type1num = new int[]{6,7,10,2,9,4,14,11};
        int[] type2num = new int[]{9,5,11,1,13,3,15,12};
        for (int i = 0; i < type1num.length; i++) {
            System.out.print(type1num[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < type2num.length; i++) {
            System.out.print(type2num[i] + " ");
        }
    }

    void bytechange(byte[] array){
        array[1] = 8;
        array[2] = 10;
    }
    public static void main(String[] args) {
        TestReference testre = new TestReference();
        testre.testRandom();
    }
}
