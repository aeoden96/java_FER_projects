package hr.fer.zemris.java.hw06.observer1;

/**
 * Instances of DoubleValue class write to the standard output double value
 * of the current value which is stored in subject, but only
 * first n times since its registration with the subject (n is given in
 * constructor) After writing the double value for the n-th time, the
 * observer automatically de-registers itself from the subject.
 * It consists of a constructor and a function valueChanged.
 * 
 * @author Mateo
 *
 */
public class DoubleValue implements IntegerStorageObserver {// concrete observer
	/**
	 * saves number of iterations after which this observer should de-register itself
	 */
	private int counter;
	/**
	 * Constructor takes in number of iterations after which observer will deregister itself
	 * @param counter number of iterations wanted
	 */
	public DoubleValue(int counter) {
		super();
		this.counter = counter;
	}
	/**
	 * Function takes in a counter variable and it gets a current value stored in IntegerStorage
	 * object.Than print out double value,while not modifying current one.
	 * On n+1th iteration, observer de-registers itself form IntegerStorage.
	 * @param istorge IntegerStorage object that stores current value that we must print out
	 */
	@Override
	public void valueChanged(IntegerStorage istorage) {
		
		if (counter <= 0) {
			istorage.removeObserver(this);
			return;
		}
		
		System.out.println("Double value: "+istorage.getValue()*2);
		

		counter--;
	}
}
