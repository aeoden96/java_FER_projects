package hr.fer.zemris.java.hw02;

import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

import static java.lang.Math.atan2;;

/**
 * Class is used to store complex numbers for easier use. It has only one
 * constructor.
 * 
 * @author Mateo
 *
 */
public class ComplexNumber {

	double real;
	double imaginary;

	ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;

	}

	/**
	 * Function creates complex number just form real part,and returns it.
	 * 
	 * @param real
	 * @return
	 */
	public static ComplexNumber fromReal(double real) {
		return new ComplexNumber(real, 0);
	}

	/**
	 * Function creates complex number just form imaginary part,and returns it.
	 * 
	 * @param imaginary
	 * @return
	 */
	public static ComplexNumber fromImaginary(double imaginary) {
		return new ComplexNumber(0, imaginary);
	}

	/**
	 * Function uses magnitude nad angle to calculate real and imaginary part of a
	 * complex number, than returns the result
	 * 
	 * @param magnitude
	 * @param angle
	 * @return
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
		return new ComplexNumber(magnitude * cos(angle), magnitude * sin(angle));
	}

	/**
	 * Function reads from string and then parses the elements into appropriate real
	 * and imaginary part
	 * 
	 * @param s
	 * @return
	 */
	public static ComplexNumber parse(String s) {
		int length = s.length();
		if (s.indexOf("+", 1) == 0 && s.indexOf("-", 1) == 0) {
			if (s.indexOf("i") == 0)
				return new ComplexNumber((Double.parseDouble(s)), 0);
			else
				return new ComplexNumber(0, (Double.parseDouble(s)));
		} else {
			int index = s.indexOf("+", 1) + s.indexOf("-", 1);
			double real = Double.parseDouble(s.substring(0, index + 1));
			double imag = Double.parseDouble(s.substring(index + 1, length - 1));
			return new ComplexNumber(real, imag);
		}

	}

	/**
	 * Funnction returns real part of a complex number.
	 * 
	 * @return
	 */
	public double getReal() {
		return real;
	}

	/**
	 * Function returns imaginary part of a complex number.
	 * 
	 * @return
	 */
	public double getImaginary() {
		return imaginary;
	}

	/**
	 * Function return an angle of a complex number;
	 * 
	 * @return
	 */
	public double getAngle() {
		return atan2(real, imaginary);
	}

	/**
	 * Function return a Magnitude of a complex number
	 * 
	 * @return
	 */
	public double getMagnitude() {
		return sqrt(real * real + imaginary * imaginary);
	}

	/**
	 * Function adds two complex numbers ,then returns the result.
	 * 
	 * @param c
	 * @return
	 */
	public ComplexNumber add(ComplexNumber c) {
		return new ComplexNumber(c.getReal() + real, c.getImaginary() + imaginary);
	}

	/**
	 * Function subtracts two complex numbers ,then returns the result.
	 * 
	 * @param c
	 * @return
	 */
	public ComplexNumber sub(ComplexNumber c) {
		return new ComplexNumber(real - c.getReal(), imaginary - c.getImaginary());
	}
	/**
	 * Function divides two complex numbers then returns the result.
	 * @param c
	 * @return
	 */
	public ComplexNumber div(ComplexNumber c) {

		double a = (real * c.getReal() + c.getImaginary() * imaginary)
				/ (c.getReal() * c.getReal() + c.getImaginary() * c.getImaginary());
		double b = (imaginary * c.getReal() - real * c.getImaginary())
				/ (c.getReal() * c.getReal() + c.getImaginary() * c.getImaginary());
		return new ComplexNumber(a, b);
	}
	/**
	 * Function multiplies two complex numbers then returns the result.
	 * @param c
	 * @return
	 */
	public ComplexNumber mul(ComplexNumber c) {
		return new ComplexNumber(c.getReal() * real - c.getImaginary() * imaginary,
				real * c.getImaginary() + imaginary * c.getReal());
	}
	/**
	 * Function calculates complex number to the power n and returns the result.
	 * @param n
	 * @return
	 */
	public ComplexNumber power(int n) {
		if (n < 0)
			throw new IllegalArgumentException("power exponent must be non negative number, you entered '" + n + "'");
		return new ComplexNumber((real * real) - (imaginary * imaginary), 2 * real * imaginary);
	}
	
	/**
	 * Function calculates roots of a complex number ,and returns array with results.
	 * @param n
	 * @return
	 */
	public ComplexNumber[] root(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("root exponent must be positive number, you entered '" + n + "'");

		ComplexNumber[] temp = new ComplexNumber[2];

		return temp;
	}
	/**
	 * Function for transforming real and imaginary values to string,and returns that string.
	 */
	public String toString() {
		if (imaginary == 0)
			return "" + real;
		else if (imaginary < 0)
			return real + "" + imaginary + "i";
		else
			return real + "+" + imaginary + "i";
	}
}
