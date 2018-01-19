/*
 * 
 */
package com.accolite.miniau.accesscontrol.daoimpl;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;

import com.accolite.miniau.accesscontrol.dao.AdminDAO;
import com.accolite.miniau.accesscontrol.enums.UserType;
import com.accolite.miniau.accesscontrol.model.Admin;
import com.accolite.miniau.accesscontrol.utility.HashUtility;
import com.accolite.miniau.accesscontrol.utility.MailUtility;
import com.accolite.miniau.accesscontrol.utility.Query;
import com.accolite.miniau.accesscontrol.utility.UriUtility;

// TODO: Auto-generated Javadoc
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

		try {
			jdbcTemplate.update(Query.CREATEADMIN, admin.getAdminName(), admin.getDescription(), admin.getMailId());
			int adminId = jdbcTemplate.queryForObject(Query.GETADMINID, new Object[] { admin.getMailId() },
					Integer.class);
			admin.setAdminId(adminId);
		} catch (DataAccessException e) {
			logger.info("couldn't insert admin" + admin.getAdminName());
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
	public boolean updatePassword(int adminId, String password) {

		int rowsAffected = jdbcTemplate.update(Query.CHANGEPASSKEY, password, adminId);
		if (rowsAffected == 0) {
			logger.error("couldn't update password");
		}
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
		String sql = "SELECT USER_ID FROM ADMIN_PASSWORD_URI WHERE URI=?";
		Integer userId;
		try {
			userId = jdbcTemplate.queryForObject(sql, new Object[] { uri }, Integer.class);
		} catch (Exception e) {
			userId = 0;
		}
		return userId;
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
}
