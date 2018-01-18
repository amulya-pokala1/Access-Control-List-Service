package com.accolite.miniau.accesscontrol.model;

import org.hibernate.validator.constraints.Length;

public class Permission {

	int permissionId;
	@Length(max = 30)
	String permissionName;
	@Length(max = 100)
	String permissionDescription;
	boolean mandatory;

	public Permission() {
		super();
	}

	public Permission(String permission, String permissionDescription, boolean isMandatory) {
		super();
		this.permissionName = permission;
		this.permissionDescription = permissionDescription;
		this.mandatory = isMandatory;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionDescription() {
		return permissionDescription;
	}

	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.mandatory = isMandatory;
	}

	@Override
	public String toString() {
		return "Permission [permissionName=" + permissionName + ", permissionDescription=" + permissionDescription
				+ ", isMandatory=" + mandatory + "]";
	}

}
