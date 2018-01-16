package com.accolite.miniau.accesscontrol.model;

import java.util.Map;

import com.accolite.miniau.accesscontrol.PermissionType;

public class User {

	int userId;
	String userName;
	String password;
	PermissionType permissionType;
	Map<String, String> attributes;
	public User(int userId, String userName, String permissionType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.permissionType = PermissionType.valueOf(permissionType);
	}

	public User(int userId, String userName, PermissionType permissionType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.permissionType = permissionType;
	}

	public User() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public PermissionType getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(PermissionType permissionType) {
		this.permissionType = permissionType;
	}
}
