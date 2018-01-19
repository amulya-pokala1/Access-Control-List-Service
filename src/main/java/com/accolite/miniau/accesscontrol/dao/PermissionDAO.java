/*
 * 
 */
package com.accolite.miniau.accesscontrol.dao;

import java.util.List;

import javax.sql.DataSource;

import com.accolite.miniau.accesscontrol.model.Permission;

/**
 * The Interface PermissionDAO.
 */
public interface PermissionDAO {

	/**
	 * Sets the data source.
	 *
	 * @param dataSource
	 *            the new data source
	 */
	public void setDataSource(DataSource dataSource);

	/**
	 * Creates the permission.
	 *
	 * @param permission
	 *            the permission
	 * @return true, if successful
	 */
	public boolean createPermission(Permission permission);

	/**
	 * Delete permission.
	 *
	 * @param permissionId
	 *            the permission id
	 * @return true, if successful
	 */
	public boolean deletePermission(int permissionId);

	/**
	 * Gets the all permission list.
	 *
	 * @return the all permission list
	 */
	public List<Permission> getAllPermissionList();
}
