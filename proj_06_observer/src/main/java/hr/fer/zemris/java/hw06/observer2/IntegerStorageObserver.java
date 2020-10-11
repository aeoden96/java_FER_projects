package hr.fer.zemris.java.hw06.observer2;
/**
 * Interfece which is used to define how will function which our subjec calls
 * over every observer look like.Each observer mush implement it, in order for
 * a Subject to be able to comunicate with it.
 * 
 * @author Mateo
 *
 */

public interface IntegerStorageObserver {
	/**
	 * whenever a value is changed, a subject calls this function over every observer
	 * to let hi know of that change.
	 * @param istorage Subject class in our strategy
	 */
	public void valueChanged(IntegerStorageChange istorage);
}
