package hr.fer.zemris.java.hw07.crypto;

import java.security.spec.AlgorithmParameterSpec;


import javax.crypto.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * This elementary encryption/decryption program is used for encryption/ decryption of files
 * using AES coding. Through command line,user specifies what action he wants,
 * and accordingly after that passes parameters for filepaths.
 * Program is also used to generate a digest string using SHA-256 algorithm.
 * Every file has a unique digest ,so program checks if a generated digest and 
 * string user typed in are the same.
 * @author Mateo
 *
 */

public class Crypto {
	/**
	 * buffer size for a reader object
	 */
	private static final int BUFFER_SIZE = 8;

	/**
	 * Function is used to check if digest generated from a file and a string user 
	 * input are the same. Digest must me a string of numbers 256 bit long. 
	 * If digests are not the same, appropriate message is printed to console line.
	 * @param input filepath location ,a file from which digest is calculated
	 * @throws IOException if there is problem with reading given file, exception is thrown
	 */
	private static void checksha(String input) throws IOException  {
		System.out.println("Please provide expected sha-256 digest for "+ input + ":");
		System.out.format("> ");
		MessageDigest messageDigest=null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			System.out.println("undifined error");
			e1.printStackTrace();
			System.exit(2);
		}

		try (InputStream is = new FileInputStream(input)) {
			byte[] buffer = new byte[BUFFER_SIZE];
			while (is.read(buffer) != -1) {

				messageDigest.update(buffer);
			}
		} 
		
		String result = Util.bytetohex(messageDigest.digest());
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Digesting completed. Digest of "+ input + 
		(reader.readLine().toUpperCase().equals(result) ?  "  matches expected digest." :
			(" does not match the expected digest. Digest \nwas: " + result)) );
		
		reader.close();
		
	}
	
	/**
	 * Funcion encrypts or decrypts given file using AES.
	 * Given input file is read ,and than given to  cipher object, who then 
	 * checks what is written in encrypt argument to check if it needs to encrypt or decrypt
	 * Function also requires form used to type in a password and initialization vector,
	 * which are both 16 bytes long .They must be typed in as hexadecimal values.
	 * @param in location of file being encrypted or decryped.
	 * @param out location and type of file where generated file will be saved.
	 * @param encrypt boolean value:
	 * <ul>
	 * <li>for encryption  -> true 
	 * <li>for decryption  -> false
	 * </ul>
	 * @throws Exception is thrown if there was problem with reading a file ,
	 * or creating new file
	 */
	private static void crypto(String in, String out ,boolean encrypt) throws Exception{
		
		System.out.println("Please provide password as hex-encoded text (16 bytes, i.e. 32 hex-digits):");
		System.out.format("> ");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String keyText = reader.readLine(); // wha user provided as password
		
		System.out.println("Please provide initialization vector as hex-encoded text (32 hex-digits):");
		System.out.format("> ");
		String ivText = reader.readLine();

		
		
		SecretKeySpec keySpec = new SecretKeySpec(Util.hextobyte(keyText), "AES");
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(Util.hextobyte(ivText));
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, paramSpec);
		
		
		
		byte[] result2 = null;
		try (InputStream is = new BufferedInputStream(new FileInputStream(in));) {
			
			result2 = cipher.doFinal(is.readAllBytes());
			
		}

		try (OutputStream o = new BufferedOutputStream(new FileOutputStream(out), 128)) {

			o.write(result2);
		}
		
		System.out.println((encrypt ? "Encryption" : "Decryption") + " completed."
				+ "\nGenerated file "+ in + " based on " + out);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)  {

		if (args.length < 2) {
			System.out.println("No enough arguments.");
			System.exit(1);
		}
		boolean encrypt = false;
		
		switch (args[0]) {
		case "checksha":
			try {
				checksha(args[1]);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case "encrypt":
			encrypt=true;
		case "decrypt":
			if (args.length < 3) {
				System.out.println("No enough arguments.");
				System.exit(1);
			}
			
			try {
				crypto(args[1], args[2],encrypt);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			System.out.println("Unspported command");
			System.exit(1);
		}

	

	}

}
