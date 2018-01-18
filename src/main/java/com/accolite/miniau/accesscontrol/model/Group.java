package com.accolite.miniau.accesscontrol.model;

import java.util.List;

import org.hibernate.validator.constraints.Length;

public class Group {

	private int groupId;

	@Length(max = 100)
	private String groupName;

	@Length(max = 250)
	private String groupDescription;

	private List<Permission> permissions;
	private List<User> users;

	public Group() {
		super();
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
