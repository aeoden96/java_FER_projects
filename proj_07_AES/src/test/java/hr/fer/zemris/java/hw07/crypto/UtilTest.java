package hr.fer.zemris.java.hw07.crypto;

import static org.junit.Assert.assertEquals;


import org.junit.Test;


public class UtilTest {
	
	@Test
	public void test1() {

		String hex= "04052A360C";
		
		byte[] b = Util.hextobyte(hex);
		
		

		assertEquals(4, b[0]);
		assertEquals(5, b[1]);
		assertEquals(42, b[2]);
		assertEquals(54, b[3]);
		assertEquals(12, b[4]);
		
		String result = Util.bytetohex(b);
		
		
		assertEquals("04052A360C", result);

		
		
	}
	
	@Test
	public void test2() {

		String hex= "00005D3A0F";
		
		byte[] b = Util.hextobyte(hex);
		
		

		assertEquals(0, b[0]);
		assertEquals(0, b[1]);
		assertEquals(93, b[2]);
		assertEquals(58, b[3]);
		assertEquals(15, b[4]);
		
		String result = Util.bytetohex(b);
		
		
		assertEquals("00005D3A0F", result);

		
		
	}

}
