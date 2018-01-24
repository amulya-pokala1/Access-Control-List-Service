package com.accolite.miniau.accesscontrol.daoimpl;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.miniau.accesscontrol.dao.SuperAdminDAO;
import com.accolite.miniau.accesscontrol.enums.UserType;
import com.accolite.miniau.accesscontrol.model.SuperAdmin;
import com.accolite.miniau.accesscontrol.utility.HashUtility;
import com.accolite.miniau.accesscontrol.utility.MailUtility;
import com.accolite.miniau.accesscontrol.utility.Query;
import com.accolite.miniau.accesscontrol.utility.UriUtility;

public class SuperAdminDAOImpl implements SuperAdminDAO {

	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(AdminDAOImpl.class);

	@Autowired
	private MailUtility mailUtil;

	@Autowired
	private UriUtility uriUtil;

	@Override
	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Integer authenticate(String email, String pswd) {

		Integer superAdminId;
		try {
			superAdminId = jdbcTemplate.queryForObject(Query.AUTHENTICATESUPERADMIN, new Object[] { email, pswd },
					Integer.class);
		} catch (Exception e) {
			superAdminId = 0;
		}
		return superAdminId;

	}

	@Override
	public boolean updateSuperAdmin(SuperAdmin superAdmin) {

		try {
			jdbcTemplate.update(Query.UPDATESUPERADMIN, superAdmin.getSuperAdminName(), superAdmin.getMailId(),
					superAdmin.getSuperAdminId());
		} catch (DataAccessException e) {
			logger.error("unable to update super admin" + superAdmin.getSuperAdminId());
			return false;
		}
		logger.info("successfully update super admin" + superAdmin.getSuperAdminId());
		return true;
	}

	@Override
	public void sendPasswordLink(String email, String ip, int port) {
		Integer superadminId = 1;
		String uri = HashUtility.createUniqueUriPath(superadminId, email);
		uriUtil.createURI(superadminId, uri, UserType.ADMIN);
		mailUtil = new MailUtility();
		String link = "http://" + ip + ":" + "8080/access-control-list-service/superAdmin/updatePassword/" + uri;
		mailUtil.sendEmailAsync(email, "Update Password",
				"Hi,\nPlease update your password using the below link\n" + link);

	}

}
