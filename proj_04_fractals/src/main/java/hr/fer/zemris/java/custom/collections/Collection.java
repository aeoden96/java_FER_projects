package hr.fer.zemris.java.custom.collections;


/**
 * A blueprint for two types of collections.It has several functions for manipulating 
 * with collection of objects. By itself it has no purpose, it serves just as bluprint 
 * for ArrayIndexedCollection and LinkedListIndexedCollecton.
 * @author Mateo
 *
 */
public class Collection {
	
	/**
	 * Default contructor. It has no purpose.
	 */
	protected Collection() {


	}
	
	/**
	 * Simple function,used to check if collection is empty or not.
	 * 
	 * @return true if collection contains no objects and false otherwise
	 */
	public boolean isEmpty() {
		if(size()==0) return true;
		return false;
	}
	
	/**
	 * Returns the number of currently stored objects in this collections. 
	 * Here is implemented so it always returns 0
	 * 
	 * @return number of currently stored objects in this collection
	 */
	public int size() {
		return 0;
		
	}
	
	/**
	 * Adds the given object into this collection. 
	 * Implement it here to do nothing.
	 * @param value adds the Object 'value' in this collection
	 */
	public void add(Object value) {
		
	}
	
	/**
	 * Returns true only if the collection contains given value.
	 * @param value value to be checked
	 * @return Returns true only if the collection contains given value
	 */
	public boolean contains(Object value) {
		
		return false;
	}
	
	/**
	 * Returns true only if the collection contains given value as determined 
	 * by equals method and removes one occurrence of it.  
	 * 
	 * @param value
	 * @return  Returns true only if the collection contains given value as determined 
	 */
	public boolean remove(Object value) {
		return false;
	}

	/**
	 * Allocates new array with size equals to the size of this collections, 
	 * fills it with collection content and returns the array. 
	 * 
	 * @return array to be returned
	 */
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Method calls processor.process(.) for each element of this collection. 
	 * The order in which elements will be sent is undefined in this class. 
	 * here implemented as empty function
	 * @param processor
	 */
	public void forEach(Processor processor) {
		
		
	}
	
	/**
	 * Method adds into the current collection all elements from the given collection. 
	 * This other collection remains unchanged. 
	 *  
	 * @param other
	 */
	public void addAll(Collection other) {
		/**
		 * Local class processor is used with forEach to add all elements much easier
		 * @author Mateo
		 *
		 */
		 class processor extends Processor{
			@Override
			 /**
			  * Help function. Just used to pass value to add function.
			  */
			public void process(Object value) {
				//will add each item into the 
				 // current collection by calling method add
				add(value);
				
			}
		}
		// calls forEach on the other collection with this processor as argument. 
		 processor P= new processor();
		other.forEach(P);		
	}
	
	/**
	 * Removes all elements from this collection. 
	 * implemented as empty function
	 */
	public void clear() {
		
	}
	
}
