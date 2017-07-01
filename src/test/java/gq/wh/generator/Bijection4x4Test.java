package gq.wh.generator;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.*;

import java.util.Random;

/** 
* Bijection4x4 Tester. 
* 
* @author <Authors name> 
* @since <pre>ÁùÔÂ 26, 2017</pre> 
* @version 1.0 
*/ 
public class Bijection4x4Test { 

@Before
public void before() throws Exception { 
}
@After
public void after() throws Exception { 
}
@Test
public void testEqual(){

    byte[] testA = new byte[]{1,2,3,4};
    byte[] testB = new byte[]{5,6,7,8};
    testB = testA;
    for (int i = 0; i < testB.length; i++) {
        System.out.println(testB[i]);
    }
    assertEquals(1,17%16);
    assertTrue(true);
}
} 
