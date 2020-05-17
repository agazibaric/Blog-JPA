package hr.fer.zemris.java.tecaj_13.web.servlets.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class offers method that crypts password with SHA-1 algorithm.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class Crypto {

	private static final String shaAlgorithm = "SHA-1";

	public static String getPasswordHash(String password)  {
		try {
			MessageDigest mDigest = MessageDigest.getInstance(shaAlgorithm);
			mDigest.update(password.getBytes());
			return bytesToHex(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method checks if given password matches passwordHash 
	 * that is produced password hashing algorithm.
	 * 
	 * @param password     password that is checked
	 * @param passwordHash expected password hash
	 * @return             <code>true</code> if password is valid, 
	 * 					   <code>false</code> otherwise
	 */
	public static boolean checkPassword(String password, String passwordHash) {
		return passwordHash.equals(getPasswordHash(password));
	}
	
	/**
	 * Method transforms given byte array into hexadecimal representation of given array.
	 * 
	 * @param bytes byte array from which hexadecimal string is created
	 * @return      hexadecimal representation of given byte array
	 */
	private static String bytesToHex(byte[] bytes) {
		int length = 2 * bytes.length;
		StringBuilder hexBuilder = new StringBuilder(length);
		for (byte b : bytes) {
			hexBuilder.append(String.format("%02x", b));
		}
		return hexBuilder.toString();
	}
	
}
