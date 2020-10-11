package hr.fer.zemris.java.hw05.db;
/**
 * Interface koji opisuje što svaki objekt u FieldValueGetters mora znati izračunati ako se 
 * fukcija pozove nad njim.
 * @author Mateo
 *
 */
public interface IFieldValueGetter {
	/**
	 * funkcija prima record nekog studenta, i ispisuje neku informaciju o njemu,ovisno 
	 * o objektu nad kojim je pozvana.
	 * @param record objekt koji reprezentira studenta
	 * @return vraća nek informaciju o studentu
	 */
	public String get(StudentRecord record);
}
