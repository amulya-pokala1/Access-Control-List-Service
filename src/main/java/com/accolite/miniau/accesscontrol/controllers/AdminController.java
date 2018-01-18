package com.accolite.miniau.accesscontrol.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.customexception.CustomNotFoundException;
import com.accolite.miniau.accesscontrol.dao.AdminDAO;
import com.accolite.miniau.accesscontrol.model.Admin;
import com.accolite.miniau.accesscontrol.utility.MailUtility;

@RestController
public class AdminController {

	@Autowired
	AdminDAO adminDAO;

	@Autowired
	MailUtility mailUtility;

	@PostMapping(value = "/api/admin")
	public void addNewAdmin(@RequestBody Admin admin) {

		boolean isDone = adminDAO.createAdmin(admin);
		if (!isDone) {
			throw new CustomBadRequestException("Admin already exist with same Admin Name");
		}
		adminDAO.sendPasswordLink(admin.getMailId());
	}

	@DeleteMapping(value = "/api/admin/{adminId}")
	public void deleteAdmin(@PathVariable int adminId) {
		boolean isDone = adminDAO.deleteAdmin(adminId);
		if (!isDone) {
			throw new CustomNotFoundException("Admin " + adminId + " not found!");
		}
	}

	@PutMapping(value = "/api/admin/changePassword")
	public void changePasswordForAdmin(@RequestBody Map<String, String> reqBody) {
		// TODO complete this method after abishek complets the UI part
	}
}
