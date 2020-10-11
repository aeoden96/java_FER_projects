package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;
/**
 * Node koji predstavlja for petlj.Sadrži varijable za upravljanje petljom,kao i ime petlje.
 * @author Mateo
 *
 */
public class ForLoopNode extends Node{
	
	ElementVariable variable;  //read-only
	Element startExpression;  //read-only
	Element endExpression;  //read-only
	Element stepExpression;   //read-only //can be null
	/**
	 * Konstruktor za inicijalizaciju for petljr,tj.njezinih argumenata
	 * @param variable
	 * @param startExpression
	 * @param endExpression
	 * @param stepExpression
	 */
	public ForLoopNode(ElementVariable variable, Element startExpression, Element endExpression,
			Element stepExpression) {
		super();
		this.variable = variable;
		this.startExpression = startExpression;
		this.endExpression = endExpression;
		this.stepExpression = stepExpression;
	} 
	/**
	 * vraća ime petlje
	 * @return
	 */
	public ElementVariable getVariable() {
		return variable;
	}
	/**
	 * vraća početni uuvjet
	 * @return
	 */
	public Element getStartExpression() {
		return startExpression;
	}
	/**
	 * vraća prekidni vjet
	 * @return
	 */
	public Element getEndExpression() {
		return endExpression;
	}
	/**
	 * vraća korak petlje
	 * @return
	 */
	public Element getStepExpression() {
		return stepExpression;
	}
	

}
