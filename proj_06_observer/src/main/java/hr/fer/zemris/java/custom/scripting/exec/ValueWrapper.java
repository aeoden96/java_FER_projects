package hr.fer.zemris.java.custom.scripting.exec;

/**
 * ValueWrapper stores a single object for keeping.Object must be a String or
 * Double or Integer type. Null is also supported value. Class also supports
 * arithmetic operations with elements, but only if operations for that elements
 * are defined,which means operation is allowed between two objects only if an
 * object is null ,Double,Integer ,or String that can be parsed into a Double or
 * an Integer.If user tries to use arithmetic operation on a regular string
 * which is not parsable in any of the above, RuntimeException will be thrown.
 * Also,Strings that were not involved in any arithmetic operation,including
 * null, stay unmodified, inclding parsable Strings.After operation, a String or
 * any other object type MAY change during an operation,except for Double,
 * Double type object can never change ,no matter the operation.
 * 
 * Here is more detailed list:
 * <ul>
 * <li>If two Integer objects are involved in a single operation, result is also
 * an Integer
 * <li>If any of the two are of type Double,result will also be of type Double
 * <li>If both objects are of type Double,result will also be of type Double
 * <li>If any of the two is null object, it is treatred as Integer with value 0
 * <li>If a String that can be parsed into an Integer is involved in an
 * operation, it is parsed into an Integer before that, or if it cannot be
 * parsed,it is then parsed into a Double
 * 
 * 
 * </ul>
 * 
 * 
 * @author Mateo
 *
 */
public class ValueWrapper {
	/**
	 * stored object
	 */
	private Object value;

	/**
	 * Function that checks if a value is of valid form to be stored in this Object.
	 * Supported types are only String ,Double or Integer. Null is also supported.
	 * 
	 * @param value
	 *            value to be checked
	 */
	private static void checkIfArithmetic(Object value) {
		if (value == null || value instanceof String || value instanceof Double || value instanceof Integer)
			return;
		throw new RuntimeException("input value is not allowed ,it must be of type String or Integer or Double");
	}

	/**
	 * Constructor takes in an object,and than checkes if it is of valid type.If it
	 * is, it is stored in this object.
	 * 
	 * @param value
	 *            value to be stored
	 */
	public ValueWrapper(Object value) {
		checkIfArithmetic(value);
		this.value = value;

	}

	/**
	 * Transforms a String that is taken as an argument ,and turns it into a Number
	 * object, if that Sring is parsable into double or int value. If it isn't
	 * ,exception is thrown. It should be noted that this function does not modify
	 * current stored object in any way.
	 * 
	 * @param value
	 *            a String that will be parsed into Double or Integer
	 * @return returns a Double or Integer object with parsed value from given
	 *         String
	 * @throws throws
	 *             an exception if given String cant be parsed
	 */
	private Number extractString(String value) {
		try {
			if (value.indexOf('.') == -1) {
				return Integer.valueOf(value);
			} else {
				return Double.valueOf(value);
			}

		} catch (NumberFormatException ex) {
			throw new RuntimeException("Entered String cannot be parsed into int or double");
		}
	}

	/**
	 * Checks if a value is eligible for computing
	 * <ul>
	 * <li>if a value is null,it is returned as Integer with value 0
	 * <li>if a value is a string, first it checks if a current string is even
	 * parsble,and if it is, it is then returned
	 * <li>if a Object is Double or Integer ,then it is surely eligible for
	 * computing
	 * 
	 * </ul>
	 * 
	 * 
	 * 
	 * @param value
	 *            value for which function must check if it is eligible for
	 *            computing with other number
	 * @return returns casted object ,depending on what type of object is sent
	 */
	private Number chekcIfComapatible(Object value) {

		if (value == null) {
			return Integer.valueOf(0);

		} else if (value instanceof String) {
			return extractString((String) value);

		} else if (value instanceof Double) {
			return (Double) value;

		} else if (value instanceof Integer) {
			return (Integer) value;

		} else
			throw new RuntimeException("unexpected error");

	}

	/**
	 * Public getter for the value.
	 * 
	 * @return returns the stored value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Public setter for stored value.
	 * 
	 * @param value
	 *            new value to be stored in this object
	 */
	public void setValue(Object value) {
		checkIfArithmetic(value);
		this.value = value;
	}

	/*
	 * public void printClass() { if (value == null) {
	 * System.out.println("**null stored"); return; }
	 * System.out.println(value.getClass()); }
	 */

	/**
	 * Interface which is used for writing of lambda expression which are then used
	 * for easier and more clear defining of task that each operation needs to do.
	 * 
	 * @author Mateo
	 *
	 */
	interface Transformer {
		/**
		 * Transform the object from send objects a and b ,and
		 * 
		 * @param a
		 * @param b
		 * @param x
		 * @return returns transformed number
		 */
		Number transform(Number a, Number b, boolean x);
	}

	/**
	 * Tests if current two numbers are Integers or not,so the operations knows what
	 * type the result is.
	 * 
	 * @param a
	 * @param b
	 * @return returs true if both elements are Inegers,false otherwise
	 */
	private static boolean test(Number a, Number b) {
		return (a instanceof Integer) && (b instanceof Integer);
	}

	/**
	 * Function first tests what type of number a and b are, to see what type of number result will be.
	 * Then calls transform function which calculates the solution , which is then returned in 
	 * apropriate type.
	 * @param a first argument
	 * @param b second argument
	 * @param k Transformer object which calculates a colution
	 * @return return a Number solution
	 */
	private Number calculate(Number a, Number b, Transformer k) {
		if (test(a, b))
			return (Integer) Integer.valueOf(k.transform(a, b, true).intValue());

		return (Double) Double.valueOf(k.transform(a, b, false).doubleValue());
	}

	/**
	 * Function adds two numbers ,and result is then saved to this object as current
	 * state. Function checks first if current objects are even eligible for
	 * addition, and then adds two objects.
	 * 
	 * @param incValue
	 *            number which we add to current number
	 */
	public void add(Object incValue) {
		Number storedNumber = chekcIfComapatible(this.value);
		Number argumentNumber = chekcIfComapatible(incValue);

		Transformer k = (a, b, x) -> x ? a.intValue() + b.intValue() : a.doubleValue() + b.doubleValue();

		this.value = calculate(storedNumber, argumentNumber, k);

	}

	/**
	 * Function subtracts two numbers ,and result is then saved to this object as
	 * current state. Function checks first if current objects are even eligible for
	 * subtraction, and then subtracts two objects.
	 * 
	 * @param decValue
	 *            number which we cubtract from current number stored
	 */
	public void subtract(Object decValue) {
		Number storedNumber = chekcIfComapatible(this.value);
		Number argumentNumber = chekcIfComapatible(decValue);

		Transformer k = (a, b, x) -> x ? a.intValue() - b.intValue() : a.doubleValue() - b.doubleValue();

		this.value = calculate(storedNumber, argumentNumber, k);

	}

	/**
	 * Function multiplies two numbers ,and result is then saved to this object as
	 * current state. Function checks first if current objects are even eligible for
	 * multiplying, and then multiplies two objects.
	 * 
	 * @param mulValue
	 *            number with which we multiply current number
	 */
	public void multiply(Object mulValue) {
		Number storedNumber = chekcIfComapatible(this.value);
		Number argumentNumber = chekcIfComapatible(mulValue);

		Transformer k = (a, b, x) -> x ? a.intValue() * b.intValue() : a.doubleValue() * b.doubleValue();

		this.value = calculate(storedNumber, argumentNumber, k);

	}

	/**
	 * Function divides two numbers ,and result is then saved to this object as
	 * current state. Function checks first if current objects are even eligible for
	 * dividing,then it checks if zero is passed, and then divides two objects.
	 * 
	 * @param divValue
	 *            number with which we divide current object
	 */
	public void divide(Object divValue) {
		Number storedNumber = chekcIfComapatible(this.value);
		Number argumentNumber = chekcIfComapatible(divValue);

		if (argumentNumber.doubleValue() < 1E-6)
			throw new RuntimeException("Must not divide with 0");

		Transformer k = (a, b, x) -> x ? a.intValue() / b.intValue() : a.doubleValue() / b.doubleValue();

		this.value = calculate(storedNumber, argumentNumber, k);

	}

	/**
	 * Function compares two objects ,and returns the following:
	 * 
	 * <ul>
	 * <li>if current object is same as arguent , funtion returns 0
	 * <li>if current object is arithmeticly smaller than argument ,it returns -1
	 * <li>if current object is arithmeticly bigger than the argument,it returns 1
	 * 
	 * </ul>
	 * 
	 * @param withValue
	 *            argument with which currenct object is comapred
	 * @return return result of comaprision
	 */
	public int numCompare(Object withValue) {

		Number storedNumber = chekcIfComapatible(this.value);
		Number argumentNumber = chekcIfComapatible(withValue);

		if (storedNumber instanceof Integer)
			return ((Integer) storedNumber).compareTo(argumentNumber.intValue());

		else
			return ((Double) storedNumber).compareTo(argumentNumber.doubleValue());

	}

}
