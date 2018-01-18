package com.accolite.miniau.accesscontrol.controllers;

import java.util.List;

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
import com.accolite.miniau.accesscontrol.dao.PermissionDAO;
import com.accolite.miniau.accesscontrol.model.Permission;

@RestController
public class PermissionController {

	@Autowired
	PermissionDAO permissionDAO;

	@PostMapping(value = "/api/permission")
	public void createPermission(@RequestBody @Valid Permission permission, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new CustomBadRequestException("Invalid Details!");
		}
		boolean isDone = permissionDAO.createPermission(permission);
		if (!isDone) {
			throw new CustomBadRequestException("Permission already exsist!");
		}
	}

	@DeleteMapping(value = "/api/permission/{permissionId}")
	public void deletePermission(@PathVariable int permissionId) {
		boolean isDone = permissionDAO.deletePermission(permissionId);
		if (!isDone) {
			throw new CustomBadRequestException("Cannot Delete! Permission does not exsist.");
		}
	}

	@GetMapping(value = "/api/permissions")
	public List<Permission> getAllPermissions() {
		return permissionDAO.getAllPermissionList();
	}
}
