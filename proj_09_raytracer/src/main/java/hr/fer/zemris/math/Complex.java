package hr.fer.zemris.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa za kompleksne brojeve. Svaki kompleksni broj se pamti pomoću dvije
 * varijable, posebno realni, posebno imaginarni dio. Klasa također sadrži
 * funkciju za parrsiranje, i par elementranih kompleksnih brojeva kao static
 * varijable.
 * 
 * @author Mateo
 *
 */
public class Complex {
	/**
	 * Statička varijabla koja čuva kompleksni broj za nulu.
	 */
	public static final Complex ZERO = new Complex(0, 0);
	/**
	 * Statička varijabla koja predstavlja 1.
	 */
	public static final Complex ONE = new Complex(1, 0);
	/**
	 * Stat.var koja prestavlja -1.
	 */
	public static final Complex ONE_NEG = new Complex(-1, 0);
	/**
	 * Stat.va. koja predstavlja i.
	 */
	public static final Complex IM = new Complex(0, 1);
	/**
	 * Stat.var. koja predstavlja -i
	 */
	public static final Complex IM_NEG = new Complex(0, -1);
	/**
	 * realni dio broja
	 */
	private final double re;
	/**
	 * imaginarni dio broja
	 */
	private final double im;

	/**
	 * Vraća realni dio broja.
	 * @return vraća realni dio
	 */
	public double getRe() {
		return re;
	}
	/**
	 * Vraća imaginarni dio broja
	 * @return vraća imaginarni dio
	 */
	public double getIm() {
		return im;
	}
	/**
	 * Defaultni konstruktor stavlja realni i imaginarni dio na nulu.
	 */
	public Complex() {
		re = 0;
		im = 0;
	}
	/**
	 * Konstruktor prima realni i imaginarani dio kroz argumente.
	 * @param re realni dio
	 * @param im imaginarni dio
	 */
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;

	}

	/**
	 * Vraća modul ovog kompleksnog broja 
	 * @return modul broja
	 */
	public double module() {
		return Math.sqrt(re * re + im * im);
	}

	/**
	 * Množi dva kompleksna broja.
	 * @param c drugi kompleksni broj
	 * @return rezultat množenja dva kompleksna broja
	 */
	public Complex multiply(Complex c) {
		return new Complex(re * c.re - im * c.im, re * c.im + c.re * im);
	}

	/**
	 * Vraća rezltat dijeljenja dva kompleksna broja.
	 * @param c drugi kompleksni broj
	 * @return rezultat dijeljenja ta 2 broja
	 */
	public Complex divide(Complex c) {
		if (c.im == 0 && c.re == 0) {
			throw new IllegalArgumentException("cant div with 0");
		}
		double div = (c.re * c.re) + (c.im * c.im);
		
		return  new Complex(((re * c.re) + (im * c.im)) / div, ((im * c.re) - (re * c.im)) / div);

	}

	/**
	 * Vraća zbroj 2 kompleksna broja. 
	 * @param c drugi kompleksni broj
	 * @return dobiveni zbroj
	 */
	public Complex add(Complex c) {
		return new Complex(re + c.re, im + c.im);
	}

	/**
	 * Vraća razliku dva kompleksna broja.
	 * @param c broj koji se oduzima prvom
	 * @return vraća razliku
	 */
	public Complex sub(Complex c) {
		return new Complex(re - c.re, im - c.im);
	}

	/**
	 * Vraća negiranu vrijednost kompleksnog broja.
	 * @return negiran kompleksni broj
	 */
	public Complex negate() {
		return new Complex(-re, -im);
	}

	/**
	 * Vraća kompleksni broj na potenciju.
	 * @param n potencija 
	 * @return vraća ntu potenciju kompleksnog broja
	 */
	public Complex power(int n) {
		if (n == 0)
			return ONE;

		Complex temp = new Complex(re, im);
		for (int i = 1; i < n; i++) {
			temp = temp.multiply(this);
		}
		return temp;
	}

	/**
	 * Funckija vraća nti korijen kompleksnog broja.
	 * 
	 * @param n
	 *            indeks
	 * @return vraća listu korijena
	 */
	public List<Complex> root(int n) {

		List<Complex> list = new ArrayList<>();

		if (re == 0 && im == 0) {
			list.add(ZERO);
			return list;
		}

		if (n <= 0) {
			throw new IllegalArgumentException();
		}

		double m = Math.pow(module(), 1.0 / n);
		double angle =0;

		for (int i = 0; i < n; i++) {
			angle = (2 * i * Math.PI + Math.atan2(im, re)) / n;
			list.add(new Complex(m * Math.cos(angle), Math.sin(angle) * m));
		}
		return list;
	}

	/**
	 * Funkcija parsira određeni broj . Svi su oblici podržani : 1, -1 , i , -i ,
	 * 1+i ,-1+i itd.
	 * 
	 * @param string
	 *            string koji sadrži kompleksni broj
	 * @return vraća novi kompleksni broj zapisan u danom stringu
	 */
	public static Complex parse(String string) {
		string=string.trim();
		if (string.indexOf('+') != -1 || string.substring(1).indexOf('-') != -1) {
			int index = string.substring(1).indexOf('-') != -1 ? string.substring(1).indexOf('-')+1
					: string.substring(1).indexOf('+')+1;

			double re = Double.parseDouble(string.substring(0, index));
			double im = Double.parseDouble(string.substring(index + 1, string.length() - 1));
			return new Complex(re, im);

		} else if (string.indexOf('i') != -1) {
			if (string.trim().length() == 1) {
				return IM;
			} else if (string.trim().length() == 2 && string.charAt(0) == '-') {
				return IM_NEG;
			}
			double im = Double.parseDouble(string.substring(0, string.length() - 1));
			return new Complex(0, im);
		} else {
			double re = Double.parseDouble(string);
			return new Complex(re, 0);
		}
	}

	/**
	 * Vraća kompleksni broj u obliku stringa.
	 */
	@Override
	public String toString() {
		return "(" + re + "," + im + ")";
	}
}
