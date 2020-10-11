package hr.fer.zemris.java.custom.scripting.nodes;

/**
 * klasa koja predstavlja tekst izvan tagova
 * 
 * @author Mateo
 *
 */
public class TextNode extends Node {
	String text; // read-only

	/**
	 * konstruktor kojim se prosljeđuje vrijednost stringa
	 * 
	 * @param text
	 */
	public TextNode(String text) {
		super();
		this.text = text;
	}

	/**
	 * getter fja kojim se vraća string
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

}
