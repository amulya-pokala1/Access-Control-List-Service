/*
 * 
 */
package com.accolite.miniau.accesscontrol.model;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.accolite.miniau.accesscontrol.utility.StringLiteral;

/**
 * The Class User.
 */
public class User {

	/** The user id. */
	private int userId;

	/** The user name. */
	@Length(max = 45)
	private String userName;

	/** The mail id. */
	@Length(max = 200)
	@Pattern(regexp = StringLiteral.EMAIL)
	private String mailId;

	/** The password. */
	@Length(min = 8, max = 25)
	private String password;

	/** The permissions. */
	private List<Permission> permissions;

	/**
	 * Instantiates a new user.
	 */
	public User() {

	}

	/**
	 * Instantiates a new user.
	 *
	 * @param userName
	 *            the user name
	 * @param mailId
	 *            the mail id
	 */
	public User(String userName, String mailId) {
		super();
		this.userName = userName;
		this.mailId = mailId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the permissions.
	 *
	 * @return the permissions
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * Sets the permissions.
	 *
	 * @param permissions
	 *            the new permissions
	 */
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	/**
	 * Gets the mail id.
	 *
	 * @return the mail id
	 */
	public String getMailId() {
		return mailId;
	}

	/**
	 * Sets the mail id.
	 *
	 * @param mailId
	 *            the new mail id
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", mailId=" + mailId + "]";
	}

}
