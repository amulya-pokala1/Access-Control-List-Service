package com.accolite.miniau.accesscontrol.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.miniau.accesscontrol.dao.UserDAO;
import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.utility.MailUtility;

@RestController
public class AclController {

	@Autowired
	UserDAO userDAO;

	@Autowired
	MailUtility mailUtil;

	private static Logger logger = Logger.getLogger(AclController.class);

	@GetMapping(value = "/ACL/{userId}")
	public List<Permission> isAuthenticated(@PathParam(value = "userId") int userId) {

		logger.info("Request for " + userId);
		// if user is available
		// if password match .. send permissions
		// else throw not CustomNotFoundException
		// TODO complete this method
		return null;
	}

	@GetMapping("test")
	public void test() {
		mailUtil.sendEmailAsync("tarun.k@accoliteindia.com","Hello Tarun" , "This is a sample mail");
	}
}
