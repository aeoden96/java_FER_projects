package hr.fer.zemris.java.gui.charts;

/**
 * Klasa sprema informacije za jedan unos u graf. Sastoji se od uređenog para,
 * broja koji piše ispod unosa i visine tog stupca.
 * @author Mateo
 *
 */
public class XYValue {
	/**
	 * broj koji piše na x osi
	 */
	private int x;
	/**
	 * visina stupca određenog s x
	 */
	private int y;
	/**
	 * Konstruktor prima x poziciju i visinu komponente u grafu.
	 * @param x
	 * @param y
	 */
	public XYValue(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	/**
	 * Funkcija vraća x komponentu.
	 * @return x komponenta
	 */
	public int getX() {
		return x;
	}
	/**
	 * Funkcija vraća visinu x komponente.
	 * @return visina x komponente
	 */
	public int getY() {
		return y;
	}
	
}
