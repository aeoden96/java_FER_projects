package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Command pushes current directory to top of the stack,and also 
 * definies new current dir. from given argument (only if arg. is valid).
 * @author Mateo
 *
 */
public class Pushd implements ShellCommand{
	/**
	 * Main command which is executed from the shell. Environment object is passed 
	 * so that current command can comunicate to the user through it.
	 * Function pushes current directory on top of the stack.
	 * @param env Environmen object that holds all input/outpt fn. for comunication with a user
	 * @param arguments additional arguments that the command needs
	 * @return returns ShellStatus enum, if there was no error ,it returns CONTINUE, or else TERMINATE
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		arguments=arguments.trim();
		if(!Files.exists(Paths.get(arguments)) || !Files.isDirectory(Paths.get(arguments))) {
			env.writeln("Not valid directory");
			return ShellStatus.CONTINUE;
		}
		
		
		if(env.getSharedData("cdstack")==null) {
			
			env.setSharedData("cdstack", new Stack<Path>());
		}
		
		((Stack<Path>)(env.getSharedData("cdstack"))).push(env.getCurrentDirectory());
	
		
		
		
		
		env.setCurrentDirectory(Paths.get(arguments));
		
		return ShellStatus.CONTINUE;
	}
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {
		return "pushd";
	}
	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> desc = new LinkedList<>();
		desc.add("Pushes current directory to stack. ");
		return desc;
	}

}
