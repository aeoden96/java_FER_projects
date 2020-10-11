package hr.fer.zemris.java.hw06.observer1;

/**
 * Instances of SquareValue class write a square of the integer stored in the
 * IntegerStorage to the standard output (but the stored integer itself is not
 * modified)
 * 
 * @author Mateo
 *
 */
public class SquareValue implements IntegerStorageObserver{// concrete observer
	
	/**
	 * Function prints out square value of value stored in our Subject,id does not 
	 * modify it in any way
	 * @param istorage its square value is printed to console output
	 */
	public void valueChanged(IntegerStorage istorage) {
		System.out.println("Square is "+ istorage.getValue()*istorage.getValue());
	}
}
