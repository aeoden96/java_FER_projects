package hr.fer.zemris.java.custom.collections;
/**
 * Simple Exception that is needes for our ObjectStack class. Somehow we needed to alert 
 * the user if he tries to pop something from empty stack.
 * @author Mateo
 *
 */
public class EmptyStackException extends RuntimeException {

	/**
	 * potrebno za normalan rad ove klase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * konstruktor za exception
	 */
	public EmptyStackException() {
		super();
	}
	/**
	 * konstruktor za exception
	 * @param message
	 */
	public EmptyStackException(String message) {
		super(message);
	}
	/**
	 * konstruktor za exception
	 * @param cause 
	 * @param message 
	 */
	public EmptyStackException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * konstruktor za exception
	 * @param cause 
	 */
	public EmptyStackException(Throwable cause) {
		super(cause);
	}

}
