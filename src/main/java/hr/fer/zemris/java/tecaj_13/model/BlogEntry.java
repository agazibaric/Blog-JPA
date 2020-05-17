package hr.fer.zemris.java.tecaj_13.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class represents blog entry that has title, text and its comments.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
@Entity
@Table(name = "blog_entries")
@Cacheable(true)
public class BlogEntry {

	/**
	 * Blog entry's ID.
	 */
	private Long id;
	/**
	 * Blog entry's comments.
	 */
	private List<BlogComment> comments = new ArrayList<>();
	/**
	 * Blog entry's creation date.
	 */
	private Date createdAt;
	/**
	 * Blog entry's date of last modification.
	 */
	private Date lastModifiedAt;
	/**
	 * Blog entry's title.
	 */
	private String title;
	/**
	 * Blog entry's text.
	 */
	private String text;
	/**
	 * Blog entry's user that created it.
	 */
	private BlogUser creator;

	/**
	 * Method returns blog entry's ID.
	 *  
	 * @return blog entry's ID
	 */
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	/**
	 * Method sets blog entry's ID.
	 * 
	 * @param id new blog entry's ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method blog entry's comments.
	 * 
	 * @return blog entry's comments
	 */
	@OneToMany(mappedBy = "blogEntry", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	@OrderBy("postedOn")
	public List<BlogComment> getComments() {
		return comments;
	}

	/**
	 * Method sets blog entry's comments.
	 * 
	 * @param comments new blog entry's comments
	 */
	public void setComments(List<BlogComment> comments) {
		this.comments = comments;
	}

	/**
	 * Method returns blog entry's date of creation.
	 * 
	 * @return blog entry's creation date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Method sets blog entry's creation date.
	 * 
	 * @param createdAt new blog entry's creation date
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Method returns blog entry's date of last modification.
	 * 
	 * @return blog entry's date of last modification
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	/**
	 * Method sets blog entry's date of last modification.
	 * 
	 * @param lastModifiedAt new blog entry's date of last modification 
	 */
	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	/**
	 * Method returns blog entry's title.
	 * 
	 * @return blog entry's title
	 */
	@Column(length = 200, nullable = false)
	public String getTitle() {
		return title;
	}

	/**
	 * Method sets blog entry's title.
	 * 
	 * @param title new blog entry's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method returns blog entry's text.
	 * 
	 * @return blog entry's text
	 */
	@Column(length = 4096, nullable = false)
	public String getText() {
		return text;
	}

	/**
	 * Method sets blog entry's text.
	 * 
	 * @param text new blog entry's text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Method returns blog entry's creator.
	 * 
	 * @return blog entry's creator
	 */
	@ManyToOne
	public BlogUser getCreator() {
		return creator;
	}

	/**
	 * Method sets blog entry's creator.
	 * 
	 * @param creator new blog entry's creator
	 */
	public void setCreator(BlogUser creator) {
		this.creator = creator;
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
		BlogEntry other = (BlogEntry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}