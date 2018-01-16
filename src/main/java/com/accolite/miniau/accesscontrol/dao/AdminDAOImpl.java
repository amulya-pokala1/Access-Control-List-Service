package com.accolite.miniau.accesscontrol.dao;

import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.miniau.accesscontrol.model.Admin;
import com.accolite.miniau.accesscontrol.utility.Query;

public class AdminDAOImpl implements AdminDAO {

	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(com.accolite.miniau.accesscontrol.dao.AdminDAOImpl.class);

	public AdminDAOImpl() {
		BasicConfigurator.configure();
	}

	public boolean createAdmin(Admin admin) {

		int rowsAffected = jdbcTemplate.update(Query.CREATEADMIN, admin.getAdminId(), admin.getAdminName(),
				admin.getPassword(), admin.getDescription());
		if (rowsAffected == 0) {
			logger.error("couldn't insert" + admin.getAdminId() + " into the admin table");
		}
		logger.info("inserted " + admin.getAdminId() + "into  admin successfully");
		return true;

	}

	public boolean deleteAdmin(int adminId) {
		int rowsAffected = jdbcTemplate.update(Query.DELETEADMIN, adminId);
		if (rowsAffected == 0) {
			logger.error("couldn't delete" + adminId + " from delete table");
		}
		logger.info("deleted " + adminId + "from admin table");
		return true;
	}

	public boolean changePassword(int adminId, String password) {

		int rowsAffected = jdbcTemplate.update(Query.CHANGEPASSWORD, password, adminId);
		if (rowsAffected == 0) {
			logger.error("couldn't update password");
		}
		logger.info("changed password");
		return true;
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("setting the data source has been successful");

	}
}
