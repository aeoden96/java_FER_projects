package hr.fer.zemris.java.hw02;

import org.junit.Assert;
import org.junit.Test;

public class ComplexNumberTest {



		@Test(expected=IllegalArgumentException.class)
		public void power() {
			ComplexNumber x=new ComplexNumber(1,3);
			x.power(-3);
		}
		
		@Test
		public void add() {
			ComplexNumber x=new ComplexNumber(1,3);
			ComplexNumber c=new ComplexNumber(7,3);
			
			ComplexNumber y=new ComplexNumber(8,6);
			Assert.assertEquals(y.toString(),x.add(c).toString() );
		}
		
		@Test 
		public void contains() {
			ComplexNumber c2 = ComplexNumber.parse("2.532+3i");
			
			Assert.assertEquals(2.532, c2.getReal(),1E-6);
		}
		

	

}
