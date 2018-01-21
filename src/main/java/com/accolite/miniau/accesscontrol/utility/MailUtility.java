/*
 * 
 */
package com.accolite.miniau.accesscontrol.utility;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * The Class MailUtility.
 */
@Component
public class MailUtility {

	/** The mail sender. */
	@Autowired
	JavaMailSender mailSender;

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(MailUtility.class);

	/**
	 * Send email async.
	 *
	 * @param to
	 *            the to
	 * @param subject
	 *            the subject
	 * @param text
	 *            the text
	 */
	@Async
	public boolean sendEmailAsync(String to, String subject, String text) {
		logger.info("Sending mail to " + to + " SUBJECT: " + subject);
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(to);
		email.setSubject(subject);
		email.setText(text);
		try {
			mailSender = new JavaMailSenderImpl();
			mailSender.send(email);
		} catch (MailException e) {
			return false;
		}
		return true;
	}
}
