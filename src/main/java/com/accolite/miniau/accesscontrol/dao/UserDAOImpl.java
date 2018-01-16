
package com.accolite.miniau.accesscontrol.dao;

import java.util.List;
import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
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

		int rowsAffected = jdbcTemplate.update(Query.ADDNEWUSER, user.getUserId(), user.getUserName(),
				user.getPassword());
		if (rowsAffected == 0) {
			logger.error("couldn't insert" + user.getUserId() + " into the user table");
		}
		logger.info("inserted " + user.getUserId() + "into user table successfully");
		return true;
	}

	public User getUser(int userId) {

		return jdbcTemplate.queryForObject(Query.GETUSER, new Object[] { userId }, new UserMapper());

	}

	public boolean deleteUser(int userId) {
		// TODO review
		String query = "DELETE FROM USER WHERE USERID=?";
		String query1 = "DELETE FROM USER_GROUP WHERE USERID=?";
		int rowsAffected = jdbcTemplate.update(query, userId);
		jdbcTemplate.update(query1, userId);
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

	public boolean addPermissionToUser(int userId, Permission permission) {
		int rowsAffected = jdbcTemplate.update(Query.ADDPERMISSIONTOUSER, userId, permission.getPermissionId());
		if (rowsAffected == 0) {
			logger.info("failed to add permission " + permission.getPermissionId() + " to user");
			return false;
		}
		logger.info("successfully added permission" + permission.getPermissionId() + " to user");
		return true;
	}

	public boolean removePermissionFromUser(int userId, Permission permission) {
		int rowsAffected = jdbcTemplate.update(Query.REMOVEPERMISSIONFROMUSER, userId, permission.getPermissionId());
		if (rowsAffected == 0) {
			logger.info("failed to delete permission" + permission.getPermissionId() + " from user" + userId);
			return false;
		}
		logger.info("successfully deleted permission " + permission.getPermissionId() + "from user" + userId);
		return true;
	}

}
