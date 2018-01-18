/*
 * 
 */
package com.accolite.miniau.accesscontrol.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class HashUtility.
 */
public class HashUtility {

	/** The logger. */
	private static Logger logger = Logger.getLogger(HashUtility.class);

	/** The Constant SALT. */
	/* *IMPORTANT* DONT CHANGE THE SALT */
	private static final String SALT = "dvnewofq0Q!i2i0320_2u4t34jgow@dsmv";
	/* IMPORTANT* DONT CHANGE THE SALT */

	/**
	 * Instantiates a new hash utility.
	 */
	private HashUtility() {

	}

	/**
	 * Hash password.
	 *
	 * @param passwordToHash the password to hash
	 * @return the string
	 */
	public static String hashPassword(String passwordToHash) {

		return generateHash(passwordToHash, SALT);
	}

	/**
	 * Creates the unique uri path.
	 *
	 * @param userId the user id
	 * @param userName the user name
	 * @return the string
	 */
	public static String createUniqueUriPath(Integer userId, String userName) {
		Double number = Math.ceil(Math.random() * 100) * userId;
		String salt = number.toString();
		return generateHash(userName, salt);
	}

	/**
	 * Generate hash.
	 *
	 * @param stringToHash the string to hash
	 * @param salt the salt
	 * @return the string
	 */
	public static String generateHash(String stringToHash, String salt) {
		String generatedPassword = null;
		try {

			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes("UTF-8"));

			byte[] bytes;
			bytes = md.digest(stringToHash.getBytes("UTF-8"));

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			logger.error("Problem with Hashing function.", e);
		}

		return generatedPassword;
	}
}
