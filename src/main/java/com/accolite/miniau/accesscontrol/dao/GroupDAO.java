/*
 * 
 */
package com.accolite.miniau.accesscontrol.dao;

import java.util.List;

import javax.sql.DataSource;

import com.accolite.miniau.accesscontrol.model.Group;
import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.model.User;

/**
 * The Interface GroupDAO.
 */
public interface GroupDAO {

	/**
	 * Sets the data source.
	 *
	 * @param dataSource
	 *            the new data source
	 */
	public void setDataSource(DataSource dataSource);

	/**
	 * Adds the new group.
	 *
	 * @param group
	 *            the group
	 * @return true, if successful
	 */
	public boolean addNewGroup(Group group);

	/**
	 * Adds the permission.
	 *
	 * @param groupId
	 *            the group id
	 * @param permissionId
	 *            the permission id
	 * @return true, if successful
	 */
	public boolean addPermission(int groupId, int permissionId);

	/**
	 * Removes the permission.
	 *
	 * @param groupId
	 *            the group id
	 * @param permissionId
	 *            the permission id
	 * @return true, if successful
	 */
	public boolean removePermission(int groupId, int permissionId);

	/**
	 * Gets the all group names.
	 *
	 * @return the all group names
	 */
	public List<String> getAllGroupNames();

	/**
	 * Gets the all groups.
	 *
	 * @return the all groups
	 */
	public List<Group> getAllGroups();

	/**
	 * Gets the users in group.
	 *
	 * @param groupId
	 *            the group id
	 * @return the users in group
	 */
	public List<User> getUsersInGroup(int groupId);

	/**
	 * Adds the user to group.
	 *
	 * @param groupId
	 *            the group id
	 * @param userId
	 *            the user id
	 * @return true, if successful
	 */
	public boolean addUserToGroup(int groupId, int userId);

	/**
	 * Removes the user from group.
	 *
	 * @param groupId
	 *            the group id
	 * @param userId
	 *            the user id
	 * @return true, if successful
	 */
	public boolean removeUserFromGroup(int groupId, int userId);

	/**
	 * Delete group.
	 *
	 * @param groupId
	 *            the group id
	 * @return true, if successful
	 */
	public boolean deleteGroup(int groupId);

	public List<User> getUsersNotInGroup(int groupId);

	public List<Permission> getGroupPermissions(int groupId);

	public List<Permission> getPermissionNotInGroup(int groupId);

}
