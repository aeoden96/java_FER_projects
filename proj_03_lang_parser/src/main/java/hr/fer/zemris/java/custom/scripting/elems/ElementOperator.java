package hr.fer.zemris.java.custom.scripting.elems;
/**
 * Naslijeđena iz Element. Čuva simbol u obliku stringa 
 * Podržani simboli: +,-,*,/,^
 * @author Mateo
 *
 */
public class ElementOperator extends Element{
	/**
	 * string koji čuva simbol
	 */
	String symbol;
	
	/**
	 * Konstruktor za ovu klasu,jedino tako moguće promijeniti vrijednost,pošto je operator read-only
	 * @param symbol
	 */
	public ElementOperator(String symbol) {
		super();
		this.symbol = symbol;
	}
	
	/**
	 * veaća simbol kao striing vrijednost
	 */
	@Override
	public String asText() {
		return symbol;
	}
}
