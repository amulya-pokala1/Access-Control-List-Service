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

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.customexception.CustomNotFoundException;
import com.accolite.miniau.accesscontrol.dao.GroupDAO;
import com.accolite.miniau.accesscontrol.dao.GroupDAOImpl;
import com.accolite.miniau.accesscontrol.model.Group;
import com.accolite.miniau.accesscontrol.model.User;

@RestController
public class GroupController {

	@Autowired
	GroupDAO groupDAO;

	@PostMapping(value = "/api/group")
	public void createNewGroup(Group group) {
		boolean isDone = groupDAO.addNewGroup(group);
		if (!isDone) {
			throw new CustomBadRequestException("Group already exist with same Group Name");
		}
	}

	@GetMapping(value = "/api/groupNames")
	public List<String> getAllGroupNames() {
		return groupDAO.getAllGroupNames();
	}

	@GetMapping(value = "/api/groups")
	public List<Group> getAllGroups() {
		return groupDAO.getAllGroups();
	}

	@PutMapping(value = "/api/group/{groupId}")
	public void addUserToGroup(@PathParam(value = "groupId") int groupId, @RequestBody User user) {
		boolean isDone = groupDAO.addUserToGroup(groupId, user);
		if(!isDone) {
			throw new CustomBadRequestException("User Already exsist in Group");
		}
	}

	@DeleteMapping(value = "/api/group/{groupId}/{userId}")
	public void deleteUserFromGroup(@PathParam(value = "groupId") int groupId,
			@PathParam(value = "userId") int userId) {
		boolean isDone = groupDAO.removeUserFromGroup(groupId, userId);
		if(!isDone) {
			throw new CustomNotFoundException("User already not in group!");
		}
	}

	@DeleteMapping(value = "/api/group/{groupId}")
	public void deleteGroup(@PathParam(value = "groupId") int groupId) {
		boolean isDone = groupDAO.deleteGroup(groupId);
		if (!isDone) {
			throw new CustomNotFoundException("Cannot Delete! Group doesn't Exsist.");
		}

	}
}
