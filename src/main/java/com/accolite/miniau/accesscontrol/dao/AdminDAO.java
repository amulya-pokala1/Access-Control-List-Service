/*
 * 
 */
package com.accolite.miniau.accesscontrol.dao;

import javax.sql.DataSource;

import com.accolite.miniau.accesscontrol.model.Admin;

// TODO: Auto-generated Javadoc
/**
 * The Interface AdminDAO.
 */
public interface AdminDAO {
	
	/**
	 * Creates the admin.
	 *
	 * @param admin the admin
	 * @return true, if successful
	 */
	public boolean createAdmin(Admin admin);

	/**
	 * Delete admin.
	 *
	 * @param adminId the admin id
	 * @return true, if successful
	 */
	public boolean deleteAdmin(int adminId);

	/**
	 * Update password.
	 *
	 * @param adminId the admin id
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean updatePassword(int adminId, String password);

	/**
	 * Sets the data source.
	 *
	 * @param dataSource the new data source
	 */
	public void setDataSource(DataSource dataSource);

	/**
	 * Gets the admin id from URI.
	 *
	 * @param uri the uri
	 * @return the admin id from URI
	 */
	public Integer getAdminIdFromURI(String uri);

	/**
	 * Gets the admin id using email.
	 *
	 * @param email the email
	 * @return the admin id using email
	 */
	public Integer getAdminIdUsingEmail(String email);

	/**
	 * Send password link.
	 *
	 * @param email the email
	 */
	public void sendPasswordLink(String email);

}
