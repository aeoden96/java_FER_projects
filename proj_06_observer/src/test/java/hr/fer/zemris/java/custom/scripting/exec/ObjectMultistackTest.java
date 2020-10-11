package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class ObjectMultistackTest {

	@Test
	public void testForNull() {

		
		ObjectMultistack multistack = new ObjectMultistack();
		multistack.push("a", null);
		
		assertEquals(null, multistack.peek("a"));
		
		ValueWrapper a = new ValueWrapper(Integer.valueOf(5));
		multistack.push(null, a);
		
		assertEquals(5, multistack.peek(null).getValue());
	
	}
	
	@Test
	public void testForIntegers() {

		
		ObjectMultistack multistack = new ObjectMultistack();
		ValueWrapper a=new ValueWrapper(Integer.valueOf(5));
		multistack.push("a", a);
		
		assertEquals(5, multistack.peek("a").getValue());
		
		ValueWrapper b = new ValueWrapper(Integer.valueOf(15));
		
		multistack.push("a", b);
		
		assertEquals(15, multistack.peek("a").getValue());
		
		multistack.peek("a").add(5);
		
		assertEquals(20, multistack.peek("a").getValue());
		
		multistack.pop("a");
		
		assertEquals(5, multistack.peek("a").getValue());
	}
	
	@Test
	public void testForEmpty() {

		
		ObjectMultistack multistack = new ObjectMultistack();
		ValueWrapper a=new ValueWrapper(Integer.valueOf(5));
		multistack.push("a", a);
		
		ValueWrapper b = new ValueWrapper(Integer.valueOf(15));
		
		multistack.push("a", b);
		
		multistack.peek("a").add(5);
		
		multistack.pop("a");
		
		multistack.pop("a");
		
		assertEquals(true, multistack.isEmpty("a"));
	}
	@Test
	public void testForMixed() {

		
		ObjectMultistack multistack = new ObjectMultistack();
		ValueWrapper a=new ValueWrapper("Marko");
		
		multistack.push("a", a);
		
		ValueWrapper b=new ValueWrapper("3.14");
		
		multistack.push("a", b);
		
		assertEquals("3.14", multistack.peek("a").getValue());
		
		multistack.peek("a").add(0.06);
		
		assertEquals(3.2, multistack.peek("a").getValue());
		
		multistack.pop("a");
		
		assertEquals("Marko", multistack.peek("a").getValue());
		
		
		
	}
	
	
}
