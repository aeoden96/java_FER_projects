package hr.fer.zemris.java.hw06.shell.commands;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Command makes new folder structure from a given argument.
 * 
 * @author Mateo
 *
 */
public class Mkdir implements ShellCommand {
	/**
	 * Main command which is executed from the shell. Environment object is passed
	 * so that current command can comunicate to the user through it. Program
	 * creates a file structure from a given argument. Given argument is a final
	 * diretory of that structure.
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

		
		new File(arguments.trim()).mkdirs();

		
		return ShellStatus.CONTINUE;
	}

	/**
	 * Funtion returns command name as a string.
	 * 
	 * @return command name
	 */
	@Override
	public String getCommandName() {

		return "mkdir";
	}

	/**
	 * Function returns a list which holds a description abouut current command.
	 * 
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {

		List<String> desc = new LinkedList<>();
		desc.add("One arguent must be given,it is a file path ,and all nonexistent directories in ");
		desc.add("that path will be created");
		return desc;
	}

}
