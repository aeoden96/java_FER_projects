package hr.fer.zemris.java.gui.prim;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hr.fer.zemris.java.gui.prim.PrimDemo.PrimListModel;

public class PrimDemoTest {
	@Test
	public void modelTest() {
		PrimListModel<Integer> model = new PrimDemo.PrimListModel<>();
		
		
		assertEquals(2.0, model.next(), 1E-10); 
		assertEquals(3.0, model.next(), 1E-10); 
		assertEquals(5.0, model.next(), 1E-10); 
		assertEquals(7.0, model.next(), 1E-10); 
		
		
	}
}
