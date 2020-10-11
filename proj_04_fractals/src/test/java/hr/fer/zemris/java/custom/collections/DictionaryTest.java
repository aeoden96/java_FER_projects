package hr.fer.zemris.java.custom.collections;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 * 
 * @author Mateo
 *
 */
public class DictionaryTest {
	/**
	 * 
	 */
	@Test
	public void testGetNull() {
		Dictionary a = new Dictionary();

		assertEquals(null, a.get("g"));
	}
	/**
	 * 
	 */
	@Test(expected = NullPointerException.class)
	public void testNullInput() {
		Dictionary a = new Dictionary();
		a.put(null, "newValue");
	}

	/**
	 * 
	 */
	@Test
	public void testValueGet() {
		Dictionary a = new Dictionary();
		a.put("Marko", 8);
		a.put("Petra", 3);
		a.put("Mirko", 0);
		
		assertEquals(0, a.get("Mirko"));

		
	}
	/**
	 * 
	 */
	@Test
	public void testRewrite() {
		Dictionary a = new Dictionary();
		a.put("Marko", 8);
		a.put("Petra", 3);
		a.put("Marko", 0);
		
		assertEquals(0, a.get("Marko"));

		
	}
	/**
	 * 
	 */
	@Test
	public void clear() {
		Dictionary a = new Dictionary();
		a.put("Marko", 8);
		a.put("Petra", 3);
		a.put("Marko", 0);
		a.clear();
		assertEquals(null, a.get("Marko"));

		
	}
	


}
