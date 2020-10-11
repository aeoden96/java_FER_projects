package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.Element;
/**
 * Echo node sadrži niz elemenata ,mogu biti bilokoja vrsta elementa
 */
public class EchoNode extends Node{
	Element[] elements; //read-only
	/**
	 * inicijalizira polje elemenata,tj.argumenata za echo
	 * @param elements
	 */
	public EchoNode(Element[] elements) {
		super();
		this.elements = elements;
	}
	/**
	 * getter fja za dohvćanje polja elemenata
	 * @return
	 */
	public Element[] getElements() {
		return elements;
	}
}
