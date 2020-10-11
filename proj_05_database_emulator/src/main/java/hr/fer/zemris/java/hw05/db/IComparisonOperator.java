package hr.fer.zemris.java.hw05.db;

/**
 * Interface koji određuje kojeg će formata bit operatori i što svaki od njih
 * mora znati izračunati i vratiti. Pošto je zadana samo jedna fja, preporuča se
 * korištenje lambda izraza.
 * 
 * @author Mateo
 *
 */
public interface IComparisonOperator {
	/**
	 * Defaultna funkcija svakog operatora. Operator uzme 2 stringa ,usporedi ih ,te vrati 
	 * true ili false,ovisno o operaciji
	 * @param value1 prvi argument
	 * @param value2 drugi argument
	 * @return braća true ili false ,ovisno o operaciji
	 */
	public boolean satisfied(String value1, String value2);
}
