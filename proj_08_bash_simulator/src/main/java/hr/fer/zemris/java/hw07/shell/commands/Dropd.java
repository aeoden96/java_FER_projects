package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;
/**
 * Command drops a path from a stack,it doesnt save it or print it anywhere.
 * @author Mateo
 *
 */
public class Dropd implements ShellCommand {

	/**
	 * Main command which is executed from the shell. Environment object is passed 
	 * so that current command can comunicate to the user through it.
	 * Funtion drops a path from a stack,it doesnt save it or print it anywhere.
	 * @param env Environmen object that holds all input/outpt fn. for comunication with a user
	 * @param arguments additional arguments that the command needs
	 * @return returns ShellStatus enum, if there was no error ,it returns CONTINUE, or else TERMINATE
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		if (env.getSharedData("cdstack") == null) {
			env.writeln("There's no stack!");
			return ShellStatus.CONTINUE;
		}
		if (((Stack<Path>) (env.getSharedData("cdstack"))).empty()) {
			env.writeln("Stack empty!");
			return ShellStatus.CONTINUE;
		}
		
		((Stack<Path>) env.getSharedData("cdstack")).pop();
		
		return ShellStatus.CONTINUE;
	}
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {
		return "dropd";
	}
	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> desc = new LinkedList<>();
		desc.add("Command drops a path from a stack,it doesnt save it or print it anywhere.");
		return desc;
	}

}
