package com.accolite.miniau.accesscontrol.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.dao.SuperAdminDAO;
import com.accolite.miniau.accesscontrol.model.SuperAdmin;

@RestController
public class SuperAdminController {

	@Autowired
	SuperAdminDAO superAdminDAO;

	@PostMapping(value = "/api/superAdmin/update")
	public void updateSuperAdmin(@RequestBody @Valid SuperAdmin superadmin, BindingResult bindingResult,
			HttpSession session, HttpServletRequest request) throws UnknownHostException {

		if (bindingResult.hasErrors()) {
			throw new CustomBadRequestException("Invalid details.\n");
		}

		boolean result = superAdminDAO.updateSuperAdmin(superadmin);
		if (!result) {
			throw new CustomBadRequestException("Bad Request");
		}
		superAdminDAO.sendPasswordLink(superadmin.getMailId(), InetAddress.getLocalHost().getHostAddress(),
				request.getLocalPort());

	}

}
