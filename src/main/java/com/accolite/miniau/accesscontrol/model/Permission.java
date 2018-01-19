/*
 * 
 */
package com.accolite.miniau.accesscontrol.model;

import org.hibernate.validator.constraints.Length;

// TODO: Auto-generated Javadoc
/**
 * The Class Permission.
 */
public class Permission {

	/** The permission id. */
	int permissionId;

	/** The permission name. */
	@Length(max = 30)
	String permissionName;

	/** The permission description. */
	@Length(max = 100)
	String permissionDescription;

	/** The mandatory. */
	boolean mandatory;

	/**
	 * Instantiates a new permission.
	 */
	public Permission() {
		super();
	}

	/**
	 * Instantiates a new permission.
	 *
	 * @param permission
	 *            the permission
	 * @param permissionDescription
	 *            the permission description
	 * @param isMandatory
	 *            the is mandatory
	 */
	public Permission(String permission, String permissionDescription, boolean isMandatory) {
		super();
		this.permissionName = permission;
		this.permissionDescription = permissionDescription;
		this.mandatory = isMandatory;
	}

	/**
	 * Gets the permission id.
	 *
	 * @return the permission id
	 */
	public int getPermissionId() {
		return permissionId;
	}

	/**
	 * Sets the permission id.
	 *
	 * @param permissionId
	 *            the new permission id
	 */
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * Gets the permission name.
	 *
	 * @return the permission name
	 */
	public String getPermissionName() {
		return permissionName;
	}

	/**
	 * Sets the permission name.
	 *
	 * @param permissionName
	 *            the new permission name
	 */
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	/**
	 * Gets the permission description.
	 *
	 * @return the permission description
	 */
	public String getPermissionDescription() {
		return permissionDescription;
	}

	/**
	 * Sets the permission description.
	 *
	 * @param permissionDescription
	 *            the new permission description
	 */
	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}

	/**
	 * Checks if is mandatory.
	 *
	 * @return true, if is mandatory
	 */
	public boolean isMandatory() {
		return mandatory;
	}

	/**
	 * Sets the mandatory.
	 *
	 * @param isMandatory
	 *            the new mandatory
	 */
	public void setMandatory(boolean isMandatory) {
		this.mandatory = isMandatory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Permission [permissionName=" + permissionName + ", permissionDescription=" + permissionDescription
				+ ", isMandatory=" + mandatory + "]";
	}

}
