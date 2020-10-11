package hr.fer.zemris.java.hw05.db;

/**
 * Ova klasa sadrži niz objekata koji reprezentiraju operatore koje korisnik
 * upisuje. Svi operatori su tipa IComparisonOperator i static su.
 * 
 * @author Mateo
 *
 */
public class ComparisonOperators {
	/**
	 * Operator koji prima 2 stringa i vraća true ako je prvi manji od drugog, false
	 * ako nije
	 */
	public static final IComparisonOperator LESS = (n1, n2) -> n1.compareTo(n2) < 0 ? true : false;
	/**
	 * Operator koji prima 2 stringa i vraća true ako je prvi manji ili jednak
	 * drugom, false ako nije
	 */
	public static final IComparisonOperator LESS_OR_EQUALS = (n1, n2) -> n1.compareTo(n2) <= 0 ? true : false;
	/**
	 * Operator koji prima 2 stringa i vraća true ako je prvi veći od drugog, false
	 * ako nije
	 */
	public static final IComparisonOperator GREATER = (n1, n2) -> n1.compareTo(n2) > 0 ? true : false;
	/**
	 * Operator koji prima 2 stringa i vraća true ako je prvi veći ili jednak
	 * drugom, false ako nije
	 */
	public static final IComparisonOperator GREATER_OR_EQUALS = (n1, n2) -> n1.compareTo(n2) >= 0 ? true : false;
	/**
	 * Operator koji prima 2 stringa i vraća true ako su stringovi jednaki,false ako
	 * nisu
	 */
	public static final IComparisonOperator EQUALS = (n1, n2) -> n1.equals(n2);
	/**
	 * Operator koji prima 2 stringa i vraća true ako su stringovi različiti,false
	 * ako su isti
	 */
	public static final IComparisonOperator NOT_EQUALS = (n1, n2) -> !n1.equals(n2);
	/**
	 * Operator koji prima string i pravilo,te se provjerava dali string spada pod
	 * definirano pravilo
	 */
	public static final IComparisonOperator LIKE = (n1, n2) -> {
		n2 = n2.replaceAll("\\*", "\\.\\*");
		return n1.matches(n2);
	};

}
