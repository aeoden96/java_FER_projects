package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.parser.ArrayIndexedCollection;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

/**
 * glavna klasa node iz koje se svi ostali nodovi nasljeđuju. Sadrži kolekciju djece ,i tatko stvara
 * stablo dokumenta
 * @author Mateo
 *
 */
public  class Node {
	ArrayIndexedCollection childrenCollection;
	/**
	 * dodaje child node trentnom parent nodu.Child node nesmije biti null. 
	 * funkcija ih potom pohranjuje.
	 * 
	 * @param child
	 */
	public  void addChildNode(Node child) {
		if(childrenCollection==null)
			childrenCollection=new ArrayIndexedCollection();
		childrenCollection.add(child);
		
	}

	/**
	 * vraća broj djece trenutnog noda
	 * 
	 * @return
	 */
	public  int numberOfChildren() {
		return childrenCollection.size();
	}

	/**
	 * vraća dijete node korz odgovaraćuji index .Ako je index neispravat ,funkcija baca iznimku
	 */
	public  Node getChild(int index) {
		if(index<0 || index >= childrenCollection.size())
			throw new SmartScriptParserException("tried to get child on "+ index + " position");
		return (Node)childrenCollection.get(index);
	}
}
