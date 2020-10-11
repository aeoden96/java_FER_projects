package demo;

import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;

/**
 * U trećem mainu koristi se izbornik unutar programa pomoću kojeg je potrebno
 * izabrati neki primjer L-sistema kojeg će program ispisat.
 * 
 * @author Mateo
 *
 */
public class Glavni3 {
	/**
	 * Stvara izbornik u kojem je potrebno izabrat neki primejrak fraktala, koji će
	 * program ispisat.
	 * 
	 * @param args
	 *            ne koristi se
	 */
	public static void main(String[] args) {
		//NAPOMENA*************************************
		//primjeri su u lib/examples 
		//sierpinskiGasket i plant3 su izmijenjeni jer im nije bio definiran 
		//po jedan command .sada svi rade
		LSystemViewer.showLSystem(LSystemBuilderImpl::new);

	}

}
