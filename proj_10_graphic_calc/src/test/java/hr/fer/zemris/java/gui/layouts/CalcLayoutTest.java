package hr.fer.zemris.java.gui.layouts;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalcLayoutTest {

	@Test(expected=IllegalArgumentException.class)
	public void testExc1() {
		
		JPanel p = new JPanel(new CalcLayout(3));
		p.add(new JLabel("x"), new RCPosition(-1,1));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testExc2() {
		
		JPanel p = new JPanel(new CalcLayout(3));
		p.add(new JLabel("x"), new RCPosition(2,0));
	}
	
	
	@Test
	public void prefferred1() {
		
		JPanel p = new JPanel(new CalcLayout(2));
		JLabel l1 = new JLabel(""); l1.setPreferredSize(new Dimension(10,30));
		JLabel l2 = new JLabel(""); l2.setPreferredSize(new Dimension(20,15));
		p.add(l1, new RCPosition(2,2));
		p.add(l2, new RCPosition(3,3));
		
		
		assertEquals(152, p.getPreferredSize().getWidth(), 1E-6);
		assertEquals(158, p.getPreferredSize().getHeight(),1E-6);
	}
	
	
	@Test
	public void prefferred2() {
		
		JPanel p = new JPanel(new CalcLayout(2));
		JLabel l1 = new JLabel(""); l1.setPreferredSize(new Dimension(108,15));
		JLabel l2 = new JLabel(""); l2.setPreferredSize(new Dimension(16,30));
		p.add(l1, new RCPosition(0,0));
		p.add(l2, new RCPosition(3,3));
		
		
		assertEquals(152, p.getPreferredSize().getWidth(), 1E-6);
		assertEquals(158, p.getPreferredSize().getHeight(),1E-6);
	}
	
	
}
