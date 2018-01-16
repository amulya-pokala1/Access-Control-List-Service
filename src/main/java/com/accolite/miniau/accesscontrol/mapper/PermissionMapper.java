package com.accolite.miniau.accesscontrol.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.miniau.accesscontrol.model.Permission;

public class PermissionMapper implements RowMapper<Permission> {

	@Override
	public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
		Permission permission = new Permission();
		permission.setPermissionId(rs.getInt("PERMISSION_ID"));
		permission.setPermissionName(rs.getString("PERMISSION_NAME"));
		permission.setPermissionDescription(rs.getString("PERMISSION_DESCRIPTION"));
		return permission;
	}

}
