package com.accolite.miniau.accesscontrol.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.customexception.CustomUnAuthorizedException;
import com.accolite.miniau.accesscontrol.dao.AdminDAO;
import com.accolite.miniau.accesscontrol.dao.UserDAO;
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
		return StringLiteral.HOME;
	}

	@PostMapping("login1")
	public String login(@RequestParam String email, @RequestParam String pswd, HttpSession session) {

		pswd = HashUtility.hashPassword(pswd);
		Integer id = adminDAO.authenticate(email, pswd);
		if (id == null) {
			session.invalidate();
			throw new CustomUnAuthorizedException("Invalid credentials!");
		}
		session.setAttribute("adminId", id);
		return StringLiteral.HOME;
	}

	@GetMapping("/{userType}/updatePassword/{uri}")
	public String updatePasswordPage(@PathVariable String uri, @PathVariable String userType, HttpSession session) {
		session.setAttribute("uri", uri);
		session.setAttribute("userType", userType);
		return "redirect: /access-control-list-service/forgotpassword";
	}

	@GetMapping("/forgotpassword")
	public String providePasswordResetPage() {
		return "forgotpassword";
	}

	@GetMapping("/mailForpass")
	public String getMailIdForPasswordResetPage() {
		return "mailId";
	}

	@PostMapping("/mailForpass")
	public String getMailIdForPasswordReset(@RequestParam String email, HttpServletRequest request)
			throws UnknownHostException {
		boolean flag = adminDAO.isAdmin(email);
		if (!flag) {
			throw new CustomBadRequestException("Invalid Email");
		}
		adminDAO.sendPasswordLink(email, InetAddress.getLocalHost().getHostAddress(), request.getLocalPort());
		return "redirect: /access-control-list-service/";
	}

	@PostMapping("/resetPassword")
	public String updatePasswordRequest(HttpSession session, @RequestParam String pswd) {
		String uri = (String) session.getAttribute("uri");
		if (uri == null)
			throw new CustomBadRequestException("Invalid Request");
		String userType = (String) session.getAttribute("userType");
		pswd = HashUtility.hashPassword(pswd);
		if (userType.equals("admin")) {
			adminDAO.updatePassword(uri, pswd);
		} else {
			userDAO.updatePassword(uri, pswd);
		}
		return "redirect: /access-control-list-service/";
	}

	@GetMapping("/superAdmin")
	public String getSuperAdminPage() {
		return "superadminpage";
	}
}
