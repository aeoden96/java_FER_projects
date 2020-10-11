package hr.fer.zemris.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 * 
 * @author Mateo
 *
 */
public class Vector2DTest {

	/**
	 * 
	 */
	@Test
	public void getX() {
		Vector2D a = new Vector2D(0,2);

		assertEquals(0, a.getX(),1E-6);
	}
	/**
	 * 
	 */
	@Test
	public void getY() {
		Vector2D a = new Vector2D(0,2);

		assertEquals(2, a.getY(),1E-6);
	}
	/**
	 * 
	 */
	@Test(expected = NullPointerException.class)
	public void translateNull() {
		Vector2D a = new Vector2D(0,2);

		a.translate(null);
	}
	/**
	 * 
	 */
	@Test(expected = NullPointerException.class)
	public void translatedNull() {
		Vector2D a = new Vector2D(0,2);

		a=a.translated(null);
	}
	/**
	 * 
	 */
	@Test
	public void translate() {
		Vector2D a = new Vector2D(0,2);
		Vector2D offset=new Vector2D(1,0);
		a.translate(offset);
		assertEquals(1,a.getX(),1E-6);
		assertEquals(2,a.getY(),1E-6);
	}
	/**
	 * 
	 */
	@Test
	public void translated() {
		Vector2D a = new Vector2D(0,2);
		Vector2D offset=new Vector2D(1,0);
		
		assertEquals(1,a.translated(offset).getX(),1E-6);
		assertEquals(2,a.translated(offset).getY(),1E-6);
	}
	/**
	 * 
	 */
	@Test
	public void rotated() {
		Vector2D a = new Vector2D(0,2);


		a.rotate(90);
		assertEquals(-2,a.getX(),1E-6);
		assertEquals(0,a.getY(),1E-6);
	}
	/**
	 * 
	 */
	@Test
	public void rotate() {
		Vector2D a = new Vector2D(0,2);
		
		
		assertEquals(2,a.rotated(-90).getX(),1E-6);
		assertEquals(0,a.rotated(-90).getY(),1E-6);
	}
	/**
	 * 
	 */
	@Test
	public void scale() {
		Vector2D a = new Vector2D(0,2);


		a.scale(2);
		assertEquals(0,a.getX(),1E-6);
		assertEquals(4,a.getY(),1E-6);
	}
	/**
	 * 
	 */
	@Test
	public void scaled() {
		Vector2D a = new Vector2D(0,2);
		
		
		assertEquals(0,a.scaled(2).getX(),1E-6);
		assertEquals(4,a.scaled(2).getY(),1E-6);
	}
	/**
	 * 
	 */
	@Test
	public void copy() {
		Vector2D a = new Vector2D(0,2);
		
		Vector2D b=a.copy();
		assertEquals(a.getX(),b.getX(),1E-6);
		assertEquals(a.getY(),b.getY(),1E-6);
	}


}
