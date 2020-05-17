package hr.fer.zemris.java.tecaj_13.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class represents comment that is posted to the blog entry.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
@NamedQueries({
	@NamedQuery(name = "BlogComment.queryBlog", query = "select c from BlogComment as c where c.blogEntry=:be") 
	})
@Entity
@Table(name = "blog_comments")
public class BlogComment {

	/**
	 * Blog's ID.
	 */
	private Long id;
	/**
	 * Blog entry to whom comment belongs.
	 */
	private BlogEntry blogEntry;
	/**
	 * User's email.
	 */
	private String usersEMail;
	/**
	 * Comment's message.
	 */
	private String message;
	/**
	 * Time when comment was posted.
	 */
	private Date postedOn;

	/**
	 * Method returns comment's ID.
	 *  
	 * @return comment's ID
	 */
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	/**
	 * Method sets comment's ID.
	 * 
	 * @param id new comment's ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method returns blog entry to whom comment belongs.
	 * 
	 * @return blog entry to whom comment belongs
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	public BlogEntry getBlogEntry() {
		return blogEntry;
	}

	/**
	 * Method sets comment's blog entry.
	 * 
	 * @param blogEntry new comment's blog entry
	 */
	public void setBlogEntry(BlogEntry blogEntry) {
		this.blogEntry = blogEntry;
	}

	/**
	 * Method returns comment's user email.
	 * 
	 * @return comment's user email
	 */
	@Column(length = 100, nullable = false)
	public String getUsersEMail() {
		return usersEMail;
	}

	/**
	 * Method sets comment's user email.
	 * 
	 * @param usersEMail new comment's user email
	 */
	public void setUsersEMail(String usersEMail) {
		this.usersEMail = usersEMail;
	}

	/**
	 * Method returns comment's message.
	 * 
	 * @return comment's message
	 */
	@Column(length = 4096, nullable = false)
	public String getMessage() {
		return message;
	}

	/**
	 * Method sets comment's message.
	 * 
	 * @param message new comment's message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Method returns comment's date when it was posted.
	 * 
	 * @return date of the post
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getPostedOn() {
		return postedOn;
	}

	/**
	 * Method sets comment's date when it was posted.
	 * 
	 * @param postedOn new date of the post
	 */
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogComment other = (BlogComment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}