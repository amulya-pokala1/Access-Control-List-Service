/*
 * 
 */
package com.accolite.miniau.accesscontrol.dao;

import java.util.List;

import javax.sql.DataSource;

import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDAO.
 */
public interface UserDAO {

	/**
	 * Sets the data source.
	 *
	 * @param dataSource
	 *            the new data source
	 */
	public void setDataSource(DataSource dataSource);

	/**
	 * Adds the new user.
	 *
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public boolean addNewUser(User user);

	/**
	 * Gets the user.
	 *
	 * @param userId
	 *            the user id
	 * @return the user
	 */
	public User getUser(int userId);

	/**
	 * Adds the permission to user.
	 *
	 * @param userId
	 *            the user id
	 * @param permissionId
	 *            the permission id
	 * @return true, if successful
	 */
	public boolean addPermissionToUser(int userId, int permissionId);

	/**
	 * Removes the permission from user.
	 *
	 * @param userId
	 *            the user id
	 * @param permissionId
	 *            the permission id
	 * @return true, if successful
	 */
	public boolean removePermissionFromUser(int userId, int permissionId);

	/**
	 * Delete user.
	 *
	 * @param userId
	 *            the user id
	 * @return true, if successful
	 */
	public boolean deleteUser(int userId);

	/**
	 * Update password.
	 *
	 * @param adminId
	 *            the admin id
	 * @param password
	 *            the password
	 * @return true, if successful
	 */
	public boolean updatePassword(int adminId, String password);

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public List<User> getAllUsers();

	/**
	 * Gets the all user names.
	 *
	 * @return the all user names
	 */
	public List<String> getAllUserNames();

	/**
	 * Gets the permission of user.
	 *
	 * @param userId
	 *            the user id
	 * @return the permission of user
	 */
	public List<Permission> getPermissionOfUser(int userId);

	/**
	 * Validate user.
	 *
	 * @param user
	 *            the user
	 * @return the int
	 */
	public int validateUser(User user);

	/**
	 * Gets the user id from URI.
	 *
	 * @param uri
	 *            the uri
	 * @return the user id from URI
	 */
	public Integer getUserIdFromURI(String uri);

	/**
	 * Gets the user id using email.
	 *
	 * @param email
	 *            the email
	 * @return the user id using email
	 */
	public Integer getUserIdUsingEmail(String email);

	/**
	 * Send password link.
	 *
	 * @param email
	 *            the email
	 */
	public void sendPasswordLink(String email);

}
