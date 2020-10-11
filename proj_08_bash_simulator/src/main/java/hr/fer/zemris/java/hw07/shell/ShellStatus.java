package hr.fer.zemris.java.hw07.shell;


/**
 * Enum is used in Shell to tell whenever main loop should stop or continue to execute.
 * If some error occurs in on of the commands, they will return TERMINATE value,
 * which will then tell the program to close.
 * @author Mateo
 *
 */
public enum ShellStatus {
	/**
	 * If command finished as planned, then returns CONTINUE
	 */
	CONTINUE,
	/**
	 * If some error occured (or something else undefined), TERMINATE is passed.
	 */
	TERMINATE
}
