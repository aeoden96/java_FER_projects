package hr.fer.zemris.java.hw06.demo2;

/**
 * Class tests class PrimesCollection and prints to console every element of
 * Cartesian product P(n)xP(n) ,where P(n) represents a set of primes up to n.
 * It uses and implemented interator for that.
 * 
 * @author Mateo
 *
 */
public class PrimesDemo2 {
	/**
	 * Class tests class PrimesCollection and prints to console every element of
	 * Cartesian product.
	 * 
	 * @param args it's not used here
	 */
	public static void main(String[] args) {
		PrimesCollection primesCollection = new PrimesCollection(2);
		for (Integer prime : primesCollection) {
			for (Integer prime2 : primesCollection) {
				System.out.println("Got prime pair: " + prime + ", " + prime2);
			}
		}
	}
}
