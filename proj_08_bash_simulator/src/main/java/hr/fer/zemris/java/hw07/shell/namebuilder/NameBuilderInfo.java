package hr.fer.zemris.java.hw07.shell.namebuilder;

/**
 * This interface defines a object NameBuilderInfo,which has to be specific for
 * each file. This object should have its own instance of StringBuilder,and by
 * passing it to NameBuilder objects,its StringBuilder object is slowly filled
 * with resulting filename. It also has to have its own matcher object, and when
 * getGroup is called,it has ti get a group from regex expresion from saved
 * matcher object.
 * 
 * @author Mateo
 *
 */
public interface NameBuilderInfo {
	/**
	 * Returns final StringBuilder object,which represents final filename.
	 * 
	 * @return new filename
	 */
	public StringBuilder getStringBuilder();

	/**
	 * Function generates a String,sing a saved matcher object.
	 * This string represents some group from regex expression form matcher object.
	 * @param index what group is required
	 * @return returns a group 
	 */
	public String getGroup(int index);
}
