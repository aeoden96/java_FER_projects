package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;
/**
 * Command pops a value from a stack in shared-data.
 * @author Mateo
 *
 */
public class Popd implements ShellCommand {
	/**
	 * Function pops a value from a stack stored in Environment object.
	 */
	@SuppressWarnings({ "unchecked" })
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

		Path temp = ((Stack<Path>) env.getSharedData("cdstack")).pop();

		if (!Files.exists(temp)) {
			env.writeln("Not valid directory");
			return ShellStatus.CONTINUE;
		}

		env.setCurrentDirectory(temp);

		return ShellStatus.CONTINUE;
	}
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {
		return "popd";
	}
	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> desc = new LinkedList<>();
		desc.add("Command pops a path from the stack.");
	
		return desc;
	}

}
