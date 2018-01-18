/*
 * 
 */
package com.accolite.miniau.accesscontrol.model;

import java.util.List;

import org.hibernate.validator.constraints.Length;

// TODO: Auto-generated Javadoc
/**
 * The Class Group.
 */
public class Group {

	/** The group id. */
	private int groupId;

	/** The group name. */
	@Length(max = 100)
	private String groupName;

	/** The group description. */
	@Length(max = 250)
	private String groupDescription;

	/** The permissions. */
	private List<Permission> permissions;
	
	/** The users. */
	private List<User> users;

	/**
	 * Instantiates a new group.
	 */
	public Group() {
		super();
	}

	/**
	 * Gets the group id.
	 *
	 * @return the group id
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * Sets the group id.
	 *
	 * @param groupId the new group id
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * Gets the group name.
	 *
	 * @return the group name
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Sets the group name.
	 *
	 * @param groupName the new group name
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	 * @param permissions the new permissions
	 */
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Gets the group description.
	 *
	 * @return the group description
	 */
	public String getGroupDescription() {
		return groupDescription;
	}

	/**
	 * Sets the group description.
	 *
	 * @param groupDescription the new group description
	 */
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

}
