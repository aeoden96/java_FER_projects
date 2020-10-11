package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;
/**
 * Command resolves new path from current path. If an absolute path
 * is given as argument,then that path is then new current path.
 * @author Mateo
 *
 */
public class Cd implements ShellCommand {
	
	/**
	 * Main command which is executed from the shell. Environment object is passed 
	 * so that current command can comunicate to the user through it.
	 * Function resolves new path from current path.
	 * @param env Environmen object that holds all input/outpt fn. for comunication with a user
	 * @param arguments additional arguments that the command needs
	 * @return returns ShellStatus enum, if there was no error ,it returns CONTINUE, or else TERMINATE
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		arguments=arguments.trim();
		

		Path newPath=env.getCurrentDirectory().resolve(arguments);
		
		env.setCurrentDirectory(newPath);
		
		
		return ShellStatus.CONTINUE;
	}
	
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {
		return "cd";
	}
	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> desc = new LinkedList<>();
		desc.add("Function resolves new path from current path.");
		
		return desc;
	}

}
