package hr.fer.zemris.math;

import static org.junit.Assert.assertEquals;


import org.junit.Test;


public class Vector3Test {
	
	@Test
	public void toArray() {

		Vector3 a=new Vector3(2,5,8);
		
		double[] b= new double[] {2,5,8};
		
		assertEquals(b[0], a.toArray()[0],1E-6);
		assertEquals(b[1], a.toArray()[1],1E-6);
		assertEquals(b[2], a.toArray()[2],1E-6);
	
	}
	
	@Test
	public void getters() {

		Vector3 a=new Vector3(2,5,8);
		
		
		
		assertEquals(2, a.getX(),1E-6);
		assertEquals(5, a.getY(),1E-6);
		assertEquals(8, a.getZ(),1E-6);
	
	}
	
	
	@Test
	public void angle() {

		Vector3 a=new Vector3(1,2,3);
		Vector3 b=new Vector3(1,1,1);
		
		double angle=a.cosAngle(b);
		
		assertEquals(0.92582, angle,1E-6);
		
	
	}
	
	
	@Test
	public void scale() {

		Vector3 a=new Vector3(1,2,3);
		
		
		Vector3 scaled=a.scale(4);
		
		assertEquals(4, scaled.getX(),1E-6);
		assertEquals(8, scaled.getY(),1E-6);
		assertEquals(12, scaled.getZ(),1E-6);
		
	
	}
	
	@Test
	public void cross() {

		Vector3 a=new Vector3(1,0,0);
		Vector3 b=new Vector3(0,0,1);
		
		Vector3 c=a.cross(b);
		
		assertEquals(0, c.getX(),1E-6);
		assertEquals(-1, c.getY(),1E-6);
		assertEquals(0, c.getZ(),1E-6);
		
	
	}
	
	
	@Test
	public void dot() {

		Vector3 a=new Vector3(1,4,2);
		Vector3 b=new Vector3(1,2,3);
		
		double dotP=a.dot(b);
		
		assertEquals(15, dotP,1E-6);
	
	}
	@Test
	public void sub() {

		Vector3 a=new Vector3(1,4,2);
		Vector3 b=new Vector3(1,2,3);
		
		Vector3 s=a.sub(b);
		
		assertEquals(0, s.getX(),1E-6);
		assertEquals(2, s.getY(),1E-6);
		assertEquals(-1, s.getZ(),1E-6);
	
	}
	
	
	@Test
	public void add() {

		Vector3 a=new Vector3(1,4,2);
		Vector3 b=new Vector3(1,2,3);
		
		Vector3 s=a.add(b);
		
		assertEquals(2, s.getX(),1E-6);
		assertEquals(6, s.getY(),1E-6);
		assertEquals(5, s.getZ(),1E-6);
	
	}
	
	@Test
	public void normalized() {

		Vector3 a=new Vector3(1,4,2);
		
		
		Vector3 n=a.normalized();
		
		assertEquals(0.21821789, n.getX(),1E-6);
		assertEquals(0.87287156, n.getY(),1E-6);
		assertEquals(0.43643578, n.getZ(),1E-6);
	
	}
	
	@Test
	public void norm() {

		Vector3 a=new Vector3(1,4,2);
		
		assertEquals(4.5825757, a.norm(),1E-6);
		
	
	}


}
