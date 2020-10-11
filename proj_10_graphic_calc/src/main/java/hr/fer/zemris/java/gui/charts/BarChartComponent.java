package hr.fer.zemris.java.gui.charts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

/**
 * Klasa izrađuje jedan graf pomoću Graphics objekta.
 * @author Mateo
 *
 */
public  class BarChartComponent extends JComponent {
	/**
	 * informacije o grafuu ,proslijeđen objekt kao argment konstuktora
	 */
	private  BarChart chart;
	/**
	 * Konstruktor prima BarChart objekt koji sadrži informacije o grafu.
	 * @param chart
	 */
	public BarChartComponent(BarChart chart) {
		super();
		this.chart = chart;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * Funkcija dobiva kao argument graphics objekt ,i nad njim zove funkcije za crtanje 
	 * koje iscrtavaju dio po dio grafa unutar komponente.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		int numOfHLines=chart.maxY/chart.razmak+1;
		int numOfVLines=chart.list.size()+1;
		int step=chart.razmak;
		Insets ins = getInsets();
		Dimension dim = getSize();
		
		Rectangle r = new Rectangle(
				ins.left+50, 
				ins.top, 
				dim.width-ins.left-ins.right-50,
				dim.height-ins.top-ins.bottom-50);
		
		if(!isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(r.x, r.y, r.width, r.height);
		}
		
		g.setFont(new Font("Calibri" , Font.BOLD,14));
		g.setColor(getForeground());
		
		
		//iscrtavanje horiz.linija i brojeva uz te linije
		for(int i=0;i<numOfHLines ;i++) {
			g.drawLine(r.x,r.y+i*r.height/(numOfHLines-1) , r.x+r.width , r.y+i*r.height/(numOfHLines-1));
			g.drawString(String.valueOf(numOfHLines*step-i*step-step), r.x-20, r.y+i*r.height/(numOfHLines-1)+10);
			
		}
		//iscrtavanje vert.linija
		for(int i=0;i<numOfVLines ;i++) {
			g.drawLine(r.x+i*r.width/(numOfVLines-1), r.y,r.x+i*r.width/(numOfVLines-1),r.y+r.height);
		}
		
		g.drawLine(r.x, r.y+r.height, r.x+r.width, r.y+r.height);
		
		
		
		
		//ispis teksta na x osi
		g.drawString(chart.opisX, r.x +r.width*2/5, r.y+r.height+ 40);
		
		//definiranje okomitog fonta
		AffineTransform af= new AffineTransform();
		af.rotate(Math.toRadians(-90),0,0);
		Font old= g.getFont();
		Font rotFont=old.deriveFont(af);
		g.setFont(rotFont);
		//ispis teksta na y osi
		g.drawString(chart.opisY, r.x -40 , r.y+r.height*3/5);
		g.setFont(old);
		
		int t=1;
		for(XYValue i: chart.list ) {
			//crtanje oznaka uz x komponente
			g.setColor(new Color(0, 0,0,255));
			g.drawString(String.valueOf(i.getX()), r.x+t*r.width/(numOfVLines-1)-r.width/(numOfVLines-1)/2, r.y+r.height+20);
			
			//crtanje plavih pravokutnika
			g.setColor(new Color(0, 102, 255,100));
			g.fill3DRect(r.x+(t-1)*r.width/(numOfVLines-1),
					r.y+r.height-i.getY()/step*r.height/(numOfHLines-1),
					r.width/(numOfVLines-1),
					i.getY()/step*r.height/(numOfHLines-1),true);
			t++;
		}
	}

}
