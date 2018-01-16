
package com.accolite.miniau.accesscontrol.dao;

import java.util.List;
import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.miniau.accesscontrol.PermissionType;
import com.accolite.miniau.accesscontrol.model.User;

public class UserDAOImpl implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	static final Logger logger = Logger.getLogger(com.accolite.miniau.accesscontrol.dao.UserDAOImpl.class);

	public UserDAOImpl() {
		BasicConfigurator.configure();
	}

	public boolean addNewUser(User user) {

		String query = "INSERT INTO USER(USERID, USERNAME, PERMISSIONTYPE) VALUES (?,?,?)";
		int rowsAffected = jdbcTemplate.update(query, user.getUserId(), user.getUserName(),
				user.getPermissionType().name());
		if (rowsAffected == 0) {
			logger.error("couldn't insert into the user table");
		}
		logger.info("inserted into user table successfully");
		return true;
	}

	@SuppressWarnings("unchecked")
	public User getUser(int userId) {
		String query = "SELECT * FROM USER WHERE USERID=?";

		return jdbcTemplate.queryForObject(query, new Object[] { userId }, new BeanPropertyRowMapper<User>(User.class));

	}

	public boolean updatePermission(int userId, PermissionType permissionType) {
		String query = "UPDATE USER SET PERMISSIONTYPE=? WHERE USERID=?";
		int rowsAffected = jdbcTemplate.update(query, permissionType.name(), userId);
		if (rowsAffected == 0) {
			logger.error("failed to update the user permission");
			return false;
		}
		logger.info("updated user permission successfully");
		return true;
	}

	public boolean deleteUser(int userId) {
		String query = "DELETE FROM USER WHERE USERID=?";
		String query1 = "DELETE FROM USER_GROUP WHERE USERID=?";
		int rowsAffected = jdbcTemplate.update(query, userId);
		jdbcTemplate.update(query1, userId);
		if (rowsAffected == 0) {
			logger.error("failed to delete user");
			return false;
		}
		logger.info("deleted user in both the user and user_group tables");
		return true;
	}

	public List<User> getAllUsers() {

		String query = "SELECT * FROM USER";
		logger.info("performing get all users operation");
		return jdbcTemplate.query(query, new BeanPropertyRowMapper<User>(User.class));

	}

	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("setup of the datasource is successful");

	}

	public List<String> getAllUserNames() {
		String query = "SELECT USERNAME FROM USER";
		logger.info("performing get all usernames operation");
		return (List<String>) jdbcTemplate.queryForList(query, String.class);

	}

}
