package com.accolite.miniau.accesscontrol.dao;

import java.util.List;

import javax.sql.DataSource;

import com.accolite.miniau.accesscontrol.model.Permission;

public interface PermissionDAO {

	public void setDataSource(DataSource dataSource);

	public boolean createPermission(Permission permission);

	public boolean deletePermission(int permissionId);

	public List<Permission> getAllPermissionList();
}
