package hr.fer.zemris.java.tecaj_13.dao;

import hr.fer.zemris.java.tecaj_13.dao.jpa.JPADAOImpl;

/**
 * Singleton class that returns the service provider 
 * to access the data subsystem.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class DAOProvider {

	/**
	 * DAO object.
	 */
	private static DAO dao = new JPADAOImpl();
	
	/**
	 * Method returns DAO object.
	 * 
	 * @return DAO object
	 */
	public static DAO getDAO() {
		return dao;
	}
	
}