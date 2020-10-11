package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Path;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Lists all paths in the stack.
 * @author Mateo
 *
 */
public class Listd implements ShellCommand {
	/**
	 * Main command which is executed from the shell. Environment object is passed 
	 * so that current command can comunicate to the user through it.
	 * Function lists in defined output all saved paths in the stack.
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
		for( Object i : ((Stack<Path>) (env.getSharedData("cdstack")))) {
			env.writeln( ((Path)i).toString()  );
		}
		
		
		

		return ShellStatus.CONTINUE;
	}
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {
		return "listd";
	}
	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		
		return null;
	}

}
