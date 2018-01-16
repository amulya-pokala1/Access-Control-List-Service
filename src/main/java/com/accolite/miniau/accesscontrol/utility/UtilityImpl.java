package com.accolite.miniau.accesscontrol.utility;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import com.accolite.miniau.accesscontrol.PermissionType;

public class UtilityImpl implements Utility {

	JdbcTemplate jdbcTemplate;
	static final Logger logger = Logger.getLogger(com.accolite.miniau.accesscontrol.utility.UtilityImpl.class);

	public UtilityImpl() {
		BasicConfigurator.configure();
	}

	public boolean isUserAuthorized(int userId, PermissionType permissiontype) {
		String query = "SELECT PERMISSIONTYPE FROM USER WHERE USERID=?";
		logger.info("executing user authorization");
		String permission = jdbcTemplate.queryForObject(query, new Object[] { userId }, String.class);
		logger.info("executed user authorization");
		return permission.equals(permissiontype.name()) ? true : false;

	}

}
