/*
 * 
 */

package com.accolite.miniau.accesscontrol.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;

import com.accolite.miniau.accesscontrol.dao.UserDAO;
import com.accolite.miniau.accesscontrol.enums.UserType;
import com.accolite.miniau.accesscontrol.mapper.PermissionMapper;
import com.accolite.miniau.accesscontrol.mapper.UserMapper;
import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.model.User;
import com.accolite.miniau.accesscontrol.utility.HashUtility;
import com.accolite.miniau.accesscontrol.utility.MailUtility;
import com.accolite.miniau.accesscontrol.utility.Query;
import com.accolite.miniau.accesscontrol.utility.UriUtility;

/**
 * The Class UserDAOImpl.
 */
public class UserDAOImpl implements UserDAO {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	/** The uri util. */
	@Autowired
	UriUtility uriUtil;

	/** The mail util. */
	@Autowired
	MailUtility mailUtil;

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	String uri;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.accolite.miniau.accesscontrol.dao.UserDAO#addNewUser(com.accolite.miniau.
	 * accesscontrol.model.User)
	 */
	@Override
	public boolean addNewUser(User user) {

		int rowsAffected;
		try {
			rowsAffected = jdbcTemplate.update(Query.ADDNEWUSER, user.getUserName(), user.getMailId());
			int userId = jdbcTemplate.queryForObject(Query.GETUSERID, new Object[] { user.getMailId() }, Integer.class);
			user.setUserId(userId);

		} catch (Exception e) {
			logger.error("Error creating User", e);
			rowsAffected = 0;
		}
		if (rowsAffected == 0) {
			logger.error("couldn't insert" + user.getUserName() + " into the user table");
			return false;
		}
		logger.info("inserted " + user.getUserId() + "into user table successfully");
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.UserDAO#getUser(int)
	 */
	@Override
	public User getUser(int userId) {

		try {
			return jdbcTemplate.queryForObject(Query.GETUSER, new Object[] { userId }, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.UserDAO#deleteUser(int)
	 */
	@Override
	public boolean deleteUser(int userId) {
		int rowsAffected = jdbcTemplate.update(Query.DELETEUSER, userId);
		if (rowsAffected == 0) {
			logger.error("failed to delete user " + userId);
			return false;
		}
		logger.info("deleted user " + userId + "in both the user and user_group tables");
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.UserDAO#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers() {

		logger.info("performing get all users operation");
		return jdbcTemplate.query(Query.GETALLUSERS, new BeanPropertyRowMapper<User>(User.class));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.UserDAO#setDataSource(javax.sql.
	 * DataSource)
	 */
	@Override
	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.UserDAO#getAllUserNames()
	 */
	@Override
	public List<String> getAllUserNames() {
		logger.info("performing get all usernames operation");
		return jdbcTemplate.queryForList(Query.GETALLUSERNAMES, String.class);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.UserDAO#addPermissionToUser(int,
	 * int)
	 */
	@Override
	public boolean addPermissionToUser(int userId, int permissionId) {

		try {
			jdbcTemplate.update(Query.ADDPERMISSIONTOUSER, userId, permissionId);
		} catch (DataAccessException e) {
			logger.error("" + e);
			return false;
		}

		logger.info("successfully added permission" + permissionId + " to user");
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.accolite.miniau.accesscontrol.dao.UserDAO#removePermissionFromUser(int,
	 * int)
	 */
	@Override
	public boolean removePermissionFromUser(int userId, int permissionId) {
		int rowsAffected = jdbcTemplate.update(Query.REMOVEPERMISSIONFROMUSER, userId, permissionId);
		if (rowsAffected == 0) {
			logger.info("failed to delete permission" + permissionId + " from user" + userId);
			return false;
		}
		logger.info("successfully deleted permission " + permissionId + "from user" + userId);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.UserDAO#getPermissionOfUser(int)
	 */
	@Override
	public List<Permission> getPermissionOfUser(int userId) {
		return jdbcTemplate.query(Query.GETUSERPERMISSIONS, new Object[] { userId }, new PermissionMapper());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.UserDAO#updatePassword(int,
	 * java.lang.String)
	 */
	@Override
	public boolean updatePassword(String uri, String password) {
		int userId = getUserIdFromURI(uri);
		int rowsAffected = jdbcTemplate.update(Query.UPDATEPASSKEY, password, userId);
		if (rowsAffected == 0) {
			logger.info("failed to update passsword for user" + userId);
			return false;
		}
		uriUtil.deleteURI(uri, UserType.USER);
		logger.info("successfully updated password for user" + userId);
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accolite.miniau.accesscontrol.dao.UserDAO#validateUser(com.accolite.
	 * miniau.accesscontrol.model.User)
	 */
	@Override
	public int validateUser(User user) {
		String sql = "SELECT USER_ID FROM USER WHERE MAIL_ID = ? AND PASSKEY=?";
		int userId;
		try {
			userId = jdbcTemplate.queryForObject(sql, new Object[] { user.getMailId(), user.getPassword() },
					Integer.class);
		} catch (Exception e) {
			logger.error("Exception", e);
			userId = 0;
		}
		return userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.accolite.miniau.accesscontrol.dao.UserDAO#getUserIdFromURI(java.lang.
	 * String)
	 */
	@Override
	public Integer getUserIdFromURI(String uri) {
		String sql = "SELECT USER_ID FROM ACL.USER_PASSWORD_URI WHERE URI=?";
		Integer userId;
		try {
			userId = jdbcTemplate.queryForObject(sql, new Object[] { uri }, Integer.class);
		} catch (Exception e) {
			logger.error("error getting user id from uri", e);
			logger.error("Exception", e);
			userId = 0;
		}
		return userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.accolite.miniau.accesscontrol.dao.UserDAO#getUserIdUsingEmail(java.lang.
	 * String)
	 */
	@Override
	public Integer getUserIdUsingEmail(String email) {
		String sql = "SELECT USER_ID FROM USER WHERE MAIL_ID = ?";
		Integer userId;
		try {
			userId = jdbcTemplate.queryForObject(sql, new Object[] { email }, Integer.class);
		} catch (Exception e) {
			userId = 0;
		}
		return userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.accolite.miniau.accesscontrol.dao.UserDAO#sendPasswordLink(java.lang.
	 * String)
	 */
	@Override
	@Async
	public void sendPasswordLink(String email, String ip, int port) {
		Integer userId = getUserIdUsingEmail(email);
		String uri1 = HashUtility.createUniqueUriPath(userId, email);
		uriUtil.createURI(userId, uri1, UserType.USER);
		String link = "http://" + ip + ":" + port+"/access-control-list-service/user/updatePassword/" + uri1;

		mailUtil.sendEmailAsync(email, "Update Password",
				"Hi,\nPlease update your password using the below link\n" + link);
		uri = uri1;
	}

	@Override
	public List<Permission> getAllPermissionsExceptUser(int userId) {
		String sql = "SELECT * FROM PERMISSION WHERE PERMISSION_ID NOT IN (SELECT PERMISSION_ID FROM USER_PERMISSION WHERE USER_ID = ?)";
		return jdbcTemplate.query(sql, new Object[] { userId }, new PermissionMapper());
	}

	@Override
	public void setDataSourceForURIUtil(DataSource dataSource) {
		uriUtil = new UriUtility();
		uriUtil.setDataSource(dataSource);
	}

	@Override
	public String getURI() {
		return uri;
	}
}
