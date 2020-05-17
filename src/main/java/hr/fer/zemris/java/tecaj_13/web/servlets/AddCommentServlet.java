package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogComment;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

/**
 * Web servlet that adds new comment to specified blog.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
@WebServlet("/servleti/addComment")
public class AddCommentServlet extends HttpServlet {

	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		long blogID;
		try {
			blogID = Long.parseLong(req.getParameter("blogID"));
		} catch (NumberFormatException | NullPointerException ex) {
			resp.sendError(400);
			return;
		}
		
		BlogEntry blog = DAOProvider.getDAO().getBlogEntry(blogID);
		String nick = req.getParameter("nick");
		if (nick == null) {
			resp.sendError(400);
			return;
		}
		
		req.setAttribute("nick", nick);
		req.setAttribute("blog", blog);
		
		String message = req.getParameter("message");
		if (message == null || message.trim().isEmpty()) {
			resp.sendRedirect(req.getContextPath() + "/servleti/author/" + nick + "/" + blogID);
			return;
		}
		
		Object currentUserObj = req.getSession().getAttribute("currentUserNick");
		String currentUser = null;
		String email = null;
		if (currentUserObj == null) {
			email = req.getParameter("email");
			if (email == null || email.trim().isEmpty()) {
				resp.sendRedirect(req.getContextPath() + "/servleti/author/" + nick + "/" + blogID);
				return;
			}
		} else {
			currentUser = currentUserObj.toString();
			BlogUser user = DAOProvider.getDAO().getBlogUserWithNick(currentUser);
			email = user.getEmail();
		}
		
		BlogComment comment = new BlogComment();
		comment.setMessage(message);
		comment.setBlogEntry(blog);
		comment.setPostedOn(new Date());
		comment.setUsersEMail(email);
		DAOProvider.getDAO().addBlogComment(comment);
		
		resp.sendRedirect(req.getContextPath() + "/servleti/author/" + nick + "/" + blogID);
	}
	
}
