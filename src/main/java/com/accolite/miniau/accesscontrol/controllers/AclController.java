/*
 * 
 */
package com.accolite.miniau.accesscontrol.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.dao.GroupDAO;
import com.accolite.miniau.accesscontrol.dao.UserDAO;
import com.accolite.miniau.accesscontrol.model.Group;
import com.accolite.miniau.accesscontrol.model.User;
import com.accolite.miniau.accesscontrol.utility.HashUtility;
import com.accolite.miniau.accesscontrol.utility.MailUtility;

/**
 * The Class AclController.
 */
@RestController
public class AclController {

	/** The user DAO. */
	@Autowired
	UserDAO userDAO;

	@Autowired
	GroupDAO groupDAO;

	/** The mail utility. */
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
	@PostMapping(value = "/ACL/user") // requires user name and password to get permissions
	public User userPermission(@RequestBody User user) {

		logger.info("Incoming Request for " + user.getMailId());
		user.setPassword(HashUtility.hashPassword(user.getPassword()));
		int id = userDAO.validateUser(user);
		if (id == 0) {
			throw new CustomBadRequestException("User not availabe!");
		}
		user.setPermissions(userDAO.getPermissionOfUser(id));
		return user;
	}

	@GetMapping(value = "/ACL/group/{groupName}")
	public Group groupPermission(@PathVariable String groupName) {
		logger.info("Incoming Request for " + groupName);
		Group group = groupDAO.getGroupDetailsByGroupName(groupName);
		if (group == null) {
			throw new CustomBadRequestException("Group Does not exsist!");
		}
		group.setPermissions(groupDAO.getGroupPermissions(group.getGroupId()));
		group.setUsers(groupDAO.getUsersInGroup(group.getGroupId()));
		return group;

	}
}
