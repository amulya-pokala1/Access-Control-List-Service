package com.accolite.miniau.accesscontrol.model;

import java.util.List;

public class User {

	private int userId;
	private String userName;
	private String password;
	private List<Permission> permissions;

	private String mailId;

	public User() {

	}

	public User(String userName, String mailId) {
		super();
		this.userName = userName;
		this.mailId = mailId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", mailId=" + mailId + "]";
	}

}
