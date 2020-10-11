package hr.fer.zemris.java.hw01;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Mateo Martinjak
 * @version 1.0
 */
public class FactorialTest {

	@Test
	public void getFactorialNonZero() {
		Assert.assertEquals(120, Factorial.getFactorial((short)5));
	}
	
	@Test
	public void getFactorialZero() {
		Assert.assertEquals(1, Factorial.getFactorial((short)0));
	}
	
	@Test
	public void getFactorialLimit() {
		long expected=2432902008176640000l;
		Assert.assertEquals(expected, Factorial.getFactorial((short)20));
	}

	
}
