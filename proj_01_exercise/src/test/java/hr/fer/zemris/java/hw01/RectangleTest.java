package hr.fer.zemris.java.hw01;

import org.junit.Assert;
import org.junit.Test;


public class RectangleTest {
	
	@Test
	public void getPerimeterNonZero() {
		
		float expected=66.06f;
		Assert.assertEquals(expected,Rectangle.getPerimeter(2.48f,30.55f), 1E-6);
	}
	
	@Test
	public void getPerimeterZero() {
		float expected=61.1f;
		Assert.assertEquals(expected,Rectangle.getPerimeter(0,30.55f), 1E-6);
	}
}
