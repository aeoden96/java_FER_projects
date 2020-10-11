package hr.fer.zemris.java.hw03.prob1;
/**
 * Klasa za pohranu tokena. Token je par vrijednosti : Elementa i vrste
 * vrijednosti koju pohranjuje. Vrste vrijednosti su određene enumeracijom
 * TokenType
 * 
 * @author Mateo
 *
 */
public class Token {
	/**
	 * Određuje tip vrijednosti tokena
	 */
	private TokenType type;
	/**
	 * Pohranjena vrijednost tipa Object
	 */
	private Object value;
	
	/**
	 * konstruktor za inicijalizaciju tokena. TokenType nesmije biti null
	 * 
	 * @param type
	 * @param value
	 */
	public Token(TokenType type, Object value) {
		if(type==null)
			throw new IllegalArgumentException("TokenType nesmije biti null");
		this.type=type;
		this.value=value;
	}
	/**
	 * funkcija vraća pohranjenu vrijednost tipa Object
	 * 
	 * @return
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * vraća se vrsta tokena koja je pohranjena u token
	 * 
	 * @return
	 */
	public TokenType getType() {
		return type;
	}
}