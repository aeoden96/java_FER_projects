package hr.fer.zemris.java.custom.scripting.elems;

/**
 * sluzi za spremanje imena funkcija ,nasljeđeno od Element
 * 
 * @author Mateo
 *
 */
public class ElementFunction extends Element {

	/**
	 * Ovaj parametar je read-only,pa se nesmije mijenjati. Inicijalizira se pomoću
	 * konstruktora ,i ima svoje odgovarajuće get funkcije za dohvaćanje elementa
	 */
	String name;

	/**
	 * Konstruktor za ElementFunction,Pošto je String read-only ,moguće ga je
	 * pormijeniti samo pomoću konstruktora
	 * 
	 * @param name
	 */
	public ElementFunction(String name) {
		super();
		this.name = name;
	}
	/**
	 * Vraća ime funkcije kao string
	 */
	@Override
	public String asText() {
		return name;
	}

}
