package com.accolite.miniau.accesscontrol.dao;

import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.miniau.accesscontrol.model.Admin;

public class AdminDAOImpl implements AdminDAO {

	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(com.accolite.miniau.accesscontrol.dao.UserDAOImpl.class);

	public AdminDAOImpl() {
		BasicConfigurator.configure();
	}

	public boolean createAdmin(Admin admin) {

		String query = "INSERT INTO ACL.ADMIN(ADMIN_ID,ADMIN_NAME,PASSWORD, DESCRIPTION) VALUES (?,?,?,?)";
		int rowsAffected = jdbcTemplate.update(query, admin.getAdminId(), admin.getAdminName(), admin.getPassword(),
				admin.getDescription());
		if (rowsAffected == 0) {
			logger.error("couldn't insert" + admin.getAdminId() + " into the admin table");
		}
		logger.info("inserted " + admin.getAdminId() + "into  admin successfully");
		return true;

	}

	public boolean deleteAdmin(int adminId) {
		String query = "DELETE FROM ACL.ADMIN WHERE ADMINID=?";
		int rowsAffected = jdbcTemplate.update(query, adminId);
		if (rowsAffected == 0) {
			logger.error("couldn't delete" + adminId + " from delete table");
		}
		logger.info("deleted " + adminId + "from admin table");
		return true;
	}

	public boolean changePassword(int adminId, String password) {

		String query = "UPDATE ACL.ADMIN SET PASSWORD=? WHERE ADMINID=?";
		int rowsAffected = jdbcTemplate.update(query, password, adminId);
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
