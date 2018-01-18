/*
 * 
 */
package com.accolite.miniau.accesscontrol.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.customexception.CustomNotFoundException;
import com.accolite.miniau.accesscontrol.customexception.CustomUnAuthorizedException;
import com.accolite.miniau.accesscontrol.dao.UserDAO;
import com.accolite.miniau.accesscontrol.model.User;

// TODO: Auto-generated Javadoc
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
	 */
	@PostMapping(value = "/api/user")
	public void addUser(@RequestBody @Valid User user, BindingResult bindingResult, HttpSession session) {
		if (session.getAttribute("adminId") == null)
			throw new CustomUnAuthorizedException("Please login to perform this task!");
		if (bindingResult.hasErrors()) {
			throw new CustomBadRequestException("Invalid Details!");
		}
		boolean isDone = userDAO.addNewUser(user);
		if (!isDone) {
			throw new CustomBadRequestException("User Already exsist!");
		}
		userDAO.sendPasswordLink(user.getMailId());
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
		if (session.getAttribute("adminId") == null)
			throw new CustomUnAuthorizedException("Please login to perform this task!");
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

	/**
	 * Update password.
	 *
	 * @param uri
	 *            the uri
	 * @param user
	 *            the user
	 */
	@PostMapping(value = "/api/user/updatePassword/{uri}/")
	public void updatePassword(@PathVariable String uri, @RequestBody User user) {

		int adminId = userDAO.getUserIdFromURI(uri);
		boolean isDone = userDAO.updatePassword(adminId, user.getPassword());
		if (!isDone) {

		}
		// delete the uri
	}
}
