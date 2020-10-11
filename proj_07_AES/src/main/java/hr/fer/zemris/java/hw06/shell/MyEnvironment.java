package hr.fer.zemris.java.hw06.shell;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 
 * @author Mateo
 *
 */
public class MyEnvironment implements Environment {

	/**
	 * stores prompt symbol
	 */
	private Character PROMPTSYMBOL = Character.valueOf('>');
	/**
	 * stores morelines symbol
	 */
	private Character MORELINESSYMBOL = Character.valueOf('\\');
	/**
	 * stores multilines symbol
	 */
	private Character MULTILINESYMBOL = Character.valueOf('|');
	/**
	 * Stores a map of currently supported shell commands
	 */
	Map<String, ShellCommand> map = new TreeMap<>();

	/**
	 * Reader object for console input.
	 */
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Takes in a map of supported commands from Shell
	 * 
	 * @param map
	 *            all supported commands,with their ShellCommand objects
	 */
	public MyEnvironment(Map<String, ShellCommand> map) {
		super();
		this.map = map;
	}

	/**
	 * Reads line from standard console input and returns it. @throws
	 */
	@Override
	public String readLine() throws ShellIOException {
		try {
			return reader.readLine();
		} catch (IOException e) {

			throw new ShellIOException();
		}

	}

	/**
	 * Writes given string to standard console output without new line
	 * character.Doesnt throw exceptions.
	 * 
	 */
	@Override
	public void write(String text) throws ShellIOException {
		System.out.format(text);
	}
	/**
	 * Writes given string to standard console output with new line
	 * character.Doesnt throw exceptions.
	 */
	@Override
	public void writeln(String text) throws ShellIOException {
		System.out.println(text);
	}
	/**
	 * Returns a map that is read only.Map holds all supported commands 
	 * and their String reperesentations.
	 * @return unmodifiable sorted map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public SortedMap<String, ShellCommand> commands() {
		return Collections.unmodifiableSortedMap((SortedMap) map);

	}

	/**
	 * Get function for Multiline symbol.Symbol gets printed in the output before
	 * user typed in his second or any after that line of command. In his first
	 * line, me must type Morelines symbol last in order for this to get printed
	 * out.Default one is '|'
	 * 
	 * @return function returns multiline symbol
	 */
	public Character getMultilineSymbol() {
		return MULTILINESYMBOL;
	}
	/**
	 * Function takes in a character as argument, and replaces is with current multiline
	 * symbol
	 * @param symbol new multiline symbol
	 */
	public void setMultilineSymbol(Character symbol) {

		MULTILINESYMBOL = symbol.charValue();
	}
	/**
	 * Function get current prompt symbol.This symbol gets printed ouut before user tipes in 
	 * his first command. Default one is '>'
	 * @return returns current prompt symbol
	 */
	public Character getPromptSymbol() {
		return PROMPTSYMBOL;
	}
	/**
	 * Function sets new prompt symbol and replaces the old one.
	 * @param symbol new prompt symbol
	 */
	public void setPromptSymbol(Character symbol) {
		PROMPTSYMBOL = symbol.charValue();
	}
	/**
	 * Function gets current more lines symbol.This symbol mustt be printed in by user 
	 * to strech his current unfinished command to next line . This character must 
	 * be tiped in last in current line.
	 * Defalt value for this is '\'
	 * @return returns current morelines symbol
	 */
	public Character getMorelinesSymbol() {
		return MORELINESSYMBOL;
	}
	/**
	 * Function sets new more lines symbol value,and replaces it with old one.
	 * @param symbol new more lines value
	 */
	public void setMorelinesSymbol(Character symbol) {
		MORELINESSYMBOL = symbol.charValue();

	}
}
