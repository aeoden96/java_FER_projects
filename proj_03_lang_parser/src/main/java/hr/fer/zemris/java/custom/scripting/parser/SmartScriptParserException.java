package hr.fer.zemris.java.custom.scripting.parser;

/**
 * Iznimka koja se poziva unutar parsera i lexera.Zamjenjuje sve ostale klasične
 * iznimke
 * 
 * @author Mateo
 *
 */
public class SmartScriptParserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * konstuktor za izniku bez poruke o grešci
	 */
	public SmartScriptParserException() {
		super();
	}
	/**
	 * konstuktor za izniku s porukom o grešci
	 */
	public SmartScriptParserException(String message) {
		super(message);
	}

	public SmartScriptParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public SmartScriptParserException(Throwable cause) {
		super(cause);
	}

}
