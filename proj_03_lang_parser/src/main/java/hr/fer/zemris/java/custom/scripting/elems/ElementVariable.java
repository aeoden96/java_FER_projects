package hr.fer.zemris.java.custom.scripting.elems;
/**
 * Naslijeđeno iz Element.Čuva ime varijable uu oblikuu stringa
 * @author Mateo
 *
 */
public class ElementVariable extends Element{
	/**
	 * čuva ime varijable
	 */
	String name; //read-only property
	/**
	 * konstrktor pomoću kojeg se varijabla inicijalizira,tj.njeno ime
	 * @param name
	 */
	public ElementVariable(String name) {
		super();
		this.name = name;
	}

	/**
	 * vraća vrijednost imena varijable
	 */
	@Override
	public String asText() {
		return name;
	}

}
