package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

/**
 * Web servlet that processes request of the author's blog.
 * It expects nick name in url path whose blogs are showed.
 * It also  processes request that wants to create new blog or
 * to show existing blog.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
@WebServlet("/servleti/author/*")
public class AuthorServlet extends HttpServlet {

	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		if (pathInfo == null) {
			resp.sendRedirect(req.getContextPath() + "/servleti/main");
			return;
		}
		
		String[] pathInfos = pathInfo.substring(1).split("/");
		if (pathInfos.length == 1) {
			String nick = pathInfos[0];
			BlogUser user = DAOProvider.getDAO().getBlogUserWithNick(nick);
			if (user == null) {
				resp.sendRedirect(req.getContextPath() + "/servleti/main");
				return;
			}
			
			req.setAttribute("nick", nick);
			req.setAttribute("blogs", user.getBlogEntries());
			req.getRequestDispatcher("/WEB-INF/pages/author.jsp").forward(req, resp);
			return;
		}
		
		if (pathInfos.length == 2) {
			String nick = pathInfos[0];
			String parameter = pathInfos[1];
			req.setAttribute("nick", nick);
			if (parameter.equals("new")) {
				resp.sendRedirect(req.getContextPath() + "/servleti/new?nick=" + nick);
				return;
			}

			Long blogID = null;
			try {
				blogID = Long.parseLong(parameter);
			} catch (NumberFormatException ex) {
				resp.sendRedirect(req.getContextPath() + "/servleti/author/" + nick);
				return;
			}
			
			BlogEntry blog = DAOProvider.getDAO().getBlogEntry(blogID);
			if (blog == null) {
				resp.sendError(400);
				return;
			}
			
			req.setAttribute("blog", blog);
			req.getRequestDispatcher("/WEB-INF/pages/showBlogEntry.jsp").forward(req, resp);;
			return;
		}
		if (pathInfos.length == 3) {
			String nick = pathInfos[0];
			String parameter = pathInfos[1];
			String blogID = pathInfos[2];
			Object currentUser = req.getSession().getAttribute("currentUserNick");
			if (currentUser == null || !currentUser.toString().equals(nick)) {
				resp.sendError(403);
				return;
			}
			if(parameter.equals("edit")) {
				resp.sendRedirect(req.getContextPath() + "/servleti/edit?nick=" + nick + "&blogID=" + blogID);
				return;
			}
		}
		
		// Else: invalid path, send error
		resp.sendError(400);
	}
}
