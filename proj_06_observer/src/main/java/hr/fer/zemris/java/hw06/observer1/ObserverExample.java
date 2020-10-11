package hr.fer.zemris.java.hw06.observer1;
/**
 * Class that tests our strategy.It creates aSubject ,to which are then added couple of observers,
 * and as values are modified,observers do some specific action. 
 * @author Mateo
 *
 */
public class ObserverExample {
	/**
	 * Main function. It tests the strategy by making a subject and the couple of Observer objects 
	 * @param args ist not used here
	 */
	public static void main(String[] args) {
		IntegerStorage istorage = new IntegerStorage(20);
		istorage.addObserver(new ChangeCounter());
		istorage.addObserver(new DoubleValue(2));
		istorage.addObserver( new SquareValue());


		istorage.setValue(5);

		istorage.setValue(2);


		istorage.setValue(25);


		istorage.setValue(1);


		istorage.setValue(2);


		
				
		
		
		}

}
