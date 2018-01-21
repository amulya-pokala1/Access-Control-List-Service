package com.accolite.miniau.accesscontrol.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.miniau.accesscontrol.model.Admin;

public class AdminMapper implements RowMapper<Admin> {

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin = new Admin();
		admin.setAdminId(rs.getInt("ADMIN_ID"));
		admin.setAdminName(rs.getString("ADMIN_NAME"));
		admin.setMailId(rs.getString("MAIL_ID"));
		admin.setDescription(rs.getString("description"));
		return admin;
	}

}
