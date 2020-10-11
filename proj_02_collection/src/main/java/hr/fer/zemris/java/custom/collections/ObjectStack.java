package hr.fer.zemris.java.custom.collections;
/**
 * This is the adapter class. It hides the implementation in front of a user
 * and instead gives the user same class,but with edited functionalities.
 * In this class a stack is created using Collection implemented with arrays.
 * @author Mateo
 *
 */
public class ObjectStack {

	ArrayIndexedCollection secretCollection;
	
	public ObjectStack() {
		secretCollection=new ArrayIndexedCollection();
	}
	/**
	 * Function return true if the stack is empty, false otherwise.
	 * @return
	 */
	public boolean isEmpty() {
		return secretCollection.isEmpty();
	}
	/**
	 * Function returns size of the stack.
	 * @return
	 */
	public int size() {
		return secretCollection.size();
	}
	/**
	 * Function puts an Object on the end of a stack
	 * @param value
	 */
	public void push(Object value) {
		if(value==null)
			throw new NullPointerException("null as element tried to be added in Stack");
		secretCollection.add(value);
	}
	/**
	 * Function removes an objects from end of the stack,and returns it.
	 * @return
	 */
	public Object pop() {
		if(secretCollection.isEmpty())
			throw new EmptyStackException("tried to pop element from empty stack");
		Object temp=secretCollection.get(size()-1);
		secretCollection.remove(secretCollection.size()-1);
		return temp;
		
	}
	/**
	 * Function does not remove an element from the end, but just returns it.
	 * @return
	 */
	public Object peek() {
		if(secretCollection.isEmpty())
			throw new EmptyStackException("tried to pop element from empty stack");
		return secretCollection.get(size()-1);
	}
	/**
	 * Function clears the entire stack.
	 */
	public void clear() {
		secretCollection.clear();
	}
}
