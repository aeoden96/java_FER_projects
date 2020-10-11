package hr.fer.zemris.math;

/**
 * Klasa čuva polinom koji je zapisan u obliku f(x)=
 * (x-x<sub>1</sub>)****(x-x<sub>n</sub>) ,gdje su x<sub>i</sub> korijeni
 * polinoma. Korijeni se proslijeđuju u konstruktor kao polje. Ne postoji gornja
 * granica na broj korijena.
 * 
 * @author Mateo
 *
 */
public class ComplexRootedPolynomial {
	/**
	 * Sadrži polje svih korijena polinoma.
	 */
	private final Complex[] roots;

	/**
	 * Konsttruktor prima polje korijena.
	 * 
	 * @param roots
	 *            polje korijena
	 */
	public ComplexRootedPolynomial(Complex... roots) {

		this.roots = roots;

	}

	/**
	 * Funkcija račuuna vrijednost polinoma u točki z tako da prolazi kroz svaki
	 * faktor i množi razlike (z-xi) u svakom koraku.
	 * 
	 * @param z
	 *            točka u kojoj se traži f(z)
	 * @return vraća se dobiveni f(z)
	 */
	public Complex apply(Complex z) {
		Complex result = Complex.ONE;
		for (Complex i : roots) {
			result = result.multiply(z.sub(i));
		}
		return result;
	}

	/**
	 * Funkcija stvara novi polinom tipa ComplexPolynomial koji je zapisan u osnovnom obliku
	 * s faktorima.
	 * @return polinom tipa ComplexPolynomial 
	 */
	public ComplexPolynomial toComplexPolynom() {
		ComplexPolynomial result = new ComplexPolynomial(Complex.ONE);
		for (Complex i : roots) {
			result = result.multiply(new ComplexPolynomial(new Complex(0, 1), i.negate()));
		}
		return result;
	}
	
	/**
	 * Funckija vraća string reprezentaciju ovog polioma.
	 */
	@Override
	public String toString() {
		String result = "";
		for (Complex i : roots) {
			result = result + i.toString() + " ";
		}
		return result;
	}

	// finds index of closest root for given complex number z that is within
	// treshold; if there is no such root, returns -1
	/**
	 * Funcija provjerava koji je korijen najbliži zadanoj točci,
	 * i potom provjerava je li udaljeniji manje od zadanog tresholda.
	 * Ako nije , vraća se -1 ,inače se vraća redni broj korijena uz koji je točka najbliža.
	 * @param z za tu se točku provjerava
	 * @param treshold zadana granica.
	 * @return vraća redni broj korijena, ili -1 ako je izvan tresholda.
	 */
	public short indexOfClosestRootFor(Complex z, double treshold) {
		int index = 0;
		double minModule = 100;

		for (int i = 0; i < roots.length; i++) {
			if (roots[i].sub(z).module() < minModule) {
				minModule = roots[i].sub(z).module();
				index = i;
			}
		}
		if (minModule <= treshold) {
			return (short) index;
		} else
			return -1;
	}
}
