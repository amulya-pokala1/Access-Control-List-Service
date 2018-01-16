package com.accolite.miniau.accesscontrol.model;

public class Permission {

	int permissionId;
	String permission;
	String permissionDescription;
	boolean isMandatory;

	public Permission() {
		super();
	}

	public Permission(String permission, String permissionDescription, boolean isMandatory) {
		super();
		this.permission = permission;
		this.permissionDescription = permissionDescription;
		this.isMandatory = isMandatory;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getPermissionDescription() {
		return permissionDescription;
	}

	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

}
