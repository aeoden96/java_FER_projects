package hr.fer.zemris.java.hw06.shell;

import java.util.List;
/**
 * Interface which gives 3 funnction that are used for communication with Environent object.
 * It also gives functions that returns information about current command.
 * such as its name and description.
 * @author Mateo
 *
 */
public interface ShellCommand {
	/**
	 * Main command which is executed from the shell. Environment object is passed 
	 * so that current command can comunicate to the user through it.
	 * @param env Environmen object that holds all input/outpt fn. for comunication with a user
	 * @param arguments additional arguments that the command needs
	 * @return returns ShellStatus enum, if there was no error ,it returns CONTINUE, or else TERMINATE
	 */
	ShellStatus executeCommand(Environment env, String arguments);
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	String getCommandName();
	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	List<String> getCommandDescription();
}
 