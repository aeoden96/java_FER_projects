package hr.fer.zemris.java.hw07.shell.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;
/**
 * Rmtree deletes entire file tree ,including current directory.
 * @author Mateo
 * 
 * **********************************************************
 *                 ODKOMENTIRATI DIO KODA (komentirano je da se slucajno ne izbrise ako se 
 *                 pozove samo rmtree bez argumenata)
 ************************************************************
 */
public class Rmtree implements ShellCommand {
	/**
	 * Main command which is executed from the shell. Environment object is passed 
	 * so that current command can comunicate to the user through it.
	 * Function calls a recursive function that goes from the "end" 
	 * and deletes every file and folder from given root.
	 * At the end ,it deletes the root folder too.
	 * @param env Environmen object that holds all input/outpt fn. for comunication with a user
	 * @param arguments additional arguments that the command needs
	 * @return returns ShellStatus enum, if there was no error ,it returns CONTINUE, or else TERMINATE
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {

		Path newPath=env.getCurrentDirectory().resolve(arguments.trim());
		
		try {
			treeList(new File(newPath.toString()));
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return ShellStatus.CONTINUE;
	}
	/**
	 * Recursive function.Goes through all files from given root ,and deletes
	 * files from "end" folder to the root .
	 * @param src
	 * @throws IOException
	 */
	void treeList(File src) throws IOException {
		if (src.isDirectory()) {
			for (File i : src.listFiles()) {
				treeList(i);
			}
		}
		/*
		 * src.delete();
		*/
		System.out.println(src.toString());
	}
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {
		return "rmtree";
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
