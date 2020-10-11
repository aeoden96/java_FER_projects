package hr.fer.zemris.java.hw07.shell;

import java.nio.file.Path;
import java.util.SortedMap;

/**
 * This interface constructs a mechanism so that every input/output in the code
 * goes through it. Also, functions for manipulating symbols used in output are
 * added.
 * 
 * @author Mateo
 *
 */
public interface Environment {
	/**
	 * Program reads a line from implemented input and returns is as a string. If
	 * any error occurs ,exception is thrown.
	 * 
	 * @return returns a string from input defined by implementation of this
	 *         interface
	 * @throws ShellIOException
	 *             gets thrown if error in reading a file occurs
	 */
	String readLine() throws ShellIOException;

	/**
	 * Program writes to implemented output without new line character. If any error
	 * occurs,exception is thrown.
	 * 
	 * @param text
	 *            text to be written out.
	 * @throws ShellIOException
	 *             gets thrown if error in writing a file occurs
	 */
	void write(String text) throws ShellIOException;

	/**
	 * Program writes to implemented output with new line character. If any error
	 * occurs,exception is thrown.
	 * 
	 * @param text
	 *            text to be written out.
	 * @throws ShellIOException
	 *             gets thrown if error in writing a file occurs
	 */
	void writeln(String text) throws ShellIOException;

	/**
	 * Function that returns sorted map of every supported command in defined
	 * program. Map retured is read only.
	 * 
	 * @return map of all supported commands ,and their String representation
	 */
	SortedMap<String, ShellCommand> commands();

	/**
	 * Get function for Multiline symbol.Symbol gets printed in the output before
	 * user typed in his second or any after that line of command. In his first
	 * line, me must type Morelines symbol last in order for this to get printed
	 * out.Default one is '|'
	 * 
	 * @return function returns multiline symbol
	 */
	Character getMultilineSymbol();

	/**
	 * Function takes in a character as argument, and replaces is with current multiline
	 * symbol
	 * @param symbol new multiline symbol
	 */
	void setMultilineSymbol(Character symbol);

	/**
	 * Function get current prompt symbol.This symbol gets printed ouut before user tipes in 
	 * his first command. Default one is '>'
	 * @return returns current prompt symbol
	 */
	Character getPromptSymbol();
	
	/**
	 * Function sets new prompt symbol and replaces the old one.
	 * @param symbol new prompt symbol
	 */
	void setPromptSymbol(Character symbol);
	/**
	 * Function gets current more lines symbol.This symbol mustt be printed in by user 
	 * to strech his current unfinished command to next line . This character must 
	 * be tiped in last in current line.
	 * Defalt value for this is '\'
	 * @return returns current morelines symbol
	 */
	Character getMorelinesSymbol();
	
	/**
	 * Function sets new more lines symbol value,and replaces it with old one.
	 * @param symbol new more lines value
	 */
	void setMorelinesSymbol(Character symbol);
	
	/**
	 * Gets current directory stored in this object.
	 * @return current directory
	 */
	Path getCurrentDirectory();
	/**
	 * Sets a new current directory
	 * @param path new directory
	 */
	void setCurrentDirectory(Path path);
	/**
	 * Gets the data from shared-data in this object.
	 * @param key key used to get the data
	 * @return data linked to that key
	 */
	Object getSharedData(String key);
	/**
	 * Sets a new value in shared-data.
	 * @param key key used to link with a value
	 * @param value a keys value
	 */
	void setSharedData(String key, Object value);
}
