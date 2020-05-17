package hr.fer.zemris.java.tecaj_13.dao.jpa;

import java.util.List;

import hr.fer.zemris.java.tecaj_13.dao.DAO;
import hr.fer.zemris.java.tecaj_13.dao.DAOException;
import hr.fer.zemris.java.tecaj_13.model.BlogComment;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

/**
 * Implementation of {@link DAO} interface that talks with database.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class JPADAOImpl implements DAO {

	@Override
	public BlogEntry getBlogEntry(Long id) throws DAOException {
		return JPAEMProvider.getEntityManager().find(BlogEntry.class, id);
	}

	@Override
	public List<BlogUser> getBlogUsers() throws DAOException {
		return JPAEMProvider.getEntityManager()
				.createNamedQuery("BlogUser.queryAll", BlogUser.class)
				.getResultList();
	}

	@Override
	public void addNewUser(BlogUser user) throws DAOException {
		JPAEMProvider.getEntityManager().persist(user);
	}

	@Override
	public BlogUser getBlogUserWithNick(String nick) throws DAOException {
		List<BlogUser> users = JPAEMProvider.getEntityManager()
				.createNamedQuery("BlogUser.queryNick", BlogUser.class)
				.setParameter("nick", nick)
				.getResultList();
		
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public void addBlogEntry(BlogEntry blog) throws DAOException {
		JPAEMProvider.getEntityManager().persist(blog);
	}

	@Override
	public void addBlogComment(BlogComment comment) throws DAOException {
		JPAEMProvider.getEntityManager().persist(comment);
	}

}