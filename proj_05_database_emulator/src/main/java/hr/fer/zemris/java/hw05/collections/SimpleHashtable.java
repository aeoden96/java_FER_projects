package hr.fer.zemris.java.hw05.collections;

import static java.lang.Math.abs;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Ova klasa služi kao jednostavna mapa. Sadrži parove ključ-vrijednost i sprema
 * ih korištenjem hash vrijednosti za brzo dohvaćanje.
 * 
 * @author Mateo
 * @param <K>
 *            tip ključa
 * @param <V>
 *            tip vrijednosti
 *
 */
public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K, V>> {
	/**
	 * kapacitet tablice ako se ne predlži neki drugi
	 */
	private static final int INIT_CAPACITY = 16;
	/**
	 * glavna tablica
	 */
	private TableEntry<K, V>[] table;
	/**
	 * broj parova u tablici
	 */
	private int size;
	/**
	 * ukupnni broj modifikacija
	 */
	private int modifcationCount = 0;

	/**
	 * Klasa predstavlja jednu vezu u mapi
	 * 
	 * @param <K>
	 *            tip kjluča
	 * @param <V>
	 *            tip vrijednosti
	 */
	public static class TableEntry<K, V> {
		/**
		 * ključ pomoću kojeg se određuje mjesto u memoriji
		 */
		private K key;
		/**
		 * vrijednost vezana uz taj ključ
		 */
		private V value;
		/**
		 * referenca na idući objekt
		 */
		private TableEntry<K, V> next;

		/**
		 * Koonstruktor prima ključ i value i stava novi objekt pomoću njih
		 * 
		 * @param key
		 *            ključ
		 * @param value
		 *            vrijednost vezana uz ključ
		 */

		public TableEntry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}

		/**
		 * Vraća ključ tipa K
		 * 
		 * @return vraća kljč
		 */
		public K getKey() {
			return key;
		}

		/**
		 * vraća vrijednost tipa V
		 * 
		 * @return vraća value od ključa
		 */
		public V getValue() {
			return value;
		}

		/**
		 * Postavlja value na neku vrijednost.
		 * 
		 * @param value
		 *            nova vrijednost za value
		 */
		public void setValue(V value) {
			this.value = value;
		}

	};

	/**
	 * Implementacija operatora potrebna da bis ekroz tablicu moglo iterirati.
	 * 
	 * @author Mateo
	 *
	 */
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K, V>> {
		/**
		 * trenutni slot u tablici ,u kojoj se nalazi ovaj iterator
		 */
		private int currentSlot;
		/**
		 * trenutni node u tablici
		 */
		private TableEntry<K, V> currentNode;
		/**
		 * ukupnii broj modifikacija od kreiranja iteratora
		 */
		private int currentModificationCount;

		/**
		 * Konstruktor iteratora.Sve projače stavlja na početke vrijednosti.
		 */
		public IteratorImpl() {
			super();
			currentNode = null;
			currentSlot = 0;
			currentModificationCount = modifcationCount;
		}

		/**
		 * Gleda postoji li idući element u tablici ,ako ne vraća false;
		 * 
		 * @return false ako ne postoji više elemenata.
		 */
		public boolean hasNext() {
			if (currentModificationCount != modifcationCount)
				throw new ConcurrentModificationException("tablica mijenjana izvana!");
			if (currentNode == null) {
				return true;
			}
			if (currentNode.next != null)
				return true;
			for (int i = currentSlot + 1; i < table.length; i++) {
				if (table[i] != null)
					return true;
			}
			return false;
		}

		/**
		 * Uzima idući element iz tablice koji je iduć u redu.
		 * 
		 * @return vraća taj element
		 */
		@SuppressWarnings("rawtypes")
		public SimpleHashtable.TableEntry next() {
			if (currentModificationCount != modifcationCount)
				throw new ConcurrentModificationException("tablica mijenjana izvana!");
			if (currentNode == null) {
				for (int i = 0; i < table.length; i++) {
					if (table[i] != null) {
						currentNode = table[i];
						currentSlot = i;
						break;
					}
				}
			}
			if (hasNext() == false)
				throw new NoSuchElementException("irerator dosao do kraja tablice");
			else if (currentNode.next != null) {
				currentNode = currentNode.next;
				return currentNode;
			} else {
				currentSlot++;
				for (; currentSlot < table.length; currentSlot++) {
					if (table[currentSlot] != null)
						return table[currentSlot];
				}
				throw new NoSuchElementException("irerator dosao do kraja tablice");
			}

		}

		/**
		 * Iteratorov remove,samo on smije brisati elemente kod itarator iterira,inače
		 * će baciti exceptionn.
		 */
		public void remove() {
			if (currentModificationCount != modifcationCount)
				throw new ConcurrentModificationException("tablica mijenjana izvana!");
			 currentNode=currentNode.next;

		}
	};

	/**
	 * Mijenja veličinu tablice i uvećava ju za duplo ukoliko je to potrebno.
	 */
	@SuppressWarnings("unused")
	private void resizeTable() {
		if (size < 0.75 * table.length)
			return;
		int newSlotSize = table.length * 2;
		@SuppressWarnings("unchecked")
		TableEntry<K, V>[] newTable = (TableEntry<K, V>[]) new TableEntry[newSlotSize];
		for (int i = 0; i < table.length; i++) {
			int hash = getCode(table[i].getKey());
			newTable[hash] = table[i];
		}
		clear();
		table = newTable;

	}

	/**
	 * briše sve elemente tablice
	 */
	public void clear() {
		modifcationCount++;

		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}

	}

	/**
	 * 
	 */
	@Override
	public Iterator<SimpleHashtable.TableEntry<K, V>> iterator() {
		return new IteratorImpl();
	}

	/** stvara tablicu veličine 16 slotova */
	public SimpleHashtable() {
		this(INIT_CAPACITY);
	}

	/**
	 * prima jedan argument: broj koji predstavlja željeni početni kapacitet tablice
	 * i koji stvara tablicu veličine koja je potencija broja 2 koja je prva veća
	 * ili jednaka predanom broju (npr. ako se zada 30, bira se 32); ako je ovaj
	 * broj manji od 1, baca IllegalArgumentException
	 * @param capacity 
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable(int capacity) {
		if (capacity < 1)
			throw new IllegalArgumentException("Kapacitet nemoze biti : " + capacity);
		int size = 1;
		while (size < capacity) {
			size *= 2;
		}
		table = (TableEntry<K, V>[]) new TableEntry[size];
	}
	/**
	 * vraća hash kod za okređen ključ
	 * @param key
	 * @return hash code tog ključa
	 */
	private int getCode(Object key) {
		return abs(key.hashCode()) % table.length;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		
		if (key == null)
			throw new NullPointerException("Key nesmije biti null");

		int tableNumber = getCode(key);

		if (table[tableNumber] == null) {
			table[tableNumber] = new TableEntry<>(key, value);

			modifcationCount++;
			size++;
			return;
		}

		TableEntry<K, V> iterator = table[tableNumber];
		while (iterator.next != null) {
			if (iterator.getKey() == key) {
				iterator.setValue(value);
				return;
			}
			iterator = iterator.next;
		}
		modifcationCount++;
		size++;
		iterator.next = new TableEntry<>(key, value);

	}
	/**
	 * vraća vriednost ,čiji je ključ key
	 * @param key ključ čija se vrijednost traži
	 * @return vraća se vrijednost
	 */
	public V get(Object key) {
		int tableNumber = abs(key.hashCode()) % table.length;
		TableEntry<K, V> iterator = table[tableNumber];
		while (iterator != null) {
			if (iterator.getKey().equals(key))
				return iterator.getValue();
			iterator = iterator.next;
		}
		return null;
	}
	/**
	 * vraća broj parova pohranjenim u tablici
	 * @return broj parova u tablici
	 */
	public int size() {
		return size;

	}
/**
 * Traži u tablici određeni ključ
 * @param key ključ koji se traži
 * @return vraća true ako ga je našao 
 */
	public boolean containsKey(Object key) {
		int tableNumber = abs(key.hashCode()) % table.length;
		TableEntry<K, V> iterator = table[tableNumber];
		while (iterator != null) {
			if (iterator.getKey() == key)
				return true;
			iterator = iterator.next;
		}
		return false;
	}
/**
 * Provjerava dali se u tblici nalazi određena vrijednost-
 * @param value vriejdnost koja se traži
 * @return vraća true ako je nađena
 */
	public boolean containsValue(Object value) {
		for (int i = 0; i < table.length; i++) {
			TableEntry<K, V> iterator = table[i];
			while (iterator != null) {
				if (iterator.getValue() == value)
					return true;
				iterator = iterator.next;
			}
		}
		return false;
	}
	/**
	 * Briše jedan element iz tablice .
	 * @param key ključ koji se mora pobrisati,uključujući njegovu vrijednost
	 */
	public void remove(Object key) {
		int hash = getCode(key);
		if (table[hash] == null)
			return;
		if (table[hash].getKey() == key) {
			table[hash] = table[hash].next;
			modifcationCount++;
			size--;
			return;
		}
		TableEntry<K, V> iter = table[hash];
		while (iter.next != null) {
			if (iter.next.getKey() == key) {
				iter.next = iter.next.next;
				modifcationCount++;
				size--;
				return;
			}
		}

	}

	/**
	 * Provjerava jeli tablica prazna
	 * 
	 * @return ako je prazna vraća true,inče vraća false
	 */
	public boolean isEmpty() {
		for (int i = 0; i < size; i++) {
			if (table[i] != null)
				return false;
		}
		return true;
	}

	/**
	 * 
	 * Vraća reprezentaciju tablice kao string.
	 * @return String koji predstavlja tablicu
	 */
	public String toString() {
		String s = "[ ";
		for (int i = 0; i < table.length; i++) {
			s = s + " [" + table[i].getKey().toString() + " ," + table[i].getValue().toString() + " ]";
		}
		return s + " ]";
	}

}
