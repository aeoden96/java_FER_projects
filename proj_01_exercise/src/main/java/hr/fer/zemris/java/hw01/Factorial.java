package hr.fer.zemris.java.hw01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Program računa faktorijel broja. Ograničen je na pozitivne brojeve do 20.
 * Ispisuje na rješenje, za kraj potrebno upisati 'kraj'.
 * 
 * @author Mateo Martinjak
 * @vesion 1.0
 */
public class Factorial {
	/**
	 * Funkcija računa faktorijel broja
	 * 
	 * @param broj
	 *            broj od kojeg se traži faktorijel tipa short
	 * @return vraća faktorijel broja tipa long
	 */
	public static long getFactorial(short broj) {
		long fakt = 1;
		
		for (int i = 1; i <= broj; i++) {
			fakt = fakt * i;
		}
		return fakt;
	}
	/**
	 * Glavna main funkcija, čita upis brojeva s tipkovnice
	 * @param args args je prazan,funkcija ga ne provjerava
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Program za računanje faktorijela.");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.format("Unesite broj > ");
			String redak = reader.readLine();
			
			if (redak == null)
				break;
			if (redak.equals("kraj")) {
				System.out.println("Doviđenja.");
				break;
			}

			short broj = 0;
			
			try {
				broj = Short.parseShort(redak);
			} catch (NumberFormatException ex) {
				System.out.println("'" + redak + "' nije cijeli broj.");
				continue;
			}
			if (broj < 0 || broj > 20) {
				System.out.println("'" + broj + "' nije u dozvoljenom rasponu.");
				continue;
			}

			long fakt = getFactorial(broj);

			System.out.println(broj + "! = " + fakt);

		}
		reader.close();
	}

}
