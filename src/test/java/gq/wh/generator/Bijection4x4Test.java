package gq.wh.generator;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.*;
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
    for (int i = 0; i < 128; i++) {
        int a = i >> 4;
        int b = i % 16;
        int tempmul = a*b;
        System.out.println("a : "+ a + " b : "+ b + " tempmul :"+tempmul);
    }
    assertEquals(1,17%16);
    assertTrue(true);
}
} 
