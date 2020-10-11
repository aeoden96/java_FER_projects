package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Element klasa iz koje se naslijeđuju različiti elementi.Ne koristi se za
 * spremanje elemenata u parseru jer ni nema članove varijable,koristi se kao
 * referenca
 * 
 * @author Mateo
 *
 */
public class Element {
	/**
	 * Jedina funkcija u klasi.Potrena je da bi mogli ispisati ili provjeriti što se
	 * nalazi u trenutnom elementu.Ovaj generalni node nema vrijednost,pa vraća prazan string.
	 * 
	 * @return
	 */
	public String asText() {
		return "";
	}
}
