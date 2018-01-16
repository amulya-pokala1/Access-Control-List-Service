package com.accolite.miniau.accesscontrol.dao;

import javax.sql.DataSource;

import com.accolite.miniau.accesscontrol.model.Admin;

public interface AdminDAO {
	public boolean createAdmin(Admin admin);

	public boolean deleteAdmin(int adminId);

	public boolean changePassword(int adminId, String Password);

	public void setDataSource(DataSource dataSource);

}
