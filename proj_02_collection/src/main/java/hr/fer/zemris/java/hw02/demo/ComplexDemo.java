package hr.fer.zemris.java.hw02.demo;

import hr.fer.zemris.java.hw02.ComplexNumber;

public class ComplexDemo {
	public static void main(String[] args) {
		
		ComplexNumber c2 = ComplexNumber.parse("2.532+3i");
		System.out.println(c2);
		ComplexNumber c3 = c2.add(ComplexNumber.fromMagnitudeAndAngle(2, 1.57))
				.div(c2);
				System.out.println(c3);
	
	}
}
