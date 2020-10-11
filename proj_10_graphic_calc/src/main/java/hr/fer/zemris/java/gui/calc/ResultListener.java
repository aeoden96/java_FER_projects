package hr.fer.zemris.java.gui.calc;

import javax.swing.JLabel;
/**
 * Ovaj listener pri promjeni rezultata u kalkulatoru,ispisuje u labelu novu vrijednost.
 * @author Mateo
 *
 */
public class ResultListener implements CalcValueListener {
	/**
	 * Dobiveni jLabel objekt koji modificiramo.
	 */
	JLabel comp;
	
	/**
	 * Konstruktor prima JLabel i sprema ga.
	 * @param comp
	 */
	public ResultListener(JLabel comp) {
		super();
		this.comp = comp;
	}
	
	/**
	 * Funckija prima instancu kalkulatora koja se koristi za dohvaćanje trenutne vrijednosti
	 * u kalkulatoru, i ta se vrijednost potom u spremljen objekt JLabel sprema.
	 */
	@Override
	public void valueChanged(CalcModel model) {
		 comp.setText(model.toString());

	}

}
