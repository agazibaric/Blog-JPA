package hr.fer.zemris.java.tecaj_13.web.servlets.formulars;

import java.util.HashMap;
import java.util.Map;

/**
 * General form of formular used in blog.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public abstract class AbstractFormular {
	
	/**
	 * Map that contains errors. It is expected that keys are named as
	 * blog entry's properties and values are messages about error that happened.
	 */
	protected Map<String, String> errors = new HashMap<>();
	
	/**
	 * Method returns message about error that is associated to the given name.
	 * 
	 * @param  name entry's property to whom error is associated
	 * @return message about error that is associated to the given name
	 * 		   or <code>null</code> if there's no mapped message for given name
	 */
	public String getError(String name) {
		return errors.get(name);
	}
	
	/**
	 * Method checks if any errors occurred.
	 * 
	 * @return <code>true</code> if errors occurred,
	 * 		   <code>false</code> otherwise
	 */
	public boolean hasErrors() {
		return !errors.isEmpty();
	}
	
	/**
	 * Method checks if error occurred for the specified name.
	 * 
	 * @param  name to which error is associated
	 * @return <code>true</code> if error occurred, 
	 * 		   <code>false</code> otherwise
	 */
	public boolean hasError(String name) {
		return errors.containsKey(name);
	}
	
	/**
	 * Method sets error message to the given name.
	 * 
	 * @param name    name to which error message is associated
	 * @param message message that describes error that occurred
	 */
	public void setError(String name, String message) {
		errors.put(name, message);
	}
	
	/**
	 * Helper method that converts <code>null</code> values
	 * into empty strings or returns trimmed value that is given.
	 * 
	 * @param s String object
	 * @return  trimmed string, if given object is <code>null</code> returns empty string
	 */
	protected String prepare(String s) {
		if (s == null)
			return "";
		return s.trim();
	}
	
	/**
	 * Method performs validation of formular.
	 */
	abstract public void validate();

}
