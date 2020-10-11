package hr.fer.zemris.java.hw06.shell.commands;

import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Command is used for editing or viewing current output symbols.
 * It also gives functions that returns information about current command.
 */
public class Symbol implements ShellCommand {
	/**
	 * Main command which is executed from the shell. Environment object is passed 
	 * so that current command can comunicate to the user through it.
	 * Function edits or prints out current symbl,depending on arguments given:
	 * if no second argument is given ,then wanted symbol is only printed to output
	 * if second argument is given,then current symbol is replaced with given one
	 * @param env Environmen object that holds all input/outpt fn. for comunication with a user
	 * @param arguments additional arguments that the command needs
	 * @return returns ShellStatus enum, if there was no error ,it returns CONTINUE, or else TERMINATE
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {

		String parts[] = arguments.trim().split("\\s+");
		for (String i : parts) {
			System.out.println("**" + i + "**/");
		}

		boolean arg2 = parts.length == 2 ? true : false;
		String text = "";
		try {
			env.write("Symbol for ");
			switch (parts[0]) {
			case "MORELINES":
				text = env.getMorelinesSymbol().toString();
				if (arg2) {
					env.setMorelinesSymbol(parts[1].charAt(0));
				}

			case "MULTILINE":
				text = env.getMultilineSymbol().toString();
				if (arg2) {
					env.setMultilineSymbol(parts[1].charAt(0));
				}

			case "PROMPT":
				text = env.getPromptSymbol().toString();
				if (arg2) {
					env.setPromptSymbol(parts[1].charAt(0));
				}
			}
			env.writeln(parts[0] + " "
					+ (arg2 ? "changed from '" + text + "' to '" + parts[1].charAt(0) + "'." : " is '" + text + "'."));

		} catch (ShellIOException e) {
			e.printStackTrace();
		}

		return ShellStatus.CONTINUE;

	}
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {
		return "symbol";
	}
	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> desc = new LinkedList<>();
		desc.add("If only one argument is given, command will print out wanted symbol o output");
		desc.add("If second argument is given,command will change current symbol will given one.");
		return desc;
	}

}
