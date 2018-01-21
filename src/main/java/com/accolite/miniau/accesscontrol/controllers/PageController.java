package com.accolite.miniau.accesscontrol.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
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
			return "homepage";
		}
		int adminId = (int) session.getAttribute("adminId");
		model.addAttribute("name", adminDAO.getAdminName(adminId));
		return "adminpage";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect: /access-control-list-service/";
	}

	@PostMapping("login1")
	public String login(@RequestParam String email,@RequestParam String pswd, HttpSession session) {
		
		pswd = HashUtility.hashPassword(pswd);
		Integer id = adminDAO.authenticate(email,pswd);
		if (id == null) {
			session.invalidate();
			throw new CustomUnAuthorizedException("Invalid credentials!");
		}
		session.setAttribute("adminId", id);
		return "redirect: /access-control-list-service/";
	}

	@GetMapping("/{userType}/updatePassword/{uri}")
	public String updatePasswordPage(@PathVariable String uri, @PathVariable String userType, HttpSession session) {
		System.out.println("page req" + uri);
		session.setAttribute("uri", uri);
		System.out.println(session.getAttribute("uri"));
		session.setAttribute("userType", userType);
		return "redirect:/forgotpassword";
	}

	@GetMapping("/forgotpassword")
	public String providePasswordResetPage() {
		return "forgotpassword";
	}

	@PostMapping("/resetPassword")
	public String updatePasswordRequest(HttpSession session, @RequestParam String pswd) {
		System.out.println(pswd);
		String uri = (String) session.getAttribute("uri");
		if (uri == null)
			throw new CustomBadRequestException("Invalid Request");
		String userType = (String) session.getAttribute("userType");
		pswd = HashUtility.hashPassword(pswd);
		System.out.println(userType);
		if (userType.equals("admin")) {
			System.out.println(1);
			adminDAO.updatePassword(uri, pswd);
		} else {
			System.out.println(2);
			userDAO.updatePassword(uri, pswd);
		}
		return "redirect: /access-control-list-service/";
	}

	@GetMapping("/superAdmin")
	public String getSuperAdminPage() {
		return "superadminpage";
	}
}
