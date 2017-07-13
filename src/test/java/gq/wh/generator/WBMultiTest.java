package gq.wh.generator;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;

/** 
* WBMulti Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 1, 2017</pre> 
* @version 1.0 
*/ 
public class WBMultiTest { 

@Before
public void before() throws Exception {
    System.out.println("before test ");
} 

@After
public void after() throws Exception {
    System.out.println("after test");
} 

/** 
* 
* Method: getWBmultiply() 
* 
*/ 


/** 
* 
* Method: wbMultiply(byte A, byte B, byte C, byte D) 
* 
*/ 
@Test
public void testWbMultiply() throws Exception { 
//TODO: Test goes here...
    Bijection5x5[] inputinit;
    Bijection5x5[] multiBijections;

    //generate tables
    Generator genMUL = new Generator();
    genMUL.generatebijections();
    genMUL.generate();
    inputinit = genMUL.getInitinput();
    multiBijections = genMUL.getMultibijections();

    //
    byte A,B,C,D;
    byte encodedA,encodedB,encodedC,encodedD;
    byte WBA,WBB,WBC,WBD;
    byte[] resultWB = new byte[4];
    short mulresult,WBresult;
    for (int i = 0; i <256; i++) {
        //ȡ��A,B
        A = (byte)((i >> 4)&0x0000000f);
        encodedA = inputinit[0].getCoding()[A];
        B = (byte)(i & 0x0000000f);
        encodedB = inputinit[1].getCoding()[B];
        for (int j = 0; j < 256; j++) {
            //ȡ��C,D
            C = (byte)((j >> 4)&0x0000000f);
            encodedC = inputinit[2].getCoding()[C];
            D = (byte)(j & 0x0000000f);
            encodedD = inputinit[3].getCoding()[D];
            //�Ƚϰ׺л�ǰ�Ͱ׺л���i*j�Ľ��
            resultWB = genMUL.getMulti().wbMultiply(encodedA,encodedB,encodedC,encodedD);
            //ȡ���������룬�Ƚ�
            WBA = (byte)(multiBijections[15].getInvcoding()[resultWB[0]] & 0x0f);
            WBB = (byte)(multiBijections[14].getInvcoding()[resultWB[1]]& 0x0f);
            WBC = (byte)(multiBijections[12].getInvcoding()[resultWB[2]]& 0x0f);
            WBD = (byte)(multiBijections[7].getInvcoding()[resultWB[3]]& 0x0f);
            mulresult =(short)( i*j);
            WBresult = (short)((WBA << 12)|(WBB << 8)|(WBC << 4)|WBD);
            System.out.println("mulresult :" + mulresult + " WBresult : "+WBresult);
            assertEquals(mulresult,WBresult);
        }
    }


} 


} 
