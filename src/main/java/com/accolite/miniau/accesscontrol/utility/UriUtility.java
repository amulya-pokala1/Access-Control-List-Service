package com.accolite.miniau.accesscontrol.utility;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.accolite.miniau.accesscontrol.enums.UserType;

@Component
public class UriUtility {

	JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Async
	public boolean createURI(Integer id, String uri, UserType userType) {
		String sql;
		if (userType == UserType.ADMIN) {
			sql = "INSERT INTO ADMIN_PASSWORD_URI(ADMIN_ID,URI) VALUES(?,?)";
		} else {
			sql = "INSERT INTO USER_PASSWORD_URI(USER_ID,URI) VALUES(?,?)";
		}
		int count = jdbcTemplate.update(sql, id, uri);
		if (count == 0) {
			// TODO error
			return false;
		}
		return true;
	}
}
