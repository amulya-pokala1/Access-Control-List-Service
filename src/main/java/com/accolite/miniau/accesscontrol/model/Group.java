package com.accolite.miniau.accesscontrol.model;

import java.util.List;

import com.accolite.miniau.accesscontrol.PermissionType;

public class Group {

	private int groupId;
	private String groupName;
	private PermissionType permissionType;
	private List<User> users;

	public Group(int groupId, String groupName, PermissionType permissionType) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.permissionType = permissionType;

	}

	public Group(int groupId, String groupName, String permissionType) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.permissionType = PermissionType.valueOf(permissionType);

	}

	public Group() {

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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public PermissionType getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(PermissionType permissionType) {
		this.permissionType = permissionType;
	}

}
