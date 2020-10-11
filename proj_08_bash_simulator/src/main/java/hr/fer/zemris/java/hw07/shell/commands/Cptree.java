package hr.fer.zemris.java.hw07.shell.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;
/**
 * Command copies entire file tree to a new location.
 * @author Mateo
 *
 */
public class Cptree implements ShellCommand {
	/**
	 * size of a buffer for output stream
	 */
	private int BUFFER_LEN= 1024;
	
	/**
	 * Main command which is executed from the shell. Environment object is passed 
	 * so that current command can comunicate to the user through it.
	 * Function copies entire file tree to a new destination.
	 * @param env Environmen object that holds all input/outpt fn. for comunication with a user
	 * @param arguments additional arguments that the command needs
	 * @return returns ShellStatus enum, if there was no error ,it returns CONTINUE, or else TERMINATE
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String[] args = arguments.trim().split("\\s+");
		if (args.length != 2) {
			env.writeln("2 args required.");
			return ShellStatus.CONTINUE;
		}
		File src = new File(args[0]);
		File dest = new File(args[1]);
		if (!src.exists()) {
			env.writeln("Source folder doesn't exist.");
			return ShellStatus.CONTINUE;
		}

		try {
			treeList(src, dest);
		} catch (IOException e) {
			env.writeln("Undifined error.");
			return ShellStatus.TERMINATE;
		}

		return ShellStatus.CONTINUE;
	}
	
	/**
	 * Recursive fn. that goes through the tree and copies file in chunks defined by
	 * BUFFER_LEN. If destination directories dont exist ,function will create them.
	 * @param src root of a source directory
	 * @param dest root of a destination directory
	 * @throws IOException throws exception if ther is a problem with reading/writing a certain file
	 */
	private void treeList(File src, File dest) throws IOException {
		if (src.isDirectory()) {

			if (!dest.exists()) {
				dest.mkdir();
			}
			for (String file : src.list()) {
				treeList(new File(src, file), new File(dest, file));
			}

		} else {

			try (InputStream in = new FileInputStream(src);
					OutputStream out = new FileOutputStream(dest)) {
				byte[] buffer = new byte[BUFFER_LEN];
				int length;
				while ((length = in.read(buffer)) != -1) {
					out.write(buffer, 0, length);
				}
			}

		}

	}
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {
		return "cptree";
		
	}
	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		
		List<String> desc = new LinkedList<>();
		desc.add("Command copies entire fie tree to a new location.");
		
		return desc;
	}

}
