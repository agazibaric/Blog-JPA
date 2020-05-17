package hr.fer.zemris.java.tecaj_13.dao;

/**
 * Class represents exception that happened during interacting with {@link DAO} object.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class DAOException extends RuntimeException {

	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that creates new {@link DAOException} object.
	 * 
	 * @param message message about invalid situation
	 * @param cause   cause of the exception
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor that creates new {@link DAOException} object.
	 * 
	 * @param message message about invalid situation
	 */
	public DAOException(String message) {
		super(message);
	}
	
}