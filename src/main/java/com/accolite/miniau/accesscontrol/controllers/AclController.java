/*
 * 
 */
package com.accolite.miniau.accesscontrol.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.customexception.CustomUnAuthorizedException;
import com.accolite.miniau.accesscontrol.dao.UserDAO;
import com.accolite.miniau.accesscontrol.model.User;
import com.accolite.miniau.accesscontrol.utility.MailUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class AclController.
 */
@RestController
public class AclController {

	/** The user DAO. */
	@Autowired
	UserDAO userDAO;

	/** The mail util. */
	@Autowired
	MailUtility mailUtil;

	/** The logger. */
	private static Logger logger = Logger.getLogger(AclController.class);

	/**
	 * Checks if is authenticated.
	 *
	 * @param user
	 *            the user
	 * @return the user
	 */
	@PostMapping(value = "/ACL")
	public User isAuthenticated(@RequestBody User user) {

		logger.info("Incoming Request for " + user.getMailId());
		int id = userDAO.validateUser(user);
		if (id == 0) {
			throw new CustomBadRequestException("User not availabe!");
		}
		user.setPermissions(userDAO.getPermissionOfUser(id));
		return user;
	}

	/**
	 * Test.
	 */
	@GetMapping("test")
	public void test(HttpSession session) {
		session.setAttribute("name", "tarun");
		mailUtil.sendEmailAsync("tarun.k@accoliteindia.com", "Hello Tarun", "This is a sample mail");
	}
}
