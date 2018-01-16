package com.accolite.miniau.accesscontrol.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.miniau.accesscontrol.dao.UserDAOImpl;
import com.accolite.miniau.accesscontrol.model.User;

@RestController
public class UserController {

	@Autowired
	UserDAOImpl userDAOImpl;

	@PostMapping(value = "/api/user")
	public void addUser(User user) {
		userDAOImpl.addNewUser(user);
	}

	@GetMapping(value = "/api/user/{id}")
	public User getUserDetails(@PathParam(value = "id") int userId) {
		return userDAOImpl.getUser(userId);
	}

	@DeleteMapping(value = "/api/user/{id}")
	public void deleteUser(@PathParam(value = "id") int userId) {
		userDAOImpl.deleteUser(userId);
	}

	@GetMapping(value = "/api/userList")
	public List<User> getAllUsers() {
		return userDAOImpl.getAllUsers();
	}

	@PutMapping(value = "/api/user/{id}")
	public void updatePermission(int permissionType) {
		// TODO
	}
}
