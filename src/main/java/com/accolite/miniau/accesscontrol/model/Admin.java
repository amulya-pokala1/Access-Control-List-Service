package com.accolite.miniau.accesscontrol.model;

import javax.validation.constraints.Pattern;

public class Admin {

	private int adminId;
	private String adminName;
	private String password;
	private String description;
	private String mailId;

	public Admin(int adminId, String adminName, String password, String description, String mailId) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.password = password;
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
