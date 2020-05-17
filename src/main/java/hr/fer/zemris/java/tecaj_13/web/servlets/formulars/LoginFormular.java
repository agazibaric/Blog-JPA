package hr.fer.zemris.java.tecaj_13.web.servlets.formulars;

import javax.servlet.http.HttpServletRequest;

import hr.fer.zemris.java.tecaj_13.model.BlogUser;
import hr.fer.zemris.java.tecaj_13.web.servlets.util.Crypto;

/**
 * Class represents formular for user login to the blog.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class LoginFormular extends AbstractFormular {
	
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
		this.nick = prepare(req.getParameter("nick"));
		this.password = prepare(req.getParameter("password"));
	}

	/**
	 * Fills formular with properties from given {@link BlogUser}.
	 * 
	 * @param user object that contains required properties
	 */
	public void getFromBlogUser(BlogUser user) {
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
		user.setNick(this.nick);
		user.setPasswordHash(Crypto.getPasswordHash(this.password)); 
	}
	
	@Override
	public void validate() {
		errors.clear();
		
		if (this.nick.isEmpty()) {
			errors.put("nick", "Nick name is mandatory!");
		}
		if (this.password.isEmpty()) {
			errors.put("password", "Password is mandatory");
		}
	}

	/**
	 * Method returns user's nick.
	 * 
	 * @return user's nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * Method sets user's nick.
	 * 
	 * @param nick new user's nick
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Method returns hashed password.
	 * 
	 * @return hashed password
	 */
	public String getPasswordHash() {
		return passwordHash;
	}
	
	/**
	 * Method sets hashed password.
	 * 
	 * @param passwordHash new hashed password
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
