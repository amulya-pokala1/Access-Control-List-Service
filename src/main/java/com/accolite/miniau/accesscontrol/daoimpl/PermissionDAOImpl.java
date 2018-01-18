package com.accolite.miniau.accesscontrol.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.miniau.accesscontrol.dao.PermissionDAO;
import com.accolite.miniau.accesscontrol.mapper.PermissionMapper;
import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.utility.Query;

public class PermissionDAOImpl implements PermissionDAO {

	private static final Logger logger = Logger.getLogger(PermissionDAOImpl.class);

	JdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean createPermission(Permission permission) {
		logger.info("Creating permission " + permission.getPermissionName());
		int count = jdbcTemplate.update(Query.CREATEPERMISSION, permission.getPermissionName(),
				permission.getPermissionDescription(), permission.isMandatory());
		if (count > 0) {
			logger.info("Permission created!");
			return true;
		}
		logger.warn("Permission Not created!");
		return false;
	}

	@Override
	public boolean deletePermission(int permissionId) {
		logger.info("Deleting permission with id " + permissionId);
		int count = jdbcTemplate.update(Query.DELETEPERMISSION, permissionId);
		if (count > 0) {
			logger.info("Deleted permission with id "+permissionId);
			return true;
		}
		logger.warn("Could not delete permission with id "+permissionId);
		return false;
	}

	@Override
	public List<Permission> getAllPermissionList() {
		logger.info("Getting all permissions");
		List<Permission> permissions = jdbcTemplate.query(Query.GETALLPERMISSIONLIST, new PermissionMapper());
		return permissions;
	}

}
