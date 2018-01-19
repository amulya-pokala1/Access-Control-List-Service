/*
 * 
 */
package com.accolite.miniau.accesscontrol.utility;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
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
	public void sendEmailAsync(String to, String subject, String text) {
		logger.info("Sending mail to " + to + " SUBJECT: " + subject);
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(to);
		email.setSubject(subject);
		email.setText(text);
		mailSender.send(email);
	}
}
