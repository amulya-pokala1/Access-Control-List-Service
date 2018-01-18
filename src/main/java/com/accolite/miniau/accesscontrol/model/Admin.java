package com.accolite.miniau.accesscontrol.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.accolite.miniau.accesscontrol.utility.RegexExpression;

public class Admin {

	private int adminId;

	@Length(max = 45)
	private String adminName;

	@Length(min = 8, max = 25)
	private String password;

	@Length(max = 100)
	private String description;

	@Pattern(regexp = RegexExpression.EMAIL)
	private String mailId;

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
