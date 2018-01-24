package com.accolite.miniau.accesscontrol.dao;

import javax.sql.DataSource;

import com.accolite.miniau.accesscontrol.model.SuperAdmin;

public interface SuperAdminDAO {

	public boolean updateSuperAdmin(SuperAdmin superAdmin);

	public void setDataSource(DataSource dataSource);

	public Integer authenticate(String email, String pswd);

	public void sendPasswordLink(String email, String ip, int port);

}
