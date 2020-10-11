package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.Assert.assertEquals;


import org.junit.Test;




public class ValueWrapperTest {
	@Test(expected = RuntimeException.class)
	public void testForNull() {

		ValueWrapper test=new ValueWrapper(null);

		assertEquals(null, test.getValue());
		
		test.add(null);
		
		assertEquals(0, test.getValue());
		
		test.multiply(null);
		
		assertEquals(0, test.getValue());
		
		test.subtract(null);
		
		assertEquals(0, test.getValue());
		
		test.divide(null); //Runtime Exception expected
		
	}
	
	@Test
	public void testForInteger() {

		ValueWrapper test=new ValueWrapper(5);

		assertEquals(5, test.getValue());
		
		test.add(5);
		
		assertEquals(10, test.getValue());
		
		test.divide(2);
		
		assertEquals(5, test.getValue());
		
	}
	
	@Test
	public void testForDouble() {

		ValueWrapper test=new ValueWrapper(5.14);

		assertEquals(5.14, test.getValue());
		
		test.add(5);
		
		assertEquals(10.14, test.getValue());
		
		test.divide(2);
		
		assertEquals(5.07, test.getValue());
		
	
		
	}
	
	@Test(expected = RuntimeException.class)
	public void testForIncompatibleOperation() {

		ValueWrapper test=new ValueWrapper("Marko");

		assertEquals("Marko", test.getValue());
		
		test.add(5);
	}
	
	@Test(expected = RuntimeException.class)
	public void testForIncompatibleOperation2() {

		ValueWrapper test=new ValueWrapper(3);

		assertEquals(3, test.getValue());
		
		test.add("Marko");
	}
	
	
	@Test
	public void testForIntegerParse() {

		ValueWrapper test=new ValueWrapper("3");

		assertEquals("3", test.getValue());
		
		test.add(8);
		
		
		assertEquals(11, test.getValue());
		
		test.add("4");
		
		assertEquals(15, test.getValue());
		
		
	}
	
	
	@Test
	public void testForDoubleParse() {

		ValueWrapper test=new ValueWrapper("3.15");

		assertEquals("3.15", test.getValue());
		
		test.add(8);
		
		assertEquals(11.15, test.getValue());
		
		test.add("4");
		
		assertEquals(15.15, test.getValue());
		
	}
	
	@Test
	public void testCompare() {

		ValueWrapper test=new ValueWrapper("3.15");
		
		assertEquals(0,test.numCompare("3.15"));
		assertEquals(0,test.numCompare(3.15));
		assertEquals(0,test.numCompare("0.315E1"));
		
		assertEquals(-1,test.numCompare(8));
	}
	
	@Test
	public void testCompareForNull() {

		ValueWrapper test=new ValueWrapper(null);
		
		assertEquals(0,test.numCompare(null));
		
		assertEquals(-1,test.numCompare(8));
		
		assertEquals(1,test.numCompare("-1.4"));
		
		assertEquals(1,test.numCompare("-1"));
		
		
		
	}
	
	@Test
	public void testCompareForNull2() {

		ValueWrapper test=new ValueWrapper("2.14");
		
		assertEquals(1,test.numCompare(null));
		
		assertEquals(1,test.numCompare(0));
		
		assertEquals(1,test.numCompare("-1.4"));
		
		
		
	}
	
	
}
