package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;
import hr.fer.zemris.java.tecaj_13.web.servlets.formulars.RegistrationFormular;

/**
 * Web servlet that performs registration request of anonymous user to the blog.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
@WebServlet("/servleti/register")
public class RegisterServlet extends HttpServlet {

	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RegistrationFormular formular = new RegistrationFormular();
		req.setAttribute("form", formular);
		req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RegistrationFormular formular = new RegistrationFormular();
		formular.getFromHttpRequest(req);
		formular.validate();
		
		if (formular.hasErrors()) {
			req.setAttribute("form", formular);
			req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
			return;
		}
		
		if (DAOProvider.getDAO().getBlogUserWithNick(formular.getNick()) != null) {
			formular.setError("nick", "Chosen nick is already used");
			req.setAttribute("form", formular);
			req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
			return;
		}
		
		BlogUser user = new BlogUser();
		formular.setBlogUser(user);
		DAOProvider.getDAO().addNewUser(user);
		resp.sendRedirect(req.getContextPath() + "/servleti/main");
		
	}

}
