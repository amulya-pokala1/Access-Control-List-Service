package com.accolite.miniau.accesscontrol.controllers;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.customexception.CustomNotFoundException;
import com.accolite.miniau.accesscontrol.dao.AdminDAO;
import com.accolite.miniau.accesscontrol.model.Admin;

@RestController
public class AdminController {

	@Autowired
	AdminDAO adminDAO;

	@PostMapping(value = "/api/admin")
	public void addNewAdmin(@RequestBody Admin admin) {

		boolean isDone = adminDAO.createAdmin(admin);
		if (!isDone) {
			throw new CustomBadRequestException("Admin already exist with same Admin Name");
		}
	}

	@GetMapping(value = "/api/admin/{adminId}")
	public void deleteAdmin(@PathParam(value = "adminId") int adminId) {
		boolean isDone = adminDAO.deleteAdmin(adminId);
		if(!isDone) {
			throw new CustomNotFoundException("Admin "+adminId+" not found!");
		}
			
	}

	@PutMapping(value = "/api/admin/changePassword")
	public void changePasswordForAdmin(@RequestBody Map<String, String> reqBody) {
		// TODO complete this method after abishek complets the UI part
	}
}
