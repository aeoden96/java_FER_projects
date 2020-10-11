package hr.fer.zemris.java.hw07.shell.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellIOException;
import hr.fer.zemris.java.hw07.shell.ShellStatus;
/**
 * This Command prints to defines output all contents of a folder,
 * including the date when it was created, size of the file,
 * and also information like is it exectable, readable or writable.
 * @author Mateo
 *
 */
public class Ls implements ShellCommand {
	/**
	 * Main command which is executed from the shell. Environment object is passed 
	 * so that current command can comunicate to the user through it.
	 * Function prints out information about each file in a given folder,
	 * but its not recursive,it doesnt give information about contents inside 
	 * folders that are inside given root folder.
	 * @param env Environmen object that holds all input/outpt fn. for comunication with a user
	 * @param arguments additional arguments that the command needs
	 * @return returns ShellStatus enum, if there was no error ,it returns CONTINUE, or else TERMINATE
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		arguments= arguments.trim();
		
		
		
		
		
		
		File direktorij = new File(arguments);

		File[] djeca = direktorij.listFiles();

		if (djeca != null) {
			for (File file : djeca) {
								
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Path path = Paths.get(arguments);
				BasicFileAttributeView faView = Files.getFileAttributeView(
				path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS
				);
				BasicFileAttributes attributes=null;
				try {
					attributes = faView.readAttributes();
				} catch (IOException e) {
		
					e.printStackTrace();
				}
				FileTime fileTime = attributes.creationTime();
				String formattedDateTime = sdf.format(new Date(fileTime.toMillis()));
				
				print(file.isDirectory(),
						file.canRead(),
						file.canWrite(),
						file.canExecute(),
						file.length(),
						formattedDateTime,
						file.getName(),
						env);
				
			}
			
		}
		
		return ShellStatus.CONTINUE;
	}
	
	/**
	 * Function prints out information about current file.
	 * @param isDir  if a file is directory ,prints 'd' ,otherwise '-'
	 * @param readable if its readable prints 'r' ,otherwise '-'
	 * @param writable if its writable prints 'w' , otherwise '-'
	 * @param exec if its executable prints 'x',otherwise '-'
	 * @param size size of the file
	 * @param datetime date and time file was created
	 * @param name name of the file
	 * @param env object throgh which information is printed to defined output
	 */
	private void print(boolean isDir,
			boolean readable,
			boolean writable,
			boolean exec ,
			long size,
			String datetime,
			String name,
			Environment env
			) {
		
		
		try {
			env.write((isDir ? "d" : "-") +
					(readable ? "r" : "-") + 
					(writable ? "w" : "-") + 
					(exec ? "x" : "-") + " " + 
					String.format("%10d",size) +
					" " + 
					datetime + " " + name + "\n");
			
			
			
		} catch (ShellIOException e) {
	
			e.printStackTrace();
		}
		
		
	}
	/**
	 * Funtion returns command name as a string.
	 * @return command name
	 */
	@Override
	public String getCommandName() {
		
		return "ls";
	}
	/**
	 * Function returns a list which holds a description abouut current command.
	 * @return returns a list which holds the description
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> desc = new LinkedList<>();
		desc.add("Prints out information about each file in a given folder");
		return desc;
	}

}
