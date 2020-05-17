package hr.fer.zemris.java.tecaj_13.dao.jpa;

import javax.persistence.EntityManagerFactory;

/**
 * Provider of {@link EntityManagerFactory} object.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class JPAEMFProvider {
	
	/**
	 * EntityManagerFactory object
	 */
	public static EntityManagerFactory emf;
	
	/**
	 * Method returns {@link EntityManagerFactory} object.
	 * 
	 * @return {@link EntityManagerFactory} object
	 */
	public static EntityManagerFactory getEmf() {
		return emf;
	}
	
	/**
	 * Method sets {@link EntityManagerFactory} object.
	 * 
	 * @param emf {@link EntityManagerFactory} object
	 */
	public static void setEmf(EntityManagerFactory emf) {
		JPAEMFProvider.emf = emf;
	}
	
}