package com.startup.java.email_poc.mail;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine thymeleafTemplateEngine;

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("poc.a5221985@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	@Override
	public void sendHtmlMessage(String to, String subject, String htmlBody) {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(htmlBody, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		emailSender.send(message);
	}

	@Override
	public void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel) {
		Context thymeleafContext = new Context();
		thymeleafContext.setVariables(templateModel);
		String htmlBody = thymeleafTemplateEngine.process("template-thymeleaf.html", thymeleafContext);
		sendHtmlMessage(to, subject, htmlBody);
	}

	public JavaMailSender getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	public SpringTemplateEngine getThymeleafTemplateEngine() {
		return thymeleafTemplateEngine;
	}

	public void setThymeleafTemplateEngine(SpringTemplateEngine thymeleafTemplateEngine) {
		this.thymeleafTemplateEngine = thymeleafTemplateEngine;
	}

}
