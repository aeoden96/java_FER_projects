package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.ObjectStack;
/**
 * Class StackDemo uses a stack from ObjectStack class to calculate an postfix expression.
 * The expression is passed through command line as one string argument , 
 * the expression needs to be in quotation marks ,for example "6 -2 * 3 /" 
 * Program only computes with integers, so no float numbers allowed. Result is also expressed as 
 * integer.
 * @author Mateo Martinjak
 *
 */
public class StackDemo {
	
	/**
	 * Main function. It takes the expression and passes it to calculatePrefix fn.
	 * It prints the result.
	 * @param args Postfix expression (string) which is taken from command line.
	 */
	public static void main(String[] args) {
		if(args.length==0) {
			System.out.println("No proper command line argument");
			System.exit(1);
		}
		int solution = calculatePostfix(args[0]);
		System.out.println(solution);
	}

	/**
	 * Function calclates postfix expression and returns integer value 
	 *
	 * @param string
	 * @return returns the result od postfix expression           
	 */
	public static int calculatePostfix(String string) {

		char[] chars = string.toCharArray();
		ObjectStack s=  new ObjectStack();
		int length = chars.length;
		int sign = 1;
		
		for (int i = 0; i < length; i++) {

			if (chars[i] == '+' || chars[i] == '*' || chars[i] == '/' ||  chars[i] == '%') {

				switch (chars[i]) {
				case '+':
					s.push((Integer)s.pop()+(Integer)s.pop());
					break;
				case '*':
					s.push((Integer)s.pop() * (Integer)s.pop());
					break;

				case '/':
					Integer a=(Integer)s.pop();
					Integer b=(Integer)s.pop();
					s.push(b/a);
					break;
				case '%':
					s.push((Integer)s.pop() % (Integer)s.pop());
					break;

				}

			} else if (chars[i] == '-') {
				if (i == length - 1) {
					int a=(Integer)s.pop();
					int b=(Integer)s.pop();
					s.push(b-a);
				} else if (Character.isDigit(chars[i + 1])) {
					sign = -1;
				} else {
					s.push(-(Integer)s.pop() + (Integer)s.pop());
				}
			}

			else if (Character.isDigit(chars[i])) {
				s.push(0);
				while (Character.isDigit(chars[i])) {
					s.push((chars[i] - '0')+10 * (Integer)s.pop());
					++i;
				}
				s.push(sign * (Integer)s.pop());
				sign = 1;
			}

		}

		if (s.isEmpty()) {
			System.out.println("Error, stack is empty.");
		}
		return (Integer)s.pop();
		
			

	}



}
