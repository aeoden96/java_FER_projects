package hr.fer.zemris.java.hw07.shell.namebuilder;
/**
 * Interface defines a Builder class which extracts a piece of a String (file name),and 
 * then using a function execute passes it to a StringBuilder (which is part of
 * NBInfo object).
 * @author Mateo
 *
 */
public interface NameBuilder {
	/**
	 * Function is called for each file ,meaning every file has its own NBInfo object.
	 * 
	 * @param info
	 */
	void execute(NameBuilderInfo info);
	
}
