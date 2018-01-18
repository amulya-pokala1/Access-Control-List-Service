package com.accolite.miniau.accesscontrol.daoimpl;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;

import com.accolite.miniau.accesscontrol.dao.AdminDAO;
import com.accolite.miniau.accesscontrol.enums.UserType;
import com.accolite.miniau.accesscontrol.model.Admin;
import com.accolite.miniau.accesscontrol.utility.HashUtility;
import com.accolite.miniau.accesscontrol.utility.MailUtility;
import com.accolite.miniau.accesscontrol.utility.Query;
import com.accolite.miniau.accesscontrol.utility.UriUtility;

public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private MailUtility mailUtil;
	@Autowired
	private UriUtility uriUtil;

	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(com.accolite.miniau.accesscontrol.daoimpl.AdminDAOImpl.class);

	@Override
	public boolean createAdmin(Admin admin) {

		int rowsAffected = jdbcTemplate.update(Query.CREATEADMIN, admin.getAdminName(), admin.getDescription(),
				admin.getMailId());
		if (rowsAffected == 0) {
			logger.error("couldn't insert" + admin.getAdminId() + " into the admin table");
			return false;
		}
		logger.info("inserted " + admin.getAdminName() + "into  admin successfully");
		return true;

	}

	@Override
	public boolean deleteAdmin(int adminId) {
		int rowsAffected = jdbcTemplate.update(Query.DELETEADMIN, adminId);
		if (rowsAffected == 0) {
			logger.error("couldn't delete" + adminId + " from delete table");
			return false;
		}
		logger.info("deleted " + adminId + "from admin table");
		return true;
	}

	@Override
	public boolean updatePassword(int adminId, String password) {

		int rowsAffected = jdbcTemplate.update(Query.CHANGEPASSWORD, password, adminId);
		if (rowsAffected == 0) {
			logger.error("couldn't update password");
		}
		logger.info("changed password");
		return true;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);

	}

	@Override
	public Integer getAdminIdFromURI(String uri) {
		String sql = "SELECT USER_ID FROM ADMIN_PASSWORD_URI WHERE URI=?";
		Integer userId;
		try {
			userId = jdbcTemplate.queryForObject(sql, new Object[] { uri }, Integer.class);
		} catch (Exception e) {
			userId = 0;
		}
		return userId;
	}

	@Override
	@Async
	public void sendPasswordLink(String email) {
		Integer adminId = getAdminIdUsingEmail(email);
		String uri = HashUtility.createUniqueUriPath(adminId, email);
		boolean isStored = uriUtil.createURI(adminId, uri, UserType.ADMIN);
		if (isStored) {
			String link = null; // TODO complete this link
			mailUtil.sendEmailAsync(email, "Update Password",
					"Hi,\nPlease update your password using the below link\n" + link);
		}
	}

	@Override
	public Integer getAdminIdUsingEmail(String email) {
		String sql = "SELECT ADMIN_ID FROM ADMIN WHERE MAIL_ID = ?";
		Integer adminId;
		try {
			adminId = jdbcTemplate.queryForObject(sql, new Object[] { email }, Integer.class);
		} catch (Exception e) {
			adminId = 0;
		}
		return adminId;
	}
}
