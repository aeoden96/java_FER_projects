package hr.fer.zemris.java.custom.collections;

import java.util.Arrays;

/**
 * Inherited for Collection class, this class uses Arrays to store and
 * manipulate with objects. It has 4 constructors ,and the memory it uses is
 * dynamically allocated.
 * 
 * @author Mateo
 *
 */
public class ArrayIndexedCollection extends Collection {
	/**
	 * number of elements in collection
	 */
	private int size;
	/**
	 * capacity of collection,imcluding empty space
	 */
	private int capacity;
	/**
	 * this stores every element that was put in collection
	 */
	private Object[] elements;

	// CONSTRUCTORS
	/**
	 * creates collection with default capacity
	 */
	ArrayIndexedCollection() {
		this(16);
	}
	/**
	 * creates new collection, with set initial capacity
	 * @param initialCapacity
	 */
	ArrayIndexedCollection(int initialCapacity) {
		if (initialCapacity < 1)
			throw new IllegalArgumentException("initialCapacity is less then 1");
		capacity = initialCapacity;
		elements = new Object[capacity];
	}
	/**
	 * takes in other collection and copies its content to this collection
	 * @param other other collection
	 */
	ArrayIndexedCollection(Collection other) {
		this(16 < other.size() ? other.size() : 16);
		addAll(other);

	}
	/**
	 * takes in other collection and copies its content to this collection
	 * @param initialCapacity wanted initial capacity of collection
	 * @param other other collection
	 */
	ArrayIndexedCollection(int initialCapacity, Collection other) {
		this(initialCapacity < other.size() ? other.size() : initialCapacity);
		addAll(other);

	}

	// PRIVATE HELP FUNCTIONS
	/**
	 * Helps to reallocate memory when limit capacity is reached
	 */
	private void realloc() {

		elements = Arrays.copyOf(elements, capacity *= 2);

	}

	// Override functions

	/**
	 * Tells the size of the Collection.Can be zero.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds the given object into this collection. Implement it here to do nothing.
	 * 
	 * @param value
	 *            adds the Object 'value' in this collection
	 */
	@Override
	public void add(Object value) {
		if (value == null) {
			throw new NullPointerException("null as element tried to be added in Collection");
		}
		if (capacity <= size)
			realloc();

		elements[size] = value;
		size++;

	}

	/**
	 * Returns true only if the collection contains given value.
	 * 
	 * @param value
	 *            value to be checked
	 * @return Returns true only if the collection contains given value
	 */
	@Override
	public boolean contains(Object value) {
		for (int i = 0; i < size; i++) {
			if (value.equals(elements[i]))
				return true;
		}
		return false;
	}


	/**
	 * Returns true only if the collection contains given value as determined by
	 * equals method and removes one occurrence of it.
	 * 
	 * @param value
	 * @return Returns true only if the collection contains given value as
	 *         determined
	 */
	@Override
	public boolean remove(Object value) {

		for (int i = 0; i < size; i++) {
			if (elements[i].equals(value)) {

				for (int j = i; j < size - 1; j++) {
					elements[j] = elements[j + 1];
				}
				size--;
				return true;
			}

		}
		return false;
	}


	/**
	 * Allocates new array with size equals to the size of this collections, fills
	 * it with collection content and returns the array.
	 * 
	 * @return array to be returned
	 */
	@Override
	public Object[] toArray() {

		return Arrays.copyOf(elements, size);
	}


	/**
	 * Method calls processor.process(.) for each element of this collection. The
	 * order in which elements will be sent is undefined in this class.
	 * 
	 * @param processor
	 */
	@Override
	public void forEach(Processor processor) {
		for (Object t : elements) {
			if (t == null)
				break;
			processor.process(t);
		}

	}


	/**
	 * Removes all elements from this collection. implemented as empty function
	 */
	@Override
	public void clear() {
		size = 0;
		capacity = 16;
		elements = new Object[capacity];
	}

	// some other functions
	/**
	 * Returns the object that is stored in backing array at position index. Valid
	 * indexes are 0 to size-1. If index is invalid,it throws exception
	 * (IndexOutOfBoundsException).
	 * 
	 * @param index
	 *            index of element in array
	 * @return returns an Object on that index
	 */
	public Object get(int index) {
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("index not between 0 and " + (size - 1));
		return elements[index];
	}

	/**
	 * Inserts the given value at the given position in array. The legal positions
	 * are 0 to size (both are included). If position is invalid, function throws
	 * (IndexOutOfBoundsException).
	 * 
	 * @param value
	 *            inserts this Object in the array
	 * @param position
	 *            inserts an object in this position
	 */
	public void insert(Object value, int position) {

		if (position < 0 || position > size)
			throw new IndexOutOfBoundsException("tried to insert Object with invalid position");
		if (value == null)
			throw new NullPointerException("storage of null references is not allowed");
		add(value); // in case empty space is not allocated
		for (int i = size - 1; i > position; --i) {
			elements[i] = elements[i - 1];
		}
		elements[position] = value;
	}

	/**
	 * Searches the collection and returns the index of the first occurrence of the
	 * given value or -1 if the value is not found. Argument can be null ,in that
	 * case result is also -1
	 * 
	 * @param value
	 *            the Object the function searches
	 * @return returns index of Object the function searches
	 */
	public int indexOf(Object value) {

		for (int i = 0; i < size; ++i) {
			if (value.equals(elements[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Removes element at specified index from collection. Element that was
	 * previously at location index+1 after this operation is on location index,
	 * etc. Legal indexes are 0 to size-1. In case of invalid index function throws
	 * (IndexOutOfBoundsException).
	 * 
	 * @param index
	 */
	public void remove(int index) {
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("index not between 0 and " + (size - 1));

		for (int j = index; j < size - 1; j++) {
			elements[j] = elements[j + 1];
		}
		size--;
		elements[size]=null;
		return;

	}

}