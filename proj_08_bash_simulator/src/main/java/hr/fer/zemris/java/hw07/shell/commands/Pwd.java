package hr.fer.zemris.java.hw07.shell.commands;

import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;
/**
 * Command prints current directory to defined output.
 * @author Mateo
 *
 */
public class Pwd implements ShellCommand {
	/**
	 * When executeCommand is called, it prints to current directory to defined output.
	 * @param env through this object method prints information
	 * @param argments not used here
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		env.writeln(env.getCurrentDirectory().toString());
		return ShellStatus.CONTINUE;
	}
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {
		return "pwd";
	}
	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> desc = new LinkedList<>();
		desc.add("Function prints current directory to defined output.");
		return desc;
	}

}
