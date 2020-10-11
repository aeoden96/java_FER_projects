package hr.fer.zemris.java.hw01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Program uzima širinu i visinu kvadrata
 * iz komandne linije ili preko tipkovnice,
 * i računa i ispisuje opseg i površinu pravokutnika.
 * @author Mateo Martinjak
 * @version 1.0
 */
public class Rectangle {
	
	/**
	 * Funkcija računa i ispisuje opseg pravokutnika.
	 * @param width dobivena širina pravokutnika
	 * @param height dobivena visina pravokutnika
	 */
	public static float getPerimeter(float width,float height) {
		return 2*width+2*height;
	}

	/**
	 * printRectangle Formatirano ispisuje i računa opseg i površinu pravokutnika.
	 * Ne vraća ništa.
	 * @param width width je širina pravokutnika, prvi argument 
	 * @param height height je visina pravokutnika ,drugi argument
	 */
	public static void printRectangle(float width,float height) {
		System.out.format("Pravokutnik širine %.1f i visine %.1f ima opseg %.1f i površinu %.1f",
				 width,height,getPerimeter(width,height),width*height);
		return;
	}
	/**
	 * Glavna main funkcija programa,obrađuje ulaz i uzima 
	 * 2 validna argumenta za širinu i visinu pravokutnika.
	 * Broj argumenata komandne linije mora biti jednak 0 ili 2.
	 * 
	 * @param args args sadrži 2 parametra, širinu i visinu pravokutnika,
	 * ali može i biti prazan
	 * @throws IOException Nužno ukoliko se koristi BufferedReader za obradu ulaza.
	 */
	public static void main(String[] args) throws IOException {
		System.out.println( "Program za računanje opsega i povrsine pravokutnika." );
		 
		float width=0;
		float height=0;
		
		if(args.length==2) {
			try {
				 width=Float.parseFloat(args[0]);
				 height=Float.parseFloat(args[1]);
				 printRectangle(width,height); 
			} 
			catch(NumberFormatException ex) {
				System.out.println("Unijeli ste krive argumente.");
				System.exit(3);
			}
		}
		
			 
		else if(args.length==0) {
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			boolean haveFirstArg=false;
			while(true) {
				float broj;
				String redak;
				
				System.out.format("Unesite %s > ",haveFirstArg ? "visinu" : "širinu");
				redak=reader.readLine();
				if(redak==null) {
					System.exit(1);
				}
				try {
					broj=Float.parseFloat(redak);
				}
				catch(NumberFormatException ex) {
					System.out.println("'" + redak + "' se ne može protumačiti kao broj.");
					continue;
				}
							
				if(broj<0) {
					System.out.println("Unijeli ste negativnu vrijednost.");
					continue;
				}
				if(!haveFirstArg) {
					width=broj;
					haveFirstArg=true;
					continue;
				}
				else {
					height=broj;
					printRectangle(width,height); 
					break;
				}
			}
			reader.close();
		}
		else {
			System.out.println("Unijeli ste krivi broj argumenata");
			System.exit(2);
		}
	}
}
