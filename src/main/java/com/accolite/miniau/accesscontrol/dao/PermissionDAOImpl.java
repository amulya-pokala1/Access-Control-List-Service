package com.accolite.miniau.accesscontrol.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.miniau.accesscontrol.mapper.PermissionMapper;
import com.accolite.miniau.accesscontrol.model.Permission;

public class PermissionDAOImpl implements PermissionDAO {

	JdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean createPermission(Permission permission) {
		String sql = "INSERT INTO PERMISSION(PERMISSION_NAME,PERMISSION_DESCRIPTION) VALUES (?,?)";
		int count = jdbcTemplate.update(sql, permission.getPermissionName(), permission.getPermissionDescription());
		return (count > 0);
	}

	@Override
	public boolean deletePermission(int permissionId) {
		String sql = "DELETE FROM PERMISSION WHERE PERMISSION_ID = ?";
		int count = jdbcTemplate.update(sql, permissionId);
		return (count > 0);
	}

	@Override
	public List<Permission> getAllPermissionList() {
		String sql = "SELECT * FROM PERMISSION";
		List<Permission> permissions = jdbcTemplate.query(sql, new PermissionMapper());
		return permissions;
	}

}
