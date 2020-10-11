package hr.fer.zemris.java.custom.scripting.elems;
/**
 * Klasa naslijeđena iz Element.Čuva string vrijednost
 * @author Mateo
 *
 */
public class ElementString extends Element{
	/**
	 * čuuva string vrijednost
	 */
	String value;
	/**
	 * konstruuktor pomoć kojeg je moguće inicijalizirati string vrijednost
	 * @param value
	 */
	public ElementString(String value) {
		super();
		this.value = value;
	}
	/**
	 * vraća string iz klase
	 */
	@Override
	public String asText() {
		return value;
	}
	
}
