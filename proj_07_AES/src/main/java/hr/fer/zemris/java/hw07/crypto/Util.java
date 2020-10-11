package hr.fer.zemris.java.hw07.crypto;

/**
 * Small class that contains two important funtions for transforming hexadecimal
 * values (in a string) to byte array , or other way around.
 * 
 * @author Mateo
 *
 */
public class Util {
	/**
	 * Function takes is a hexadecimal number written in string ,and generates a
	 * byte array from that .
	 * 
	 * @param hex given string
	 * @return returns a byte array with byte representation of values from a given string
	 */
	public static byte[] hextobyte(String hex) {
		
		
		int length = hex.length();
		if(length % 2 != 0) {
			throw new IllegalArgumentException("Value must be dividable by 2");
		}
		byte[] data = new byte[length / 2];
		for (int i = 0; i < length; i += 2) {
			data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16)) * 16
					+ Character.digit(hex.charAt(i + 1), 16));
		}
		return data;
	}
	/**
	 * Function takes is a byte array ,and returns a hex string representing values in that array.
	 * Letters in this string will all be capital.
	 * @param bytearray given array of bytes 
	 * @return string with written hexadecimal values
	 */
	public static String bytetohex(byte[] bytearray) {

		String s = "";
		for (byte b : bytearray) {
			s=s + String.format("%02X", b);
		}
		return s;

	}
}
