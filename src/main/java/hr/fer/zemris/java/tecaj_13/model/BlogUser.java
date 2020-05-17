package hr.fer.zemris.java.tecaj_13.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Class represents registered user to the blog.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
@NamedQueries({ 
		@NamedQuery(name = "BlogUser.queryAll", query = "select bu from BlogUser as bu"),
		@NamedQuery(name = "BlogUser.queryNick", query = "select bu from BlogUser as bu where bu.nick=:nick")
})
@Entity
@Table(name = "blog_user")
public class BlogUser {

	/**
	 * Blog user's ID.
	 */
	private Integer id;
	/**
	 * Blog user's first name.
	 */
	private String firstName;
	/**
	 * Blog user's last name.
	 */
	private String lastName;
	/**
	 * Blog user's nick.
	 */
	private String nick;
	/**
	 * Blog user's email.
	 */
	private String email;
	/**
	 * Blog user's hashed password.
	 */
	private String passwordHash;
	/**
	 * Blog user's blog entries.
	 */
	private List<BlogEntry> blogEntries;

	/**
	 * Method returns blog user's ID.
	 *  
	 * @return blog user's ID
	 */
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	/**
	 * Method sets blog user's ID.
	 * 
	 * @param id new blog user's ID 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Method returns blog user's first name.
	 * 
	 * @return blog user's first name
	 */
	@Column(nullable = false, length = 20)
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method sets blog user's first name.
	 * 
	 * @param firstName new blog user's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Method returns blog user's last name.
	 * 
	 * @return blog user's last name
	 */
	@Column(nullable = false, length = 30)
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method sets blog user's last name.
	 * 
	 * @param lastName new blog user's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Method returns blog user's nick.
	 * 
	 * @return blog user's nick
	 */
	@Column(nullable = false, length = 20, unique = true)
	public String getNick() {
		return nick;
	}

	/**
	 * Method sets blog user's nick.
	 * 
	 * @param nick new blog user's nick
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Method returns blog user's email.
	 * 
	 * @return blog user's email
	 */
	@Column(nullable = false, length = 30)
	public String getEmail() {
		return email;
	}

	/**
	 * Method sets blog user's email
	 * 
	 * @param email new blog user's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method returns blog user's hashed password.
	 * 
	 * @return blog user's hashed password
	 */
	@Column(nullable = false, length = 50)
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * Method sets blog user's hashed password.
	 * 
	 * @param passwordHash new blog user's password
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	/**
	 * Method returns blog user's blog entries.
	 * 
	 * @return blog user's blog entries
	 */
	@OneToMany(mappedBy = "creator", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	@OrderBy("createdAt")
	public List<BlogEntry> getBlogEntries() {
		return blogEntries;
	}
	
	/**
	 * Method sets blog user's blog entries.
	 * 
	 * @param blogEntries new blog user's blog entries
	 */
	public void setBlogEntries(List<BlogEntry> blogEntries) {
		this.blogEntries = blogEntries;
	}

}
