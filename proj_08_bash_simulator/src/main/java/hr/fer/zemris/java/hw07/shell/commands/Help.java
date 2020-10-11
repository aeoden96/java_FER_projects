package hr.fer.zemris.java.hw07.shell.commands;

import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellIOException;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * This class implements ShellCommand .It is sed to help the user and show him
 * all available commands of the Shell program. If one other argument is given
 * ,the command name ,than command name and descriiption will bi written put to
 * the user.
 * 
 * @author Mateo
 *
 */
public class Help implements ShellCommand {
	/**
	 * Main command which is executed from the shell. Environment object is passed 
	 * so that current command can comunicate to the user through it.
	 * In this method ,aditional arguments are not required,
	 * then the method will just print out all available command.
	 * If argument is given, method will print out its name and description.
	 * @param env Environmen object that holds all input/outpt fn. for comunication with a user
	 * @param arguments additional arguments that the command needs
	 * @return returns ShellStatus enum, if there was no error ,it returns CONTINUE, or else TERMINATE
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {

		if (arguments.equals("")) {

			for (String i : env.commands().keySet()) {
				try {
					env.writeln(i);
				} catch (ShellIOException e) {

					e.printStackTrace();
				}
			}
		} else if (env.commands().containsKey(arguments.trim())) {
			try {
				env.writeln("Command : " + env.commands().get(arguments.trim()).getCommandName());
				for (String i : env.commands().get(arguments.trim()).getCommandDescription()) {
					env.writeln(i);
				}
			} catch (ShellIOException e) {

				e.printStackTrace();
			}
		} else {
			try {
				env.writeln("Unsupported command");
			} catch (ShellIOException e) {
				e.printStackTrace();
			}
		}

		return ShellStatus.CONTINUE;
	}
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {
		return "help";
	}
	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> desc = new LinkedList<>();
		desc.add("Command prints to output list of all supported commands if no argments are given");
		desc.add("If an argument is given,command prints out command name and its description.");
		return desc;
	}

}
