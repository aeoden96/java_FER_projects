package hr.fer.zemris.java.hw05.db;

/**
 * Klasa sadrži niz objekata koji reprezentiarju vrstu informacije o studentu.
 * Svi su objekti static i tipa su IFieldValueGetter.
 * 
 * @author Mateo
 *
 */
public class FieldValueGetters {
	/**
	 * Objekt reprezentira ime studenta.Kad se pozove funkcija nad njim, iz
	 * StudendRecord vraća ime.
	 */
	public static final IFieldValueGetter FIRST_NAME = record -> record.getFirstName();
	/**
	 * Objekt reprezentira prezime studenta.Kad se pozove funkcija nad njim, iz
	 * StudendRecord vraća prezime.
	 */
	public static final IFieldValueGetter LAST_NAME = record -> record.getLastName();
	/**
	 * Objekt reprezentira jmbag studenta.Kad se pozove funkcija nad njim, iz
	 * StudendRecord vraća jmbag.
	 */
	public static final IFieldValueGetter JMBAG = record -> record.getJmbag();

}
