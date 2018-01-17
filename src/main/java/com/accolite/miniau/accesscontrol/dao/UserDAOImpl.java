
package com.accolite.miniau.accesscontrol.dao;

import java.util.List;
import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.miniau.accesscontrol.mapper.UserMapper;
import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.model.User;
import com.accolite.miniau.accesscontrol.utility.Query;

public class UserDAOImpl implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(com.accolite.miniau.accesscontrol.dao.UserDAOImpl.class);

	public UserDAOImpl() {
		BasicConfigurator.configure();
	}

	public boolean addNewUser(User user) {

		int rowsAffected = jdbcTemplate.update(Query.ADDNEWUSER, user.getUserName(),
				user.getPassword(), user.getMailId());
		if (rowsAffected == 0) {
			logger.error("couldn't insert" + user.getUserName() + " into the user table");
			return false;
		}
		logger.info("inserted " + user.getUserId() + "into user table successfully");
		return true;
	}

	public User getUser(int userId) {

		User user = null;
		try {
			user = jdbcTemplate.queryForObject(Query.GETUSER, new Object[] { userId }, new UserMapper());
		} catch (DataAccessException e) {
			logger.error("caught: " + e);
		}
		return user;
	}

	public boolean deleteUser(int userId) {
		String query = "DELETE FROM USER WHERE USERID=?";
		int rowsAffected = jdbcTemplate.update(query, userId);
		if (rowsAffected == 0) {
			logger.error("failed to delete user " + userId);
			return false;
		}
		logger.info("deleted user " + userId + "in both the user and user_group tables");
		return true;
	}

	public List<User> getAllUsers() {

		logger.info("performing get all users operation");
		return jdbcTemplate.query(Query.GETALLUSERS, new BeanPropertyRowMapper<User>(User.class));

	}

	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("setup of the datasource is successful");

	}

	public List<String> getAllUserNames() {
		logger.info("performing get all usernames operation");
		return jdbcTemplate.queryForList(Query.GETALLUSERNAMES, String.class);

	}

	public boolean addPermissionToUser(int userId, int permissionId) {
		// TODO--review on update--exception
		int rowsAffected = jdbcTemplate.update(Query.ADDPERMISSIONTOUSER, userId, permissionId);
		if (rowsAffected == 0) {
			logger.info("failed to add permission " + permissionId + " to user");
			return false;
		}
		logger.info("successfully added permission" + permissionId + " to user");
		return true;
	}

	public boolean removePermissionFromUser(int userId, int permissionId) {
		int rowsAffected = jdbcTemplate.update(Query.REMOVEPERMISSIONFROMUSER, userId, permissionId);
		if (rowsAffected == 0) {
			logger.info("failed to delete permission" + permissionId + " from user" + userId);
			return false;
		}
		logger.info("successfully deleted permission " + permissionId + "from user" + userId);
		return true;
	}

}
