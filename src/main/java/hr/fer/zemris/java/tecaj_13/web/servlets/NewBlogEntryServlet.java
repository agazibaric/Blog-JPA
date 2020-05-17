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
import hr.fer.zemris.java.tecaj_13.model.BlogUser;
import hr.fer.zemris.java.tecaj_13.web.servlets.formulars.BlogEntryFormular;

/**
 * Web servlet that creates new blog entry.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
@WebServlet("/servleti/new")
public class NewBlogEntryServlet extends HttpServlet {

	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nick = req.getParameter("nick");
		Object loggedUser = req.getSession().getAttribute("currentUserNick");
		if (loggedUser == null || !loggedUser.toString().equals(nick)) {
			resp.sendError(403);
			return;
		}
		
		BlogEntryFormular formular = new BlogEntryFormular();
		req.setAttribute("form", formular);
		req.setAttribute("nick", nick);
		req.getRequestDispatcher("/WEB-INF/pages/newBlogEntry.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nick = req.getParameter("nick");
		Object loggedUser = req.getSession().getAttribute("currentUserNick");
		if (loggedUser == null || !loggedUser.toString().equals(nick)) {
			resp.sendError(403);
			return;
		}
		
		BlogEntryFormular formular = new BlogEntryFormular();
		formular.getFromHttpRequest(req);
		formular.validate();
		
		if (formular.hasErrors()) {
			req.setAttribute("form", formular);
			req.setAttribute("nick", nick);
			req.getRequestDispatcher("/WEB-INF/pages/newBlogEntry.jsp").forward(req, resp);;
			return;
		}
		
		BlogEntry blog = new BlogEntry();
		formular.setBlogEntry(blog);
		blog.setCreatedAt(new Date());
		BlogUser owner = DAOProvider.getDAO().getBlogUserWithNick(nick);
		blog.setCreator(owner);
		DAOProvider.getDAO().addBlogEntry(blog);
		
		resp.sendRedirect(req.getContextPath() + "/servleti/author/" + owner.getNick());
	}
	
}
