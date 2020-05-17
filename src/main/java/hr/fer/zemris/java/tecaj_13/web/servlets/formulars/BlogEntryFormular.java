package hr.fer.zemris.java.tecaj_13.web.servlets.formulars;

import javax.servlet.http.HttpServletRequest;

import hr.fer.zemris.java.tecaj_13.model.BlogEntry;

/**
 * Formular used for creating new or editing existing blog entry.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class BlogEntryFormular extends AbstractFormular {
	
	/**
	 * Blog entry's title.
	 */
	private String title;
	/**
	 * Blog entry's text.
	 */
	private String text;
	
	/**
	 * Fills formular with parameters got from {@link HttpServletRequest}.
	 * 
	 * @param req object that contains parameters
	 */
	public void getFromHttpRequest(HttpServletRequest req) {
		this.title = prepare(req.getParameter("title"));
		this.text = prepare(req.getParameter("text"));
	}

	/**
	 * Fills formular with properties from given {@link BlogEntry}.
	 * 
	 * @param blog object that contains required properties
	 */
	public void getFromBlogEntry(BlogEntry blog) {
		this.title = blog.getTitle();
		this.text = blog.getText();
	}

	/**
	 * Method sets properties of given blog from this formular.<br>
	 * Method should not be called if it's not validated before.
	 * 
	 * @param blog object that whose properties are set
	 */
	public void setBlogEntry(BlogEntry blog) {
		blog.setTitle(title);
		blog.setText(text);
	}
	
	@Override
	public void validate() {
		errors.clear();
		
		if (this.title.isEmpty()) {
			errors.put("title", "Title is mandatory!");
		}
		if (this.text.isEmpty()) {
			errors.put("text", "Text is mandatory");
		}	
	}

	/**
	 * Method returns formular's title.
	 * 
	 * @return formular's title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Method sets formular's title.
	 * 
	 * @param title new formular's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method returns formular's text.
	 * 
	 * @return formular's text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Method sets formular's text.
	 * 
	 * @param text new formular's text
	 */
	public void setText(String text) {
		this.text = text;
	}

}
