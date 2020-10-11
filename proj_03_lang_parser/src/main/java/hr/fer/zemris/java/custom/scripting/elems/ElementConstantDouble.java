package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Klasa za pospremanje realnih brojeva. Naslijeđuje se iz Element klase.
 * 
 * @author Mateo
 *
 */
public class ElementConstantDouble extends Element {
	/**
	 * Ovaj parametar je read-only,pa se nesmije mijenjati. Inicijalizira se pomoću
	 * konstruktora ,i ima svoje odgovarajuće get funkcije za dohvaćanje elementa
	 */
	double value;

	/**
	 * Odgovarajući getter za double vrijednost
	 * @return vraća duoble pohranjenoj u objektu 
	 */
	public double getValue() {
		return value;
	}
	/**
	 * Jedini konstrktor za klasu. Prima double vrijednost koja se kasnije nemože mijenjati više
	 * @param value prima double vrijednost
	 */
	public ElementConstantDouble(double value) {
		super();
		this.value = value;
	}
	
	/**
	 * Služi za ispis double vrijednosti u obliku stringa
	 */
	@Override
	public String asText() {
		return Double.toString(value);
	}
}
