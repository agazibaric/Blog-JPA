package hr.fer.zemris.java.tecaj_13.dao;

import java.util.List;

import hr.fer.zemris.java.tecaj_13.model.BlogComment;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

/**
 * Interface to Data Subsystem.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public interface DAO {
	
	/**
	 * Method returns blog entry that has given ID.
	 * 
	 * @param id            ID of the blog entry
	 * @return				blog entry with given ID
	 * @throws DAOException if returning of the blog fails
	 */
	BlogEntry getBlogEntry(Long id) throws DAOException;
	
	/**
	 * Method returns list of all blog users.
	 * 
	 * @return              list of all blog users
	 * @throws DAOException if returning of blog users fails
	 */
	List<BlogUser> getBlogUsers() throws DAOException;
	
	/**
	 * Method adds new blog user.
	 * 
	 * @param user          new blog user
	 * @throws DAOException if adding of blog user fails
	 */
	void addNewUser(BlogUser user) throws DAOException;
	
	/**
	 * Method returns blog user that has given nick name.
	 * 
	 * @param nick          nick of blog user
	 * @return              blog user with given nick name
	 * @throws DAOException if returning of blog user fails
	 */
	BlogUser getBlogUserWithNick(String nick) throws DAOException;
	
	/**
	 * Method adds new blog entry.
	 * 
	 * @param blog          blog that is added
	 * @throws DAOException if adding of blog entry fails
	 */
	void addBlogEntry(BlogEntry blog) throws DAOException;
	
	/**
	 * Method adds new blog comment.
	 * 
	 * @param comment       comment that is added
	 * @throws DAOException if adding of blog comment fails
	 */
	void addBlogComment(BlogComment comment) throws DAOException;
	
}