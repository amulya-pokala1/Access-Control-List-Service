package com.accolite.miniau.accesscontrol.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.customexception.CustomUnAuthorizedException;
import com.accolite.miniau.accesscontrol.dao.AdminDAO;
import com.accolite.miniau.accesscontrol.dao.UserDAO;
import com.accolite.miniau.accesscontrol.model.Admin;
import com.accolite.miniau.accesscontrol.utility.HashUtility;
import com.accolite.miniau.accesscontrol.utility.StringLiteral;

@Controller
public class PageController {

	@Autowired
	AdminDAO adminDAO;

	@Autowired
	UserDAO userDAO;

	@GetMapping("/")
	public String getIndexPage(Model model, HttpSession session) {

		if (session.getAttribute(StringLiteral.ADMIN_ID) == null) {
			return "index";
		}
		int adminId = (int) session.getAttribute("adminId");
		model.addAttribute("name", adminDAO.getAdminName(adminId));
		return "adminPage";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

	@PostMapping("/login")
	public String login(@RequestBody Admin admin, HttpSession session) {
		Integer id = adminDAO.authenticate(admin);
		if (id == null) {
			session.invalidate();
			throw new CustomUnAuthorizedException("Invalid credentials!");
		}
		session.setAttribute("adminId", id);
		return "adminPage";
	}

	@GetMapping("/{userType}/updatePassword/{uri}")
	public String updatePasswordPage(HttpSession session, @PathVariable String uri, @PathVariable String userType) {
		session.setAttribute("uri", uri);
		session.setAttribute("userType", userType);
		return "updatePassword"; // this is a html file
	}

	@PostMapping("/resetPassword")
	public void updatePasswordRequest(HttpSession session, @RequestParam String password) {
		String uri = (String) session.getAttribute("uri");
		if (uri == null)
			throw new CustomBadRequestException("Invalid Request");
		String userType = (String) session.getAttribute("userType");
		password = HashUtility.hashPassword(password);
		if (userType == "admin") {
			adminDAO.updatePassword(uri, password);
		} else {
			userDAO.updatePassword(uri, password);
		}
		
	}
}
