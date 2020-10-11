package hr.fer.zemris.java.hw06.observer2;

import java.util.ArrayList;
import java.util.List;
/**
 * A Subject class in our design pattern, it saves the current state and keeps
 * all necessary data to notify observer whan this state needs to change.
 * @author Mateo
 *
 */
public class IntegerStorage {
	/**
	 * keeps the current state
	 */
	private int value;
	/**
	 * whenever some observer is added ore removed ,counter is changed
	 */
	private int counter=0;
	/**
	 * array of observers that get the information about changed state
	 */
	private List<IntegerStorageObserver> observers; // use ArrayList here!!!
	/**
	 * Constructor gets the initial value ,and stores it,and initialises array where
	 * it will keep all registered observers.
	 * 
	 * @param initialValue
	 *            initial value of current state
	 */
	public IntegerStorage(int initialValue) {
		this.value = initialValue;
		observers=new ArrayList<>();
	}
	/**
	 * Function adds the observer in observers if not already there.
	 * 
	 * @param observer
	 *            observer to be added to list of registered observers
	 */

	public void addObserver(IntegerStorageObserver observer) {
		// add the observer in observers if not already there ...
		counter++;
		observers.add(observer);
	}
	/**
	 * Function removes the observer from registered observers if present.
	 * @param observer observer to be removed
	 */
	public void removeObserver(IntegerStorageObserver observer) {
		counter++;
		// remove the observer from observers if present ...
		if(!observers.contains(observer))return;
		observers.remove(observer);
	}
	/**
	 * Clears entrire list of registered observers.
	 */
	public void clearObservers() {
		counter++;
		// remove all observers from observers list ...
		observers.clear();
	}
	/**
	 * Function gets the current state and returns it.
	 * @return current state stored in the object
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Function goes through the list of observers and notifies everyone that the value changed
	 * through valueChanged function.If for some reason, list of observers is altered in some way,
	 * the update of observers will break and rest of observers will not get notified.
	 * @param value value of the new state
	 */
	public void setValue(int value) {
		int curr=counter;
		// Only if new value is different than the current value:
		if (this.value != value) {
			// Update current value
			IntegerStorageChange st=new IntegerStorageChange(this, this.value, value);
			this.value = value;
			// Notify all registered observers
			if (observers != null) {
			
				for (IntegerStorageObserver observer : observers) {
					
					observer.valueChanged(st);
					if(curr!=counter) break;
				}
			}
		}
	}

}
