package hr.fer.zemris.java.hw07.shell.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Command copies a file to a given directory.
 * 
 * @author Mateo
 *
 */
public class Copy implements ShellCommand {
	/**
	 * Main command which is executed from the shell. Environment object is passed
	 * so that current command can comunicate to the user through it. It creates
	 * copy of a file to a given directory. File to be copied and wanted directory
	 * function gets as arguments.
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
		String[] parts = arguments.trim().split("\\s+");

		if (!Files.exists(Paths.get(parts[0])))
			return ShellStatus.CONTINUE;
		if (Files.isDirectory(Paths.get(parts[0])))
			return ShellStatus.CONTINUE;

	

		try (InputStream is = new FileInputStream(parts[0]); OutputStream os = new FileOutputStream(parts[1])) {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) != -1) {
				os.write(buffer, 0, length);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Funtion returns command name as a string.
	 * 
	 * @return command name
	 */
	@Override
	public String getCommandName() {
		return "copy";
	}

	/**
	 * Function returns a list which holds a description abouut current command.
	 * 
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> desc = new LinkedList<>();
		desc.add("command expects two arguments: source file name and destination file name ");
		return desc;
	}

}
