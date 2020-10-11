package hr.fer.zemris.java.hw06.observer2;

/**
 * Instances of IntegerStorageChange class encapsulate (as read-only properties)
 * following information:
 * <ul>
 * <li>a reference to IntegerStorage
 * <li>the value of stored integer before the change has occurred
 * <li>the new value of currently stored integer.
 * </ul>
 * 
 * @author Mateo
 *
 */
public class IntegerStorageChange {
	/**
	 * reference to IntegerStorage
	 */
	IntegerStorage storage;
	/**
	 * value of stored integer before the change has occurred
	 */
	int beforeChange;
	/**
	 * the new value of currently stored integer
	 */
	int value;

	/**
	 * Constuctor saves given references in apropriate variables.
	 * @param storage
	 * @param beforeChange
	 * @param value
	 */
	public IntegerStorageChange(IntegerStorage storage, int beforeChange, int value) {
		super();
		this.storage = storage;
		this.beforeChange = beforeChange;
		this.value = value;

	}

	/**
	 * Default getteer function for IntegerStorage
	 * @return return reference to current storage
	 */
	public IntegerStorage getStorage() {
		return storage;
	}

	/**
	 * Default getter function for old state
	 * @return returns an old state
	 */
	public int getBeforeChange() {
		return beforeChange;
	}
	/**
	 * Default getter function for new state
	 * @return returns new state
	 */
	public int getValue() {
		return value;
	}

}
