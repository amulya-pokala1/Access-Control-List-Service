package com.accolite.miniau.accesscontrol.model;

public class Admin {
	private int adminId;
	private String adminName;
	private String password;
	private String description;

	public Admin(int adminId, String adminName, String password, String description) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.password = password;
		this.description = description;
	}
	public Admin()
	{
		
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
