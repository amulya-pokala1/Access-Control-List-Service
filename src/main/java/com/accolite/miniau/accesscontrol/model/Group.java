package com.accolite.miniau.accesscontrol.model;

import java.util.List;

public class Group {

	private int groupId;
	private String groupName;
	private List<Permission> permissions;
	private List<User> users;
	private String groupDescription;

	public Group() {
		super();
	}

	public Group(String groupName, String groupDescription) {
		super();
		this.groupName = groupName;
		this.groupDescription = groupDescription;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

}
