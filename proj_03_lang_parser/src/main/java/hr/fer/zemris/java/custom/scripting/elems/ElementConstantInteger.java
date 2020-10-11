package hr.fer.zemris.java.custom.scripting.elems;
/**
 * Klasa za pospremanje cijelih brojeva. Naslijeđuje se iz Element klase.
 * @author Mateo
 *
 */
public class ElementConstantInteger extends Element {
	
	/**
	 * Ovaj parametar je read-only,pa se nesmije mijenjati. Inicijalizira se pomoću
	 * konstruktora ,i ima svoje odgovarajuće get funkcije za dohvaćanje elementa
	 */
	int value;

	public ElementConstantInteger(int value) {
		super();
		this.value = value;
	}
	
	@Override
	public String asText() {
		return Integer.toString(value);
	}

}
