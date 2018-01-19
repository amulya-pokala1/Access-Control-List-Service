/*
 * 
 */
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

/**
 * The Class AdminDAOImpl.
 */
public class AdminDAOImpl implements AdminDAO {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(AdminDAOImpl.class);

	/** The mail util. */
	@Autowired
	private MailUtility mailUtil;

	/** The uri util. */
	@Autowired
	private UriUtility uriUtil;

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.AdminDAO#createAdmin(com.accolite.
	 * miniau.accesscontrol.model.Admin)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.AdminDAO#deleteAdmin(int)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.AdminDAO#updatePassword(int,
	 * java.lang.String)
	 */
	@Override
	public boolean updatePassword(String uri, String password) {

		int adminId = getAdminIdFromURI(uri);
		int rowsAffected = jdbcTemplate.update(Query.CHANGEPASSWORD, password, adminId);
		if (rowsAffected == 0) {
			logger.error("couldn't update password");
		}
		uriUtil.deleteURI(uri, UserType.ADMIN);
		logger.info("changed password");
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.AdminDAO#setDataSource(javax.sql.
	 * DataSource)
	 */
	@Override
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.accolite.miniau.accesscontrol.dao.AdminDAO#getAdminIdFromURI(java.lang.
	 * String)
	 */
	@Override
	public Integer getAdminIdFromURI(String uri) {
		String sql = "SELECT ADMIN_ID FROM ADMIN_PASSWORD_URI WHERE URI=?";
		Integer adminId;
		try {
			adminId = jdbcTemplate.queryForObject(sql, new Object[] { uri }, Integer.class);
		} catch (Exception e) {
			adminId = 0;
		}
		return adminId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.accolite.miniau.accesscontrol.dao.AdminDAO#sendPasswordLink(java.lang.
	 * String)
	 */
	@Override
	@Async
	public void sendPasswordLink(String email, String ip, int port) {
		Integer adminId = getAdminIdUsingEmail(email);
		String uri = HashUtility.createUniqueUriPath(adminId, email);
		uriUtil.createURI(adminId, uri, UserType.ADMIN);
		String link = "http://" + ip + ":" + "8080/access-control-list-service/admin/updatePassword/" + uri;
		mailUtil.sendEmailAsync(email, "Update Password",
				"Hi,\nPlease update your password using the below link\n" + link);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.accolite.miniau.accesscontrol.dao.AdminDAO#getAdminIdUsingEmail(java.lang
	 * .String)
	 */
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

	@Override
	public String getAdminName(int adminId) {
		String sql = "SELECT ADMIN_NAME FROM ADMIN WHERE ADMIN_ID = ?";
		String name;
		try {
			name = jdbcTemplate.queryForObject(sql, String.class);
		} catch (Exception e) {
			name = null;
		}
		return name;
	}

	@Override
	public Integer authenticate(Admin admin) {
		String sql = "SELECT ADMIN_ID FROM ADMIN WHERE MAIL_ID=? AND PASSKEY=?";
		Integer adminId;
		try {
			adminId = jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			adminId = null;
		}
		return adminId;
	}
}
