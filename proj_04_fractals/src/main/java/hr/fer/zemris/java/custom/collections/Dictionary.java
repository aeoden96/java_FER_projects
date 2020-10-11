package hr.fer.zemris.java.custom.collections;

/**
 * Ova klasa implementira rječnik ,nešto kao mapa u C++. Rječnik čuva parove
 * objekata ,ključ i value . Princip je da ključevi moraju biti JEDINSTVENI ,a
 * svaki ključ može imati bilokakav value. 2 ključa mogu imati isti value.
 * 
 * @author Mateo
 *
 */
public class Dictionary {
	/**
	 * kolekcija koja čuva sve ključeve u rječniku
	 */
	private ArrayIndexedCollection keys;
	/**
	 * kolekcija koja čuva sve value objekte u rječniku
	 */
	private ArrayIndexedCollection values;

	/**
	 * defaultni konstruktor koji prima inicijalizira kolekcije korištene u klasi
	 */
	public Dictionary() {
		super();
		keys = new ArrayIndexedCollection();
		values = new ArrayIndexedCollection();

	}

	/**
	 * Funkcija provjerava dali je rječnik prazan
	 * 
	 * @return ako je rječnik prazan vraća true, inače vraća false
	 */
	public boolean isEmpty() {
		if (keys.isEmpty())
			return true;
		return keys.isEmpty();

	}

	/**
	 * Vraća trenutni broj parova u rječniku.Pošto su parovi jedinstveno definirani
	 * ključem,dovoljno je provjeriti broj ključeva u rječniku.
	 * @return vraća broj parova u rječniku
	 */
	public int size() {
		return keys.size();
	}
	/**
	 * Briše cijeli rječnik,ostavlja ga praznim.
	 */
	public void clear() {
		keys.clear();
		values.clear();
	}
	/**
	 * Stavlja par iz argumenata u rječnik.Ako key već postoji u rječniku ,tada 
	 * se ta vrijednost gazi s ovom.
	 * Key nesmije biti null,inače se baca exception.
	 * Valuue također nesmije biti null. Tako je definiran ArrayIndexedCollection.
	 * (moglo se definirati da value može biti null, ali takvo kompliciranje nije 
	 * potrebno za stvari za koje je riječnik namijenjen )
	 * @param key kjluč koji se stavljati u rječnik
	 * @param value value koji se stavlja uz ključ u rječnik
	 * @throws NullPointerException baca se ukoliko je key ili value =null
	 */
	public void put(Object key, Object value) {
		if (key == null)
			throw new NullPointerException("ključ nesmije biti null");
		if (value == null)
			throw new NullPointerException("value nesmije biti null");
		
		int indexKey = keys.indexOf(key);
		if (indexKey != -1) {
			values.remove(indexKey);
			values.insert(value, indexKey);
			return;
		}
		keys.add(key);
		values.add(value);

	}
	/**
	 * Dobavlja value od ključa koji je poslan kao argument.
	 * Ukoliko ga nema u rječniku,funkcija vraća null
	 * @param key ključ za koji se traži njegov value
	 * @return vraća se value od ključa
	 */
	public Object get(Object key) {
		Object k;
		try {
			k = values.get(keys.indexOf(key));
		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
		return k;
	} 
}
