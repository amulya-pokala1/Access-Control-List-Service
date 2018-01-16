package com.accolite.miniau.accesscontrol.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.miniau.accesscontrol.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		// TODO GET THE DATA FROM RS AND WRITE TO SETTER METHODS
		return user;
	}

}
