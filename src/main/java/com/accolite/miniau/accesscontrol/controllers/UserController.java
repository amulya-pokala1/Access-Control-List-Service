/*
 * 
 */
package com.accolite.miniau.accesscontrol.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.customexception.CustomNotFoundException;
import com.accolite.miniau.accesscontrol.customexception.CustomUnAuthorizedException;
import com.accolite.miniau.accesscontrol.dao.UserDAO;
import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.model.User;
import com.accolite.miniau.accesscontrol.utility.StringLiteral;

/**
 * The Class UserController.
 */
@RestController
public class UserController {

	/** The user DAO. */
	@Autowired
	UserDAO userDAO;

	/**
	 * Adds the user.
	 *
	 * @param user
	 *            the user
	 * @param bindingResult
	 *            the binding result
	 * @throws UnknownHostException
	 */
	@PostMapping(value = "/api/user")
	public void addUser(@RequestBody @Valid User user, BindingResult bindingResult, HttpSession session,
			HttpServletRequest request) throws UnknownHostException {
		if (session.getAttribute(StringLiteral.ADMIN_ID) == null)
			throw new CustomUnAuthorizedException(StringLiteral.PLEASE_LOGIN);
		if (bindingResult.hasErrors()) {
			throw new CustomBadRequestException("Invalid Details!");
		}
		boolean isDone = userDAO.addNewUser(user);
		if (!isDone) {
			throw new CustomBadRequestException("User Already exsist!");
		}
		userDAO.sendPasswordLink(user.getMailId(), InetAddress.getLocalHost().getHostAddress(), request.getLocalPort());
	}

	/**
	 * Gets the user details.
	 *
	 * @param userId
	 *            the user id
	 * @return the user details
	 */
	@GetMapping(value = "/api/user/{userId}")
	public User getUserDetails(@PathVariable int userId, HttpSession session) {
		if (session.getAttribute(StringLiteral.ADMIN_ID) == null)
			throw new CustomUnAuthorizedException(StringLiteral.PLEASE_LOGIN);
		return userDAO.getUser(userId);
	}

	/**
	 * Delete user.
	 *
	 * @param userId
	 *            the user id
	 */
	@DeleteMapping(value = "/api/user/{userId}")
	public void deleteUser(@PathVariable int userId, HttpSession session) {
		if (session.getAttribute("adminId") == null)
			throw new CustomUnAuthorizedException("Please login to perform this task!");
		boolean isDone = userDAO.deleteUser(userId);
		if (!isDone) {
			throw new CustomNotFoundException("Cannot Delete User! User Does not exsist!");
		}
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	@GetMapping(value = "/api/userList")
	public List<User> getAllUsers(HttpSession session) {
		if (session.getAttribute("adminId") == null)
			throw new CustomUnAuthorizedException("Please login to perform this task!");
		return userDAO.getAllUsers();
	}

	@PutMapping(value = "/api/user/{userId}/permission/{permissionId}")
	public void addPermissionToUser(@PathVariable int userId, @PathVariable int permissionId) {
		System.out.println("in controller "+userId);
		boolean isDone = userDAO.addPermissionToUser(userId, permissionId);
		if(!isDone) {
			throw new CustomBadRequestException("Error occured in adding permission to user");
		}
	}

	@DeleteMapping(value = "/api/user/{userId}/permission/{permissionId}")
	public void removePermissionFromUser(@PathVariable int userId, @PathVariable int permissionId) {
		userDAO.removePermissionFromUser(userId, permissionId);
	}
	
	@GetMapping(value="/api/user/{userId}/permissions")
	public List<Permission> getUserPermissions(@PathVariable int userId) {
		return userDAO.getPermissionOfUser(userId);
	}
	
	@GetMapping(value="/api/user/{userId}/exceptPermissions")
	public List<Permission> getPermissionsExceptUser(@PathVariable int userId){
		return userDAO.getAllPermissionsExceptUser(userId);
	}
}
