package hr.fer.zemris.java.hw06.demo2;
/**
 * Class tests class PrimesCollection and prints to console first n prime numbers.
 * It uses and implemented interator for that.
 * @author Mateo
 *
 */
public class PrimesDemo1 {
	/**
	 * This main function creates an istance of PrimesCollection and uses its iterator 
	 * to go through prime numbers and prints then to console.
	 * @param args it's not used here
	 */
	public static void main(String[] args) {
		PrimesCollection primesCollection = new PrimesCollection(5); // 5: how many of them
		for(Integer prime : primesCollection) {
		System.out.println("Got prime: "+prime);
		}
	}
}
