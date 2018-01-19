package com.accolite.miniau.accesscontrol.model;

public class Admin {
	private int adminId;
	private String adminName;
	private String password;
	private String description;
	private String mailId;

	public Admin(String adminName, String description, String mailId) {
		super();
		this.adminName = adminName;
		this.description = description;
		this.mailId = mailId;
	}

	public Admin() {

	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
