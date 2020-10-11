package hr.fer.zemris.java.custom.collections;

/**
 * Inherited for Collection class, this class uses linked list to store and
 * manipulate with objects. It has 2 constructors. It manages the memory better
 * that array version, but functions for searching and deleting elements may be
 * more complex
 * 
 * @author Mateo
 *
 */
public class LinkedListIndexedCollection extends Collection {
	/**
	 * Basic element node for linked list. it has storage variable,and reference to
	 * next and previous node
	 * 
	 * @author Mateo
	 *
	 */
	static class ListNode {
		public Object storage;
		public ListNode next;
		public ListNode previous;
	}

	private int size;
	private ListNode first;
	private ListNode last;

	// COSTRUCTORS
	LinkedListIndexedCollection() {
		size = 0;
		first = null;
		last = null;
	}

	LinkedListIndexedCollection(Collection other) {

		if (other == null)
			throw new NullPointerException("given Collection is null");
		if (other.size() == 0) {
			first = null;
			last = null;
			return;
		}
		addAll(other);

	}

	// Override functions
	@Override
	public int size() {
		return size;
	}

	@Override
	/**
	 * Adds the given object into this collection. 
	 * Implement it here to do nothing.
	 * @param value adds the Object 'value' in this collection
	 */
	public void add(Object value) {
		if (value == null)
			throw new NullPointerException("added value must not be null");
		if (size != 0) {
			System.out.println("adding " + value + ",size  " + size);
			ListNode newNode = last;
			last = new ListNode();
			last.storage = value;
			last.next = null;
			last.previous = newNode;
			newNode.next = last;

			size++;
		} else {
			System.out.println("adding " + value + ",size " + (size));

			ListNode newNode = new ListNode();
			first = newNode;
			last = newNode;
			newNode.storage = value;
			size++;
		}

	}

	@Override
	/**
	 * Returns true only if the collection contains given value.
	 * @param value value to be checked
	 * @return Returns true only if the collection contains given value
	 */
	public boolean contains(Object value) {

		if (indexOf(value) == -1)
			return false;
		return true;
	}

	@Override
	/**
	 * Returns true only if the collection contains given value as determined 
	 * by equals method and removes one occurrence of it.  
	 * 
	 * @param value
	 * @return  Returns true only if the collection contains given value as determined 
	 */
	public boolean remove(Object value) {

		ListNode temp = first;
		if (temp.storage.equals(value)) {
			first = first.next;
			size--;
			return true;
		} else if (last.storage.equals(value)) {
			last = last.previous;
			size--;
			return true;
		} else {
			temp = first.next;
			while (temp.next != null) {
				if (temp.storage.equals(value)) {
					temp.previous.next = temp.next;
					temp.next.previous = temp.previous;
					size--;
					return true;

				}
			}
		}
		return false;
	}

	@Override
	/**
	 * Allocates new array with size equals to the size of this collections, 
	 * fills it with collection content and returns the array. 
	 * 
	 * @return array to be returned
	 */
	public Object[] toArray() {
		Object[] tempArray = new Object[size];
		ListNode temp = first;
		int i = 0;
		while (temp.next != null) {
			tempArray[i] = temp.storage;
			temp = temp.next;
			i++;
		}
		tempArray[i] = temp.storage;

		return tempArray;

	}

	@Override
	/**
	 * Method calls processor.process(.) for each element of this collection. 
	 * The order in which elements will be sent is undefined in this class. 
	 * here implemented as empty function
	 * @param processor
	 */
	public void forEach(Processor processor) {
		ListNode temp = first;
		while (temp.next != null) {
			processor.process(temp.storage);
			temp = temp.next;
		}
		processor.process(temp.storage);

	}

	@Override
	/**
	 * Removes all elements from this collection. 
	 * implemented as empty function
	 */
	public void clear() {
		first = null;
		last = null;
		size = 0;

	}

	// some other functions

	/**
	 * Returns the object that is stored in linked list at position index. Valid
	 * indexes are 0 to size-1. If index is invalid, it throws exception
	 * (IndexOutOfBoundsException).
	 * 
	 * @param index
	 * @return
	 */
	public Object get(int index) {
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("index '" + index + "' must be between 0 and " + (size - 1));

		ListNode fromBegining = first;
		ListNode fromEnd = last;
		for (int i = 0, j = size - 1; i < index && j > index; ++i, --j) {
			fromBegining = fromBegining.next;
			fromEnd = fromEnd.previous;
		}
		if (index < size / 2 + 1)
			return fromBegining.storage;
		else
			return last;
	}

	/**
	 * inserts the given value at the given position in linked-list. Elements
	 * starting from this position are shifted one position. The legal positions are
	 * 0 to size. If position is invalid, an appropriate exception is thrown.
	 * 
	 * @param value
	 * @param position
	 */
	void insert(Object value, int position) {
		if (value == null)
			throw new NullPointerException(" inserted value must not be null");
		if (position < 0 || position > size)
			throw new IndexOutOfBoundsException("index '" + position + "' must be between 0 and " + (size));
		if (position == size) {
			add(value);
			return;
		}
		ListNode fromBegining = first;
		ListNode fromEnd = last;
		for (int i = 0, j = size - 1; i < position && j > position; ++i, --j) {
			fromBegining = fromBegining.next;
			fromEnd = fromEnd.previous;
		}
		if (position < size / 2 + 1) {
			ListNode newNode = new ListNode();
			newNode.storage = value;
			newNode.previous = fromBegining.previous;
			newNode.next = fromBegining;
			fromBegining.previous = newNode;
			// fromBegining.next stays the same
			if (position == 0) {
				first = newNode;
			}

		} else {
			ListNode newNode = new ListNode();
			newNode.storage = value;
			newNode.previous = fromEnd.previous;
			newNode.next = fromEnd;
			fromEnd.previous = newNode;
			// fromBegining.next stays the same

		}
		size++;

	}

	/**
	 * Searches the collection and returns the index of the first occurrence of the
	 * given value or -1 if the value is not found. null is valid argument.
	 * 
	 * @param value
	 * @return
	 */
	public int indexOf(Object value) {
		if (value == null)
			return -1;
		ListNode temp = first;
		if (temp.storage.equals(value))
			return 0;
		int i = 1;
		while ((temp = temp.next) != null) {
			i++;
			if (temp.storage.equals(value))
				return i;
		}

		return -1;
	}

	/**
	 * Removes element at specified index from collection. Element that was
	 * previously at location index+1 after this operation is on location index,
	 * etc. Legal indexes are 0 to size-1. In case of invalid index exceptop is thrown
	 * 
	 * @param index
	 */
	void remove(int index) {
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("index '" + index + "' must be between 0 and " + (size - 1));

		if (index == 0) {
			first = first.next;
			size--;
			return;
		} else if (index == size - 1) {
			last = last.previous;
			size--;
			return;
		} else {
			ListNode temp = first.next;
			for (int i = 1; i < index; i++) {

				temp = temp.next;

			}
			temp.previous.next = temp.next;
			temp.next.previous = temp.previous;
			size--;
		}

	}
}
