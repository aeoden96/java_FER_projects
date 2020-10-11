package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.Painter;
/**
 * Ovaj interface služi kako bi se definirao oblik naredbi koji se koriste u programu.
 * Definirana je samo jedna funkcija execute,unutra se izvršava sve potrebno kako 
 * bi naredba činila ono za što je definirana.
 * @author Mateo
 */
public interface Command {
	/**
	 * U funkciji execute svaka naredba ima svoj posebni zadatak.Preko konteksta uzima stanja kornjače
	 * koja su jos u toj trenutku potrebna(generalno se manipulira samo jednim stanjem) ,a onda
	 * pomoću paintera i fukcija može predati naredbu glavnom programu da iscrta nešto što 
	 * mu se preda preko painter.drawLine() .Funkcija to moze ,ali nemora koristiti.
	 * @param ctx kontekst stanja kornjača,oblikovan kao stog,zadnje stanje nalazi se na vrhu stoga
	 * @param painter ovaj objekt se prosljeđuje iz arhuve,sadrzži funkciju drawLine 
	 * koja se poziva ukoliko se javlja potreba za ispisom na ekran.
	 */
	void execute(Context ctx, Painter painter);
}
