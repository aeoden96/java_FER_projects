package hr.fer.zemris.java.custom.collections;
/**
 * Simple Exception that is needes for our ObjectStack class. Somehow we needed to alert 
 * the user if he tries to pop something from empty stack.
 * @author Mateo
 *
 */
public class EmptyStackException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public EmptyStackException() {
		super();
	}

	public EmptyStackException(String message) {
		super(message);
	}

	public EmptyStackException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyStackException(Throwable cause) {
		super(cause);
	}

}
