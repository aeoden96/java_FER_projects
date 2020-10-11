package hr.fer.zemris.java.gui.charts;

import java.awt.BorderLayout;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
 * Klasa inicira prozor u kojem će se nacrtati chart s informacijama 
 * iz datoteke koja se učita kao argument.
 * @author Mateo
 *
 */
public class BarChartDemo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * čuva informacije iz datoteke
	 */
	private static String info;
	/**
	 * čuva putanju do datoteke
	 */
	private static String path;
	/**
	 * Funkcija inicijalizira prozor i definira ime.
	 */
	public BarChartDemo() {
		super();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Bar Chart - " + path);
		setLocation(20, 20);
		setSize(800, 800);
		initGUI();
		
	}
	/**
	 * Funckija parsira podatke iz datoteke i iz toga stvara jedan BarChart objekt koji se šalje
	 * dalje na obradu.
	 */
	private void initGUI() {
		getContentPane().setLayout(new BorderLayout());
		
		String[] parts = info.split("\\n");

		List<XYValue> chartInfo= new ArrayList<>();
		String[] parts2= parts[2].split("\\s+");

		for( String i : parts2) {
			String[] nums=i.split(",");
			chartInfo.add(new XYValue(Integer.parseInt(nums[0]),Integer.parseInt(nums[1])));
		}
		
		BarChart model = new BarChart(
				chartInfo,
				parts[0],
				parts[1],
				Integer.parseInt(parts[3].trim()), // y-os kreće od 0
				Integer.parseInt(parts[4].trim()), // y-os ide do 22
				Integer.parseInt(parts[5].trim())
				);
		JComponent komponenta1 = new BarChartComponent(model);
		komponenta1.setLocation(100, 50);
		komponenta1.setSize(500, 500);
		komponenta1.setBorder(BorderFactory.createLineBorder(getBackground(), 10));
		komponenta1.setOpaque(true);
		
		getContentPane().add(komponenta1);
	}

	
	/**
	 * Glavna funkcija programa.Učitava putanju i čita podatke iz datoteke.
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length!=1) {
			System.out.println("Krivi broj argumenata.");
			System.exit(1);
		}
		String filepath = args[0];
		String doc=null;
		try {
			doc = new String(Files.readAllBytes(Paths.get(filepath)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("Greška pri čitanju datoteke.");
			System.exit(1);
		}
		info=doc;
		path=args[0];
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				BarChartDemo prozor = new BarChartDemo();
		
				
				prozor.setVisible(true);
			}
		});
	}
}

