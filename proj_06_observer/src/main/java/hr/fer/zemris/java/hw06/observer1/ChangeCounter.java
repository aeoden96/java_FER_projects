package hr.fer.zemris.java.hw06.observer1;

/**
 * Instances of ChangeCounter counts (and writes to the standard output) the
 * number of times the value stored has been changed since the registration.
 * It implements IntegerStorageObserver interface.
 * 
 * @author Mateo
 *
 */
public class ChangeCounter implements IntegerStorageObserver{// concrete observer
	/**
	 * Counter saves value changes since it's first initialised.
	 */
	private int counter=0;
	
	/**
	 * Only function ih this class.It updaes the 
	 * counter variable and prints its value to screen.
	 * @param istorage IntegerStorage Object ,its not used here
	 */
	public void valueChanged(IntegerStorage istorage) {
		counter++;
		System.out.println("Number of value changes since tracking: "+counter);

	}
}
