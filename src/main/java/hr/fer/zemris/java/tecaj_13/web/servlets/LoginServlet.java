package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;
import hr.fer.zemris.java.tecaj_13.web.servlets.formulars.LoginFormular;
import hr.fer.zemris.java.tecaj_13.web.servlets.util.Crypto;

/**
 * Web servlet that processes request of user's login to the blog.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
@WebServlet("/servleti/login")
public class LoginServlet extends HttpServlet{

	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/servleti/main");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		LoginFormular formular = new LoginFormular();
		formular.getFromHttpRequest(req);
		formular.validate();
		
		if (formular.hasErrors()) {
			req.setAttribute("form", formular);
			req.getRequestDispatcher("/WEB-INF/pages/mainPage.jsp").forward(req, resp);
			return;
		}
		
		BlogUser user = DAOProvider.getDAO().getBlogUserWithNick(formular.getNick());
		if (user == null) {
			formular.setError("nick", "Nonexistent nick name");
			req.getRequestDispatcher("/WEB-INF/pages/mainPage.jsp").forward(req, resp);
			return;
		}
		
		if (!Crypto.checkPassword(formular.getPassword(), user.getPasswordHash())) {
			formular.setError("password", "Invalid password for given username");
			req.getRequestDispatcher("/WEB-INF/pages/mainPage.jsp").forward(req, resp);
			return;
		}
		
		req.getSession().setAttribute("currentUserId", user.getId());
		req.getSession().setAttribute("currentUserFn", user.getFirstName());
		req.getSession().setAttribute("currentUserLn", user.getLastName());
		req.getSession().setAttribute("currentUserNick", user.getNick());
		
		resp.sendRedirect(req.getContextPath() + "/servleti/main");
	}
	
}
