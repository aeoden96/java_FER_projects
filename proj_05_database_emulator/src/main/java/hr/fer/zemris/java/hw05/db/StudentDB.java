package hr.fer.zemris.java.hw05.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *Sadrži glavni main i program za upis upita korisnika. Kada korisnik želi završiti s radom ,
 *upisuje "exit". Klasa se brine za parsiranje upita korisnika i ispis na ekran.
 * 
 * @author Mateo
 *
 */
public class StudentDB {
	/**
	 * Listu studenata ispisuje formatirano u konzolu.
	 * @param lista lista prihvaćenih spisa studenata
	 */
	public static void ispis(List<StudentRecord> lista) {
		int maxPrez = 0, maxIme = 0;
		for (StudentRecord i : lista) {
			if (i.getLastName().length() > maxPrez)
				maxPrez = i.getLastName().length();
			if (i.getFirstName().length() > maxIme)
				maxIme = i.getFirstName().length();
		}

		System.out.println("+============+======================+======================+===+");

		for (StudentRecord i : lista) {

			System.out.format("| %s | %20s | %20s | %.0f |\n",i.getJmbag(), i.getLastName(), i.getFirstName(), i.getFinalGrade()   );
		}
		System.out.println("+============+======================+======================+===+");
	}
	/**
	 * Glavni main program. Odavne se inicira baza podataka.
	 * @param args ne koristi se 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		

		StudentDatabase db = new StudentDatabase();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.format(">");
			String redak;
			redak = reader.readLine();
			if (redak == null) {
				System.out.println("Goodbye!");
				System.exit(1);
			}
			if (redak.equals("kraj")) {

				break;
			}
			

			if(!redak.substring(0,5).equals("query")) {
				System.out.println("unsupported command");
				continue;
			}
			QueryParser parser = new QueryParser(redak.substring(6));
			List<StudentRecord> lista = new ArrayList<>();
			if (parser.isDirectQuery()) {

				StudentRecord r = db.forJMBAG(parser.getQueriedJMBAG());
				lista.add(r);
				ispis(lista);
				System.out.println("Records selected: "+ lista.size());


			} else {

				for (StudentRecord r : db.filter(new QueryFilter(parser.getQuery()))) {

					lista.add(r);

				}
				ispis(lista);
				System.out.println("Records selected: "+ lista.size());
			}

		}
		reader.close();

	}
}
