/*
 * 
 */
package com.accolite.miniau.accesscontrol.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.accolite.miniau.accesscontrol.model.Permission;

// TODO: Auto-generated Javadoc
/**
 * The Class PermissionMapper.
 */
public class PermissionMapper implements RowMapper<Permission> {

	/** The logger. */
	private static Logger logger = Logger.getLogger(PermissionMapper.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public Permission mapRow(ResultSet rs, int rowNum) {
		Permission permission = new Permission();
		try {
			permission.setPermissionId(rs.getInt("PERMISSION_ID"));
			permission.setPermissionName(rs.getString("PERMISSION_NAME"));
			permission.setPermissionDescription(rs.getString("PERMISSION_DESCRIPTION"));
		} catch (SQLException e) {
			logger.error("Error parsing Permission", e);
		}
		return permission;
	}

}
