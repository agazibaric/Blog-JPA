package hr.fer.zemris.java.tecaj_13.web.servlets.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class offers different format checkers.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class PropertyFormat {
	
	/**
	 * Email regular expression.
	 */
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	/**
	 * Method validates given emal string.
	 * 
	 * @param emailStr email
	 * @return         <code>true</code> if email has valid format,
	 * 				   <code>false</code> otherwise
	 */
	public static boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

}
