package com.accolite.miniau.accesscontrol.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.miniau.accesscontrol.dao.PermissionDAO;
import com.accolite.miniau.accesscontrol.mapper.PermissionMapper;
import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.utility.Query;

public class PermissionDAOImpl implements PermissionDAO {

	JdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean createPermission(Permission permission) {
		System.out.println(permission);
		int count = jdbcTemplate.update(Query.CREATEPERMISSION, permission.getPermissionName(),
				permission.getPermissionDescription(), permission.isMandatory());
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePermission(int permissionId) {
		int count = jdbcTemplate.update(Query.DELETEPERMISSION, permissionId);
		return (count > 0);
	}

	@Override
	public List<Permission> getAllPermissionList() {
		List<Permission> permissions = jdbcTemplate.query(Query.GETALLPERMISSIONLIST, new PermissionMapper());
		return permissions;
	}

}
