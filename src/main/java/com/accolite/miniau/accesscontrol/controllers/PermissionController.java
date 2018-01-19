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
import com.accolite.miniau.accesscontrol.customexception.CustomUnAuthorizedException;
import com.accolite.miniau.accesscontrol.dao.PermissionDAO;
import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.utility.StringLiteral;

/**
 * The Class PermissionController.
 */
@RestController
public class PermissionController {

	/** The permission DAO. */
	@Autowired
	PermissionDAO permissionDAO;

	/**
	 * Creates the permission.
	 *
	 * @param permission
	 *            the permission
	 * @param bindingResult
	 *            the binding result
	 */
	@PostMapping(value = "/api/permission")
	public void createPermission(@RequestBody @Valid Permission permission, BindingResult bindingResult,
			HttpSession session) {
		if (session.getAttribute(StringLiteral.ADMIN_ID) == null)
			throw new CustomUnAuthorizedException(StringLiteral.PLEASE_LOGIN);
		if (bindingResult.hasErrors()) {
			throw new CustomBadRequestException("Invalid Details!");
		}
		boolean isDone = permissionDAO.createPermission(permission);
		if (!isDone) {
			throw new CustomBadRequestException("Permission already exsist!");
		}
	}

	/**
	 * Delete permission.
	 *
	 * @param permissionId
	 *            the permission id
	 */
	@DeleteMapping(value = "/api/permission/{permissionId}")
	public void deletePermission(@PathVariable int permissionId, HttpSession session) {
		if (session.getAttribute("adminId") == null)
			throw new CustomUnAuthorizedException("Please login to perform this task!");
		boolean isDone = permissionDAO.deletePermission(permissionId);
		if (!isDone) {
			throw new CustomBadRequestException("Cannot Delete! Permission does not exsist.");
		}
	}

	/**
	 * Gets the all permissions.
	 *
	 * @return the all permissions
	 */
	@GetMapping(value = "/api/permissions")
	public List<Permission> getAllPermissions(HttpSession session) {
		if (session.getAttribute("adminId") == null)
			throw new CustomUnAuthorizedException("Please login to perform this task!");
		return permissionDAO.getAllPermissionList();
	}
}
