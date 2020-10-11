package hr.fer.zemris.math;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;


public class ComplexTest {
	
	@Test
	public void parse() {

		Complex a = Complex.parse("2");
		assertEquals(2, a.getRe(),1E-6);
		assertEquals(0, a.getIm(),1E-6);
		
		Complex b = Complex.parse("0");
		assertEquals(0, b.getRe(),1E-6);
		assertEquals(0, b.getIm(),1E-6);
		
		Complex c = Complex.parse("-0");
		assertEquals(0, c.getRe(),1E-6);
		assertEquals(0, c.getIm(),1E-6);
		
		Complex d = Complex.parse("1i");
		assertEquals(0, d.getRe(),1E-6);
		assertEquals(1, d.getIm(),1E-6);
		
		Complex e = Complex.parse("i");
		assertEquals(0, e.getRe(),1E-6);
		assertEquals(1, e.getIm(),1E-6);
		
		Complex f = Complex.parse("-i");
		assertEquals(0, f.getRe(),1E-6);
		assertEquals(-1, f.getIm(),1E-6);
		
		Complex g = Complex.parse("2+2i");
		assertEquals(2, g.getRe(),1E-6);
		assertEquals(2, g.getIm(),1E-6);
		
		Complex h = Complex.parse("-2.0-0i");
		assertEquals(-2, h.getRe(),1E-6);
		assertEquals(0, h.getIm(),1E-6);
		
		Complex i = Complex.parse(" -2.256 + 0i ");
		assertEquals(-2.256, i.getRe(),1E-6);
		assertEquals(0, i.getIm(),1E-6);
		
	
	}
	
	
	@Test
	public void root() {

		Complex a = Complex.parse("2 + 2i");
		List<Complex> list=a.root(4);
		
		assertEquals(1.27192, list.get(0).getRe(),1E-3);
		assertEquals(0.253, list.get(0).getIm(),1E-3);
		
		assertEquals(-0.253, list.get(1).getRe(),1E-3);
		assertEquals(1.27192, list.get(1).getIm(),1E-3);
		
		assertEquals(-1.27192, list.get(2).getRe(),1E-3);
		assertEquals(-0.253, list.get(2).getIm(),1E-3);
		
		assertEquals(0.253, list.get(3).getRe(),1E-3);
		assertEquals(-1.27192, list.get(3).getIm(),1E-3);
		
	
	}
	
	@Test
	public void power() {

		Complex a = Complex.parse("2 + 2i");
		
		assertEquals(1, a.power(0).getRe(),1E-3);
		assertEquals(0, a.power(0).getIm(),1E-3);
		
		assertEquals(2, a.power(1).getRe(),1E-3);
		assertEquals(2, a.power(1).getIm(),1E-3);
		
		assertEquals(0, a.power(2).getRe(),1E-3);
		assertEquals(8, a.power(2).getIm(),1E-3);
		
		assertEquals(-16, a.power(3).getRe(),1E-3);
		assertEquals(16, a.power(3).getIm(),1E-3);
		
		
	
	}
	
	
	@Test
	public void negate() {

		Complex a = Complex.parse("2 + 2i");
		
		assertEquals(-2, a.negate().getRe(),1E-3);
		assertEquals(-2, a.negate().getIm(),1E-3);

	}
	
	@Test
	public void sub() {

		Complex a = Complex.parse("2 + 2i");
		Complex b = Complex.parse("1 + 3i");
		
		assertEquals(1, a.sub(b).getRe(),1E-3);
		assertEquals(-1, a.sub(b).getIm(),1E-3);

	}
	
	@Test
	public void add() {

		Complex a = Complex.parse("2 + 2i");
		Complex b = Complex.parse("1 + 3i");
		
		assertEquals(3, a.add(b).getRe(),1E-3);
		assertEquals(5, a.add(b).getIm(),1E-3);

	}
	
	@Test
	public void div() {

		Complex a = Complex.parse("2 + 2i");
		Complex b = Complex.parse("1 + 3i");
		
		assertEquals(0.8, a.divide(b).getRe(),1E-3);
		assertEquals(-0.4, a.divide(b).getIm(),1E-3);

	}
	
	
	@Test
	public void mul() {

		Complex a = Complex.parse("2 + 2i");
		Complex b = Complex.parse("1 + 3i");
		
		assertEquals(-4, a.multiply(b).getRe(),1E-3);
		assertEquals(8, a.multiply(b).getIm(),1E-3);

	}
	
	@Test
	public void module() {

		Complex a = Complex.parse("2 + 2i");
		
		assertEquals(2.8284, a.module(),1E-3);
		

	}
}