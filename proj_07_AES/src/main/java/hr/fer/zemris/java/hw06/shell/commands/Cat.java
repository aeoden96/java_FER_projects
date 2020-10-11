/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * Command prints contents of given file to defined output.
 * @author Mateo
 *
 */
public class Cat implements ShellCommand {
	/**
	 * defined buffer size
	 */
	private static int BUFFER_SIZE=8;
	
	/**
	 * Main command which is executed from the shell. Environment object is passed 
	 * so that current command can comunicate to the user through it.
	 * Function prints content of a file to defined output,
	 * it prints in small chunks defined by  BUFFER_SIZE.
	 * @param env Environmen object that holds all input/outpt fn. for comunication with a user
	 * @param arguments additional arguments that the command needs
	 * @return returns ShellStatus enum, if there was no error ,it returns CONTINUE, or else TERMINATE
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		
		String parts[]= arguments.trim().split("\\s+");
		
		if(!Files.exists(Paths.get(parts[0].trim()))) return ShellStatus.CONTINUE;
		if(Files.isDirectory(Paths.get(parts[0].trim()))) return ShellStatus.CONTINUE;


		Charset t;
		if(parts.length>1) {
			
			t = Charset.forName(parts[1].trim());
			
		
		}
		else {
			t= Charset.defaultCharset();
		}
		char[] buffer = new char[BUFFER_SIZE];
		try (BufferedReader br=Files.newBufferedReader(Paths.get(parts[0]), t)) {
			while (br.read(buffer) != -1) {

				env.write(new String(buffer));
			}
			env.writeln("");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		
		return "cat";
	}

	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> desc = new LinkedList<>();
		desc.add("Takes one or two arguments. The first argument is path to some file and is mandatory"
				+ "\nsecond one is a charset.");
		desc.add("This command opens given file and writes its content to console");
		return desc;
	}

}
