package com.accolite.miniau.accesscontrol.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.miniau.accesscontrol.customexception.CustomUnAuthorizedException;
import com.accolite.miniau.accesscontrol.dao.UserDAO;
import com.accolite.miniau.accesscontrol.model.User;
import com.accolite.miniau.accesscontrol.utility.MailUtility;

@RestController
public class AclController {

	@Autowired
	UserDAO userDAO;

	@Autowired
	MailUtility mailUtil;

	private static Logger logger = Logger.getLogger(AclController.class);

	@PostMapping(value = "/ACL/")
	public User isAuthenticated(@RequestBody User user) {

		logger.info("Incoming Request for " + user.getMailId());
		int id = userDAO.validateUser(user);
		if (id == 0) {
			throw new CustomUnAuthorizedException("User not authorized!");
		}
		user.setPermissions(userDAO.getPermissionOfUser(id));
		return user;
	}

	@GetMapping("test")
	public void test() {
		mailUtil.sendEmailAsync("tarun.k@accoliteindia.com", "Hello Tarun", "This is a sample mail");
	}
}
