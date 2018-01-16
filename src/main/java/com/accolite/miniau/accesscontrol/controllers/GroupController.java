package com.accolite.miniau.accesscontrol.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.miniau.accesscontrol.dao.GroupDAOImpl;
import com.accolite.miniau.accesscontrol.model.Group;
import com.accolite.miniau.accesscontrol.model.User;

@RestController
public class GroupController {

	@Autowired
	GroupDAOImpl groupDAOImpl;

	@PostMapping(value = "/api/group")
	public void createNewGroup(Group group) {
		groupDAOImpl.addNewGroup(group);
	}

	public void updatePermission() {
		// TOOD
	}

	@GetMapping(value = "/api/groupNames")
	public List<String> getAllGroupNames() {
		return groupDAOImpl.getAllGroupNames();
	}

	@GetMapping(value = "/api/groups")
	public List<Group> getAllGroups() {
		return groupDAOImpl.getAllGroups();
	}

	@PutMapping(value = "/api/group/{groupId}")
	public void addUserToGroup(@PathParam(value = "groupId") int groupId, @RequestBody User user) {
		groupDAOImpl.addUserToGroup(groupId, user);
	}

	@DeleteMapping(value = "/api/group/{groupId}/{userId}")
	public void deleteUserFromGroup(@PathParam(value = "groupId") int groupId,
			@PathParam(value = "userId") int userId) {
		groupDAOImpl.removeUserFromGroup(groupId, userId);
	}

	@DeleteMapping(value = "/api/group/{groupId}")
	public void deleteGroup(@PathParam(value = "groupId") int groupId) {
		groupDAOImpl.deleteGroup(groupId);
	}
}
