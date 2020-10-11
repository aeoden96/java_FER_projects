package hr.fer.zemris.java.custom.collections;
/**
 * This is the adapter class. It hides the implementation in front of a user
 * and instead gives the user same class,but with edited functionalities.
 * In this class a stack is created using Collection implemented with arrays.
 * @author Mateo
 *
 */
public class ObjectStack {
	/**
	 * uses a simple collection for saving objects
	 */
	ArrayIndexedCollection secretCollection;
	/**
	 * constructor initialises new stack
	 */
	public ObjectStack() {
		secretCollection=new ArrayIndexedCollection();
	}
	/**
	 * Function return true if the stack is empty, false otherwise.
	 * @return return true if its empty,false otherwise
	 */
	public boolean isEmpty() {
		return secretCollection.isEmpty();
	}
	/**
	 * Function returns size of the stack.
	 * @return returns size of current stack
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
	 * @return pops the top of the stack and returns it
	 */
	public Object pop() {
		if(secretCollection.isEmpty())
			throw new EmptyStackException("tried to pop element from empty stack");
		Object temp = peek();
		secretCollection.remove(secretCollection.size()-1);
		return temp;
		
	}
	/**
	 * Function does not remove an element from the end, but just returns it.
	 * @return return top of the stack,stack stays unchanged
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
