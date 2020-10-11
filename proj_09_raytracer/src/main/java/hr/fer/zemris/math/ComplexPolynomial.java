package hr.fer.zemris.math;

/**
 * Polinom zapisan u standardnom obliku s faktorima ,tj. f(x)=a<sub>n</sub>x<sup>n</sup>+ ... +  
 * a<sub>2</sub>x<sup>2</sup>+a<sub>1</sub>x<sup>1</sup>+a<sub>0</sub>.
 * Fatori se zadaju kroz konstruktor, od većeg prema manjem,i nule se moraju zapisati.
 * @author Mateo
 *
 */
public class ComplexPolynomial {
	/**
	 * polje koje čuva faktore polinoma
	 */
	private final Complex[] factors;

	/**
	 * Konstruktor prima polje faktora
	 * @param factors
	 */
	public ComplexPolynomial(Complex... factors) {

		this.factors = factors;
	}

	// returns order of this polynom; eg. For (7+2i)z^3+2z^2+5z+1 returns 3
	/**
	 * Vraća red polinoma ,npr za :(7+2i)z<sup>3</sup>+2z<sup>2</sup>+5z+1  vraća 3
	 * @return vraća red polinoma
	 */
	public short order() {
		return (short) (factors.length - 1);
	}

	
	/**
	 * Vraća umnožak polinoma.Ne mijenja sadašnji,nego se vraća instanca novog.
	 * @param p drugi polinom
	 * @return vraća se umnožak polinoma
	 */
	public ComplexPolynomial multiply(ComplexPolynomial p) {
		int totalLength = factors.length + p.factors.length - 1;
		
		Complex[] result = new Complex[totalLength];

		for (int i = 0; i < totalLength; i++) {
			result[i] = Complex.ZERO;
		}


		for (int i = factors.length - 1; i >= 0; i--) {
			for (int j = p.factors.length - 1; j >= 0; j--) {
				result[i + j] = result[i + j].add(factors[i].multiply(p.factors[j]));
			}
		}
			
		return new ComplexPolynomial(result);

	}


	/**
	 * Vraća derivaciju polinoma kao novi polinom.Ne mijenja sadašnji.
	 * Npr. za: (7+2i)z<sup>3</sup>+2z<sup>2</sup>+5z+1 vraća (21+6i)z<sup>2</sup>+4z+5
	 * @return vraća derivaciju polinoma
	 */
	public ComplexPolynomial derive() {
		Complex[] result = new Complex[factors.length-1];

		for (int i = 0; i < factors.length-1; i++) {
			result[i] = factors[i].multiply(new Complex(factors.length-1-i,0));
		}
		
		return new ComplexPolynomial(result);

	}

	/**
	 * Vraća vrijednost polinoma u točki z.
	 * @param z točka u kojoj se traži f(z)
	 * @return vraća traženi f(z)
	 */
	public Complex apply(Complex z) {
		Complex result = Complex.ZERO;

		for (int i = 0; i < factors.length; i++) {
			result = result.add(z.power(factors.length-1-i).multiply(factors[i]));
		}
		
		return result;
		

	}
	/**
	 * Vraća String sa zapisom ovog polinoma.
	 */
	@Override
	public String toString() {
		String result = "";
		for (Complex i : factors) {
			result = result + i.toString() + " ";
		}
		return result;
	}
}
