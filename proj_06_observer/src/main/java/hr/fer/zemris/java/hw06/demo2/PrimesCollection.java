package hr.fer.zemris.java.hw06.demo2;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class PrimesCollection is a generator of primes.It generates first n primes in a set of whole numbers.
 * Class doesn't use any form of fields or lists,so it may not be too efficient ,and its complexity
 * it poor (n). Class also implements Iterable<> so an iterator can be created to loop through
 * prime numbers from 2 to n (as mentioned, while iterating,primes are calculated in real time)
 * @author Mateo
 *
 */
public class PrimesCollection implements Iterable<Integer> {
	/**
	 * keeps the number of primes user wants to create using this class
	 */
	private int howMany;

	/**
	 * Iterator class necessary for iterating through instance of this class. It calculates 
	 * new prime in every step,and keeps the counter of numbers to be passed by iterator.
	 * It also keeps last known prime,so the calculation can resume form that point.
	 * @author Mateo
	 *
	 */
	private class IteratorImpl implements Iterator<Integer> {
		/**
		 * saves the last known prime
		 */
		int last = 1;
		/**
		 * saves the position of iterator 
		 */
		int counter = 0;

		/**
		 * Checks if the iterator has come to the last prime user wanted,if it has,
		 * it returns false,otherwise true
		 */
		@Override
		public boolean hasNext() {
			if (counter >= howMany)
				return false;
			return true;
		}
		
		/**
		 * Function calculates if sent number is a prime or not.
		 * Note:a number divisable by 2 (except for number 2) is never a prime,
		 * Note2:if number n is not divisable by numbers 2 to sqrt(n), then it's definetly a prime
		 * @param x number that we are checking if its a prime or not
		 * @return true if it is a prime, false othrwise
		 */
		private boolean prime(int x) {
			if (x % 2 == 0)
				return false;
			double sqr = Math.sqrt(x);
			for (int i = 3; i <= sqr; i+=2) {
				if (x % i == 0)
					return false;

			}
			return true;
		}
		/**
		 * Function returns new prime. If this function is called , but it exceeds number of 
		 * primes user wants, it throws an exception.
		 * @return returns next prime 
		 * @throws NoSuchElementException if limit for number of primes is reached
		 */
		@Override
		public Integer next() {
			if(!hasNext())
				throw new NoSuchElementException("nema vise elemenata");
			if (last == 1) {
				last=2;
				counter++;
				return last ;
			}
			int next=last;	
			while (true) {
				next++;
				if (!prime(next))continue;
				counter++;
				last=next;
				return last;

			}
		}

	}

	/**
	 * Function returns a new iterator object.
	 */
	@Override
	public Iterator<Integer> iterator() {
		return new IteratorImpl();
	}
	/**
	 * Constructor takes in number of primes that must be created using this class,and saves that
	 * number in howMany 
	 * @param howMany number of wanted primes
	 * @throws IllegalArgumentException if number entered is less than 0
	 * 
	 */
	public PrimesCollection(int howMany) {

		if(howMany<0)
			throw new IllegalArgumentException("not valid argument for number of primes");
		this.howMany = howMany;
	}

}
