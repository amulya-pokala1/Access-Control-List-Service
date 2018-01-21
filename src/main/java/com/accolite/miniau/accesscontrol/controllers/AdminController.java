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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.customexception.CustomNotFoundException;
import com.accolite.miniau.accesscontrol.customexception.CustomUnAuthorizedException;
import com.accolite.miniau.accesscontrol.dao.AdminDAO;
import com.accolite.miniau.accesscontrol.model.Admin;
import com.accolite.miniau.accesscontrol.utility.MailUtility;
import com.accolite.miniau.accesscontrol.utility.StringLiteral;

/**
 * The Class AdminController.
 */
@RestController
public class AdminController {

	/** The admin DAO. */
	@Autowired
	AdminDAO adminDAO;

	/** The mail utility. */
	@Autowired
	MailUtility mailUtility;

	/**
	 * Adds the new admin.
	 *
	 * @param admin
	 *            the admin
	 * @param bindingResult
	 *            the binding result
	 * @throws UnknownHostException
	 */
	@PostMapping(value = "/api/admin")
	public void addNewAdmin(@RequestBody @Valid Admin admin, BindingResult bindingResult, HttpSession session,
			HttpServletRequest request) throws UnknownHostException {

		if (session.getAttribute("sadminId") == null)
			throw new CustomUnAuthorizedException(StringLiteral.PLEASE_LOGIN);
		if (bindingResult.hasErrors()) {
			throw new CustomBadRequestException("Invalid details.\n");
		}
		boolean isDone = adminDAO.createAdmin(admin);
		if (!isDone) {
			throw new CustomBadRequestException("Admin already exist with same Admin Name");
		}
		adminDAO.sendPasswordLink(admin.getMailId(), InetAddress.getLocalHost().getHostAddress(),
				request.getLocalPort());
	}

	/**
	 * Delete admin.
	 *
	 * @param adminId
	 *            the admin id
	 */
	@DeleteMapping(value = "/api/admin/{adminId}")
	public void deleteAdmin(@PathVariable int adminId, HttpSession session) {
		if (session.getAttribute("sadminId") == null)
			throw new CustomUnAuthorizedException("Please login to perform this task!");
		boolean isDone = adminDAO.deleteAdmin(adminId);
		if (!isDone) {
			throw new CustomNotFoundException("Admin " + adminId + " not found!");
		}
	}

	@GetMapping(value = "/api/admins")
	public List<Admin> getAllAdmins(HttpSession session) {
		if (session.getAttribute("sadminId") == null)
			throw new CustomUnAuthorizedException("Please login to perform this task!");
		return adminDAO.getAllAdmins();
	}
}
