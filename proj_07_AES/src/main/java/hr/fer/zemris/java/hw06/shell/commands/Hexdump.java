package hr.fer.zemris.java.hw06.shell.commands;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
import hr.fer.zemris.java.hw07.crypto.Util;

/**
 * Command prints to defined output a hexadecimal table of given file. In every
 * line of output, 16 bytes of a file are shown ,and on the right ther
 * representation in ASCII is shown too.
 * 
 * @author Mateo
 *
 */
public class Hexdump implements ShellCommand {
	/**
	 * tells input how much bytes to take in each step/line
	 */
	private static final int BUFFER_SIZE = 16;
	/**
	 * counts lines
	 */
	private int LINE = 0;

	/**
	 * Main command which is executed from the shell. Environment object is passed
	 * so that current command can comunicate to the user through it. Command will
	 * print to output hexadecimal representation of a file, and a ASCII vesion on
	 * th right. In each line, 16 bytes of a file are shown.
	 * 
	 * @param env
	 *            Environmen object that holds all input/outpt fn. for comunication
	 *            with a user
	 * @param arguments
	 *            additional arguments that the command needs
	 * @return returns ShellStatus enum, if there was no error ,it returns CONTINUE,
	 *         or else TERMINATE
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		if(!Files.exists(Paths.get(arguments.trim()))) return ShellStatus.CONTINUE;
		if(Files.isDirectory(Paths.get(arguments.trim()))) return ShellStatus.CONTINUE;
		
		
		try (InputStream is = new FileInputStream(arguments.trim())) {
			byte[] buffer = new byte[BUFFER_SIZE];
			while (is.read(buffer) != -1) {
				print(buffer, env);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ShellIOException e) {

			e.printStackTrace();
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Help function that creates formatted output.
	 * 
	 * @param buffer
	 * @param env
	 * @throws ShellIOException
	 * @throws UnsupportedEncodingException
	 */
	private void print(byte[] buffer, Environment env) throws ShellIOException, UnsupportedEncodingException {
		String formated = "";
		String bytes = Util.bytetohex(buffer);
		for (int i = 0; i < 32; i += 2) {
			if (i == 16) {
				formated = formated + "|" + bytes.substring(i, i + 2);
			}
			formated = formated + " " + bytes.substring(i, i + 2);
		}

		for (int i = 0; i < buffer.length; i++) {
			if (buffer[i] < 32 || buffer[i] > 127) {

				buffer[i] = 46;

			}
		}

		env.write(String.format("%08d", LINE++) + ": " + formated + "| " + (new String(buffer, "UTF-8")) + "\n");

	}
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {

		return "hexdump";
	}
	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {

		List<String> desc = new LinkedList<>();
		desc.add("Function creates formated output of a hexadecimal representation of a file ");
		return desc;
	}

}
