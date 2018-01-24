package com.accolite.miniau.accesscontrol.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.accolite.miniau.accesscontrol.utility.StringLiteral;

public class SuperAdmin {
	private int superAdminId;

	@Length(max = 50)
	private String superAdminName;

	@Length(min = 8, max = 256)
	private String password;

	@Pattern(regexp = StringLiteral.EMAIL)
	private String mailId;

	public SuperAdmin() {
		super();
	}

	public SuperAdmin(@Length(max = 50) String superAdminName,
			@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])") String mailId) {
		super();
		this.superAdminName = superAdminName;
		this.mailId = mailId;
	}

	public int getSuperAdminId() {
		return superAdminId;
	}

	public void setSuperAdminId(int superAdminId) {
		this.superAdminId = superAdminId;
	}

	public String getSuperAdminName() {
		return superAdminName;
	}

	public void setSuperAdminName(String superAdminName) {
		this.superAdminName = superAdminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

}
