package hr.fer.zemris.java.tecaj_13.web.servlets.formulars;

import javax.servlet.http.HttpServletRequest;

import hr.fer.zemris.java.tecaj_13.model.BlogUser;
import hr.fer.zemris.java.tecaj_13.web.servlets.util.Crypto;
import hr.fer.zemris.java.tecaj_13.web.servlets.util.PropertyFormat;

/**
 * Class represents formular used for user registration to the blog.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class RegistrationFormular extends AbstractFormular {

	/**
	 * User's last name.
	 */
	private String lastName;
	/**
	 * User's first name.
	 */
	private String firstName;
	/**
	 * User's Email.
	 */
	private String email;
	/**
	 * User's nick name.
	 */
	private String nick;
	/**
	 * User's hashed password.
	 */
	private String passwordHash;
	/**
	 * User's password.
	 */
	private String password;
	
	/**
	 * Fills formular with parameters got from {@link HttpServletRequest}.
	 * 
	 * @param req object that contains parameters
	 */
	public void getFromHttpRequest(HttpServletRequest req) {
		this.firstName = prepare(req.getParameter("firstname"));
		this.lastName = prepare(req.getParameter("lastname"));
		this.email = prepare(req.getParameter("email"));
		this.nick = prepare(req.getParameter("nick"));
		this.password = prepare(req.getParameter("password"));
	}

	/**
	 * Fills formular with properties from given {@link BlogUser}.
	 * 
	 * @param user object that contains required properties
	 */
	public void getFromBlogUser(BlogUser user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.nick = user.getNick();
		this.passwordHash = user.getPasswordHash();
	}

	/**
	 * Method sets properties of given blog user from this formular.<br>
	 * Method should not be called if it's not validated before.
	 * 
	 * @param user object that whose properties are set
	 */
	public void setBlogUser(BlogUser user) {
		user.setFirstName(this.firstName);
		user.setLastName(this.lastName);
		user.setEmail(this.email);
		user.setNick(this.nick);
		user.setPasswordHash(Crypto.getPasswordHash(this.password)); 
	}
	
	@Override
	public void validate() {
		errors.clear();
		
		if (this.firstName.isEmpty()) {
			errors.put("firstname", "First name is mandatory!");
		}
		if (this.lastName.isEmpty()) {
			errors.put("lastname", "Last name is mandatory!");
		}
		if (this.email.isEmpty()) {
			errors.put("email", "Email is mandatory!");
		} else if (!PropertyFormat.validateEmail(this.email)) {
			errors.put("email", "Email is not of a valid format.");
		}
		if (this.nick.isEmpty()) {
			errors.put("nick", "Nick name is mandatory!");
		}
		if (this.password.isEmpty()) {
			errors.put("password", "Password is mandatory");
		}
	}

	/**
	 * Method returns user's last name.
	 * 
	 * @return user's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method sets user's last name.
	 * 
	 * @param lastName new user's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Method returns user's first name.
	 * 
	 * @return user's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method sets user's first name.
	 * 
	 * @param firstName new user's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Method returns user's email.
	 * 
	 * @return user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method sets user's email.
	 * 
	 * @param email new user's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method returns user's nick name.
	 * 
	 * @return user's nick name
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * Method sets user's nick name.
	 * 
	 * @param nick new user's nick name
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Method returns user's hashed password.
	 * 
	 * @return user's hashed password
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * Method sets user's hashed password.
	 * 
	 * @param passwordHash new user's hashed password
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * Method returns user's password.
	 * 
	 * @return user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method sets user's password.
	 * 
	 * @param password new user's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
