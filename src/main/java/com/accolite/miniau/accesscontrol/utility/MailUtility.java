package com.accolite.miniau.accesscontrol.utility;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MailUtility {

	@Autowired
	JavaMailSender mailSender;

	private static final Logger logger = Logger.getLogger(MailUtility.class);

	@Async
	public void sendEmailAsync(String to, String subject, String text) {
		logger.info("Sending mail to " + to + " SUBJECT: " + subject);
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(to);
		email.setSubject(subject);
		email.setText(text);
		mailSender.send(email);
	}
}
