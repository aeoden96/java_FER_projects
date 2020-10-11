package hr.fer.zemris.java.hw07.shell.commands;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellIOException;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Command prints out ,from given folder, his entire folder and file structure
 * in a form of a tree.
 * 
 * @author Mateo
 *
 */
public class Tree implements ShellCommand {
	/**
	 * Main command which is executed from the shell. Environment object is passed 
	 * so that current command can comunicate to the user through it.
	 * Function takes in a single argment and initiates a recursive function call 
	 * which then prints out files and folders one by one.
	 * @param env Environmen object that holds all input/outpt fn. for comunication with a user
	 * @param arguments additional arguments for folder path
	 * @return returns ShellStatus enum, if there was no error ,it returns CONTINUE, or else TERMINATE
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {

		arguments = arguments.trim();
		
		if(!Files.exists(Paths.get(arguments.trim()))) return ShellStatus.CONTINUE;
		if(!Files.isDirectory(Paths.get(arguments.trim()))) return ShellStatus.CONTINUE;


		File direktorij = new File(arguments);

		try {
			ispis(direktorij, "", env);
		} catch (ShellIOException e) {
			e.printStackTrace();
		}

		return ShellStatus.CONTINUE;

	}
	
	/**
	 * Function goes through file structure and prints out file and folder names.
	 * @param direktorij root directory
	 * @param level empty string that is printed before a name for better formated output
	 * @param env Environment object, function writes string to output through it 
	 * @throws ShellIOException in case of an error, exception is thrown
	 */
	private static void ispis(File direktorij, String level, Environment env) throws ShellIOException {
		File[] djeca = direktorij.listFiles();

		for (File file : djeca) {
			if (file.isFile()) {
				if (!file.exists())
					return;

				env.write(String.format("%s%s\n", level, file.getName()));

			}

			else if (file.isDirectory()) {
				env.write(String.format("%s%s\n", level, file.getName()));
				ispis(file, level + "  ", env);

			}
		}
	}

	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {

		return "tree";
	}
	/**
	 * Fnction returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		List <String> desc=new LinkedList<String>();
		desc.add("The tree command expects a single argument: directory name and prints a tree");
		desc.add("(each directory level shifts output two charatcers to the right)");
		return desc;
	}

}
