package com.accolite.miniau.accesscontrol.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.accolite.miniau.accesscontrol.model.User;

public class UserMapper implements RowMapper<User> {

	private static Logger logger = Logger.getLogger(UserMapper.class);

	public User mapRow(ResultSet rs, int rowNum) {
		User user = new User();
		try {
			user.setUserId(rs.getInt("USER_ID"));
			user.setUserName(rs.getString("USER_NAME"));
		} catch (SQLException e) {
			logger.error("Error parsing User", e);
		}
		return user;
	}

}
