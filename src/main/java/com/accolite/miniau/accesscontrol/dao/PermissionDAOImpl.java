package com.accolite.miniau.accesscontrol.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.miniau.accesscontrol.mapper.PermissionMapper;
import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.utility.Query;

public class PermissionDAOImpl implements PermissionDAO {

	JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	public boolean createPermission(Permission permission) {
		int count = jdbcTemplate.update(Query.CREATEPERMISSION, permission.getPermissionName(),
				permission.getPermissionDescription());
		return (count > 0);
	}

	
	public boolean deletePermission(int permissionId) {
		int count = jdbcTemplate.update(Query.DELETEPERMISSION, permissionId);
		return (count > 0);
	}

	
	public List<Permission> getAllPermissionList() {
		List<Permission> permissions = jdbcTemplate.query(Query.GETALLPERMISSIONLIST, new PermissionMapper());
		return permissions;
	}

}
