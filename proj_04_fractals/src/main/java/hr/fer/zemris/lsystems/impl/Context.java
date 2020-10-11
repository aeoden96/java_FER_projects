package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.java.custom.collections.ObjectStack;
import hr.fer.zemris.java.custom.collections.EmptyStackException;

/**
 * Primjerci ovog razreda omogućavaju izvođenje postupka prikazivanja fraktala
 * te imaju u sebi stog na koji je moguće stavljati i dohvaćati stanja kornjače
 * klase TurtleState. Može se vidjeti vrh stoga bez uklanjanja (peek), staviti
 * stanje na stog(push) ili maknuti stanje s vrha stoga(pop).
 * 
 * @author Mateo
 *
 */
public class Context {
	/**
	 * stog sadrži sva aktivna stanja kornjače prilikom izrade fraktala
	 */
	ObjectStack stog = new ObjectStack();

	/**
	 * Funkcija koja vraća stanje s vrha stoga bez uklanjanja.Ukoliko je stog prazan
	 * , baca se iznimka.
	 * 
	 * @return vraća se referenca na prvi element na stogu
	 * @throws EmptyStackException
	 *             ukoliko se pokuša pozvati funkcija nad praznim stogom
	 */
	public TurtleState getCurrentState() {
		if (stog.isEmpty())
			throw new EmptyStackException("nemogu pozvati getCurrentState,prazan stog");
		return (TurtleState) stog.peek();
	}

	/**
	 * Na vrh stoga se stavlja predano stanje
	 * 
	 * @param state
	 *            novo stanje koje će biti novi vrh stoga
	 */
	public void pushState(TurtleState state) {
		stog.push(state);
	}

	/**
	 * Miče element s vrha stoga. Ne vraća ništa ,pa ukoliko je potrebno ,prvo bi se
	 * trebala pozvati funkcija getCurrentState da se vidi što se miče sa stoga.
	 * 
	 * @throws EmptyStackException
	 *             baca iznimku ukoliko se pokuša pozvati pop nad praznim stogom
	 */
	public void popState() {
		if (stog.isEmpty())
			throw new EmptyStackException("cant popState,empty stack");

		stog.pop();

	}
}
