package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.math.Vector2D;

/**
 * Klasa pamti trenutnu poziciju na kojoj se kornjača nalazi (Vector2D), smjer u
 * kojem kornjača gleda (Vector2D), boju kojom kornjača crta (Color) te
 * efektivnu duljinu pomaka (double).
 * 
 * @author Mateo
 *
 */
public class TurtleState {
	/**
	 * trenutna pozicija kornjače
	 */
	private Vector2D position;
	/**
	 * treenuta orijentacija kornjače
	 */
	private Vector2D direction;
	/**
	 * boja kojom kornjača iscrtava linije
	 */
	private String color;
	/**
	 * duljina koraka kojom kornjača crta
	 */
	private double effStep;

	/**
	 * Konstruktor kojim se definira jedno stanje kornjače. Nije promjenjivo.
	 * 
	 * @param position
	 * @param direction
	 * @param color
	 * @param effStep
	 */
	public TurtleState(Vector2D position, Vector2D direction, String color, double effStep) {
		super();
		this.position = position;
		this.direction = direction;
		this.color = color;
		this.effStep = effStep;
	}

	/**
	 * Stvara kopiju trenutne kornjače i vraća ju.Funkcija se oslanja a copy
	 * funkcije iz Vector3D klase
	 * 
	 * @return kopija trenutnog stanja kornjače
	 */
	public TurtleState copy() {
		Vector2D newPosition = position.copy();
		Vector2D newDirection = direction.copy();
		return new TurtleState(newPosition, newDirection, color, effStep);
	}

	/**
	 * Getter za poziciju
	 * @return vraća trenutnu poziciju kornjače
	 */
	public Vector2D getPosition() {
		return position;
	}
	/**
	 * Getter za smjer
	 * @return vraća trenutni smjer kornjače
	 */
	public Vector2D getDirection() {
		return direction;
	}
	
	/**
	 * Getter za boju
	 * @return vraća trenutnu boju
	 */
	public String getColor() {
		return color;
	}
	/**
	 * Getter za korak
	 * @return vraća trenutnu veličinu koraka kojom kornjača crta
	 */
	public double getEffStep() {
		return effStep;
	}

}
