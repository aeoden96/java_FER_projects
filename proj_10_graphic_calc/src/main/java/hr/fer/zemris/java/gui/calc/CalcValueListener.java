package hr.fer.zemris.java.gui.calc;

/**
 * Interface koji definira funkcije koje bi listener objekt trebao imati.
 * @author Mateo
 *
 */
public interface CalcValueListener {
	/**
	 * Funckija prima instancu kalkulatora i pomoću nje upisuje u komponentu 
	 * (dobienu kroz konstruktor) novu ažuriranu vrijednost.
	 * @param model
	 */
	void valueChanged(CalcModel model);
}
