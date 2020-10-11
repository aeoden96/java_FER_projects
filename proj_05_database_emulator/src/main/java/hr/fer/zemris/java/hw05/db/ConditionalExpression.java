package hr.fer.zemris.java.hw05.db;

/**
 * Klasa čuva trojke IFieldValueGetter ,IComparisonOperator i String.Klasa
 * sadrži gettere za svaki od njih.IFieldValueGetter govori o kojoj se 
 * informaciji o studentu radi, String je dana vrijednost s kojom uspoređujemo
 * sve studente ,a IComparisonOperator je operator pomoću kojeg ćemo odlučiti 
 * koji studenti su traženi ,a koji nisu.
 * 
 * @author Mateo
 *
 */
public class ConditionalExpression {
	/** Vrsta informacije o studentu (ime ,prezime ili jmbag) */
	IFieldValueGetter fieldStrategy; 
	/**  Vrsta operatora s kojim uspodeđujemo. */
	IComparisonOperator compStrategy;
	/**   String kojeg uspodeđujemo s bazom studenata.  */
	String s;
	/**
	 * Jedini konstruktor ,prima defaultne vrijednosti koje se nemogu promijeniti.
	 * 
	 * @param fieldStrategy Vrsta informacije o studentu (ime ,prezime ili jmbag)
	 * @param s  String kojeg uspodeđujemo s bazom studenata.
	 * @param compStrategy Vrsta operatora s kojim uspodeđujemo.
	 */
	public ConditionalExpression(IFieldValueGetter fieldStrategy, String s, IComparisonOperator compStrategy) {
		super();
		this.fieldStrategy = fieldStrategy;
		this.compStrategy = compStrategy;
		this.s = s;
	}
	/**
	 * Getter za IFieldValueGetter.
	 * @return vraća FIRST_NAME,LAST_NAME ili JMBAG
	 */
	public IFieldValueGetter getFieldGetter() {
		return fieldStrategy;
	}
	/**
	 * Getter za IComparisonOperator.
	 * @return Vraća reprezentaiju nekog od operatora.
	 */
	public IComparisonOperator getComparisonOperator() {
		return compStrategy;
	}
	/**
	 * Getter za string kojim uspoređujemo spise.
	 * @return vraća string koji je korisnik upisao query naredbom
	 */
	public String getStringLiteral() {
		return s;
	}

}
