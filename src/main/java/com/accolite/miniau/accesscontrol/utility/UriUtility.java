/*
 * 
 */
package com.accolite.miniau.accesscontrol.utility;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.accolite.miniau.accesscontrol.enums.UserType;

/**
 * The Class UriUtility.
 */
@Component
public class UriUtility {

	/** The jdbc template. */
	JdbcTemplate jdbcTemplate;

	/**
	 * Sets the data source.
	 *
	 * @param dataSource
	 *            the new data source
	 */
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Creates the URI.
	 *
	 * @param id
	 *            the id
	 * @param uri
	 *            the uri
	 * @param userType
	 *            the user type
	 * @return true, if successful
	 */
	@Async
	public void createURI(Integer id, String uri, UserType userType) {
		String sql;
		if (userType == UserType.ADMIN) {
			sql = "INSERT INTO ADMIN_PASSWORD_URI(ADMIN_ID,URI) VALUES(?,?)";
		} else {
			sql = "INSERT INTO USER_PASSWORD_URI(USER_ID,URI) VALUES(?,?)";
		}
		try {
			jdbcTemplate.update(sql, id, uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Async
	public void deleteURI(String uri, UserType userType) {
		String sql;
		if (userType == UserType.ADMIN) {
			sql = "DELETE FROM ADMIN_PASSWORD_URI WHERE URI=?";
		} else {
			sql = "DELETE FROM USER_PASSWORD_URI WHERE URI=?";
		}
		jdbcTemplate.update(sql, uri);
	}
}
