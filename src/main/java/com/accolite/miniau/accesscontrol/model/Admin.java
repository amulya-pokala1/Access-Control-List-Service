/*
 * 
 */
package com.accolite.miniau.accesscontrol.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.accolite.miniau.accesscontrol.utility.RegexExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class Admin.
 */
public class Admin {

	/** The admin id. */
	private int adminId;

	/** The admin name. */
	@Length(max = 45)
	private String adminName;

	/** The password. */
	@Length(min = 8, max = 25)
	private String password;

	/** The description. */
	@Length(max = 100)
	private String description;

	/** The mail id. */
	@Pattern(regexp = RegexExpression.EMAIL)
	private String mailId;

	/**
	 * Instantiates a new admin.
	 */
	public Admin() {

	}

	public Admin(@Length(max = 45) String adminName, @Length(max = 100) String description,
			@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])") String mailId) {
		super();
		this.adminName = adminName;
		this.description = description;
		this.mailId = mailId;
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

	/**
	 * Gets the admin id.
	 *
	 * @return the admin id
	 */
	public int getAdminId() {
		return adminId;
	}

	/**
	 * Sets the admin id.
	 *
	 * @param adminId
	 *            the new admin id
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	/**
	 * Gets the admin name.
	 *
	 * @return the admin name
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * Sets the admin name.
	 *
	 * @param adminName
	 *            the new admin name
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
