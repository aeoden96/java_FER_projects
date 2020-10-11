package hr.fer.zemris.java.custom.scripting.exec;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is in basic terms a multimap,it maps a list of values to a single
 * key. Accessibility of keys values has complexity of 1 , (its implemented
 * using HashMap). Each value is object of type {@link ValueWrapper}
 * 
 * @author Mateo
 *
 */
public class ObjectMultistack {
	/**
	 * It stores connections between keys and beginings of lists where values are
	 * stored.
	 */
	private Map<String, MultistackEntry> stack;

	/**
	 * Constructor initialises a new HashMap
	 */
	public ObjectMultistack() {
		stack = new HashMap<>();
	}

	/**
	 * Object of type MultistackEntry act as a node in a single linked list. They
	 * store a reference to the next node and value element.
	 * 
	 * @author Mateo
	 *
	 */
	private static class MultistackEntry {
		/**
		 * A {@link ValueWrapper} element object
		 * 
		 */
		private ValueWrapper value;
		/**
		 * Reference to a next node in a list
		 */
		private MultistackEntry next;

		/**
		 * Constructor initialises a value
		 * 
		 * @param value
		 */
		public MultistackEntry(ValueWrapper value) {
			this.value = value;
		}

	}

	/**
	 * Function pushes a element on the begining of the list ,list that belongs to a
	 * specific key.
	 * 
	 * @param name
	 * @param valueWrapper
	 */
	public void push(String name, ValueWrapper valueWrapper) {
		MultistackEntry newValue = new MultistackEntry(valueWrapper);

		if (!stack.containsKey(name)) {
			stack.put(name, newValue);
			return;
		}
		newValue.next = stack.get(name);
		stack.put(name, newValue);

	}
/**
 * Function pop an element from the top of keys list,and returns that value
 * @param name keys from whose list the element will be popped
 * @return returns first value in keys list
 */
	public ValueWrapper pop(String name) {

		if (!stack.containsKey(name))
			return null;

		MultistackEntry newValue = stack.get(name);

		if (newValue.next == null) {
			stack.remove(name);
			return newValue.value;
		}

		stack.put(name, stack.get(name).next);
		return newValue.value;

	}
	/**
	 * Returns values on the beggining of the keys list.
	 * @param name
	 * @return reference to value that is on beggining of this list
	 */
	public ValueWrapper peek(String name) {
		return stack.get(name).value;
	}
	/**
	 * Checks if multimap is empty and returns true if it is ,or false otherwise
	 * @param name 
	 * @return true or false, depending on cardinality of the list
	 */
	public boolean isEmpty(String name) {
		if (stack.isEmpty())
			return true;
		return false;
	}
}
