package hr.fer.zemris.java.hw05.db;
/**
 * Interface koji daje pravilo kako trebaju izgledati objekti koji odlučuju koje
 * objekte izabrati za ispis,a koje ne.Definirana je jedna funkcija.
 * @author Mateo
 *
 */
public interface IFilter {
	/**
	 * Funkcija bi trebala provjeravati jel je taj record prihvatljiv izbor za ispis,
	 * ovisno o uvjetima.
	 * @param record record nekod studenta
	 * @return vraća true ako je record prihvatljiv,a false ako nije
	 */
	public boolean accepts(StudentRecord record);
}
