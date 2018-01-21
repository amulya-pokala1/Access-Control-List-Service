/*
 * 
 */
package com.accolite.miniau.accesscontrol.utility;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.accolite.miniau.accesscontrol.enums.UserType;

/**
 * The Class UriUtility.
 */
@Component
public class UriUtility {

	private static final Logger logger = Logger.getLogger(UriUtility.class);
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
		String deleteOld;
		String sql;
		if (userType == UserType.ADMIN) {
			deleteOld = "DELETE FROM ADMIN_PASSWORD_URI WHERE ADMIN_ID=?";
			sql = "INSERT INTO ADMIN_PASSWORD_URI(ADMIN_ID,URI) VALUES(?,?)";
		} else {
			deleteOld = "DELETE FROM USER_PASSWORD_URI WHERE USER_ID=?";
			sql = "INSERT INTO USER_PASSWORD_URI(USER_ID,URI) VALUES(?,?)";
		}
		try {
			jdbcTemplate.update(sql, id, uri);
			jdbcTemplate.update(sql, id, uri);
		} catch (Exception e) {
			logger.error("Exception", e);
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
