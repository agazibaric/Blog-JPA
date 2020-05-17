package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.web.servlets.formulars.BlogEntryFormular;

/**
 * Web servlet that edits specified blog.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
@WebServlet("/servleti/edit")
public class EditBlogEntryServlet extends HttpServlet {

	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BlogEntryFormular formular = new BlogEntryFormular();
		long blogID;
		try {
			blogID = Long.parseLong(req.getParameter("blogID"));
		} catch (NumberFormatException | NullPointerException ex) {
			return;
		}
		
		String nick = req.getParameter("nick");
		Object loggedUser = req.getSession().getAttribute("currentUserNick");
		if (loggedUser == null || !loggedUser.toString().equals(nick)) {
			resp.sendError(403);
			return;
		}
		
		BlogEntry blog = DAOProvider.getDAO().getBlogEntry(blogID);
		formular.getFromBlogEntry(blog);
		req.setAttribute("form", formular);
		req.setAttribute("blogID", blogID);
		req.setAttribute("nick", nick);
		req.getRequestDispatcher("/WEB-INF/pages/editBlogEntry.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nick = req.getParameter("nick");
		Object loggedUser = req.getSession().getAttribute("currentUserNick");
		if (loggedUser == null || !loggedUser.toString().equals(nick)) {
			resp.sendError(403);
			return;
		}
		
		long blogID;
		try {
			blogID = Long.parseLong(req.getParameter("blogID"));
		} catch (NumberFormatException | NullPointerException ex) {
			resp.sendError(400);
			return;
		}
		
		BlogEntryFormular formular = new BlogEntryFormular();
		formular.getFromHttpRequest(req);
		formular.validate();
		
		if (formular.hasErrors()) {
			req.setAttribute("form", formular);
			req.setAttribute("nick", nick);
			req.setAttribute("blogID", blogID);
			req.getRequestDispatcher("/WEB-INF/pages/editBlogEntry.jsp").forward(req, resp);;
			return;
		}
		
		BlogEntry blog = DAOProvider.getDAO().getBlogEntry(blogID);
		formular.setBlogEntry(blog);
		blog.setLastModifiedAt(new Date());
		
		resp.sendRedirect(req.getContextPath() + "/servleti/author/" + nick);
	}
	
}
