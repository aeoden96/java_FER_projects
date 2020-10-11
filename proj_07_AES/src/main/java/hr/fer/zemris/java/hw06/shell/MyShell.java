package hr.fer.zemris.java.hw06.shell;


import java.util.Map;
import java.util.TreeMap;

import hr.fer.zemris.java.hw06.shell.commands.*;

/**
 * Shell is a program with couple of basic command which user types is ,and then
 * result is printed in output defined in MyEnviroment class.
 * 
 * 
 * Defined commands:
 * <ul>
 * <li>cat
 * <li>charsets
 * <li>ls
 * <li>tree
 * <li>copy
 * <li>mkdir
 * <li>hexdump
 * 
 * </ul>
 * For more information about each command ,look up in implementation of that command.
 * Program exit if "exit" is typed in.
 * 
 * @author Mateo
 *
 */
public class MyShell {
	/**
	 * Main function initiates our shell program.Shell program runs in a loop 
	 * until user enters "exit"in output or error occurs.
	 * @param args not used here
	 */
	public static void main(String[] args)  {

		Map<String, ShellCommand> map = new TreeMap<>();
		map.put("cat", new Cat());
		map.put("charsets", new Charsets());
		map.put("ls", new Ls());
		map.put("tree", new Tree());
		map.put("copy", new Copy());
		map.put("mkdir", new Mkdir());
		map.put("hexdump", new Hexdump());
		map.put("symbol", new Symbol());
		map.put("help", new Help());

		Environment envi = new MyEnvironment(map);

		try {
			envi.writeln("Welcome to MyShell v 1.0");
		} catch (ShellIOException e) {
			System.out.println("error with output");
			e.printStackTrace();
		}
		
		
		boolean moreLines = false;
		String entry = "";
		ShellStatus loopStatus = ShellStatus.CONTINUE;
		while (loopStatus != ShellStatus.TERMINATE) {

			System.out.format(
					(moreLines ? envi.getMultilineSymbol().toString() : envi.getPromptSymbol().toString()) + " ");

			String line=null;
			try {
				line = envi.readLine().trim();
			} catch (ShellIOException e) {
				System.out.println("error with input");
				e.printStackTrace();
			}
			

			if (!line.equals("") && envi.getMorelinesSymbol().equals(Character.valueOf(line.charAt(line.length() - 1)))) {
				moreLines = true;
				entry = entry + " " + line.substring(0, line.length() - 1) + " ";
			} else {
				moreLines = false;
				entry = entry + " " + line;
				if (entry.trim().equals("exit")) {
					System.exit(0);
				}
				String[] parts = entry.trim().split("\\s+");
				String commandString = parts[0];
				String arguments = entry.substring(commandString.length() + 1, entry.length());
				entry = "";
				if(!map.containsKey(commandString)) {
					continue;
				}

				ShellCommand command = envi.commands().get(commandString);
				
			
				try {
				loopStatus = command.executeCommand(envi, arguments);
				}
				catch(ShellIOException e) {
					
				}
				
				
			}
		}
	}
}
