package com.startup.java.email_poc.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.startup.java.email_poc.mail.EmailService;

@RestController
public class EndpointsImpl implements Endpoints {

	@Autowired
	private EmailService emailService;

	@Override
	@GetMapping("/text-email")
	public boolean sendTextEmail(@RequestParam(value = "to", defaultValue = "abdm03142014@gmail.com") String to,
			@RequestParam(value = "subject", defaultValue = "Assalamu Alaikum wa Rahmathullah i wa Barakathuhu") String subject,
			@RequestParam(value = "text", defaultValue = "Alhamdulillah!!!") String text) {
		emailService.sendSimpleMessage(to, subject, text);
		return true;
	}

	@Override
	@GetMapping("/html-email")
	public boolean sendHtmlEmail(@RequestParam(value = "to", defaultValue = "abdm03142014@gmail.com") String to,
			@RequestParam(value = "subject", defaultValue = "Assalamu Alaikum wa Rahmathullah i wa Barakathuhu") String subject,
			@RequestParam(value = "text", defaultValue = "Alhamdulillah!!!") String text) {
		Map<String, Object> templateModel = new HashMap<>();
		templateModel.put("recipientName", to);
		templateModel.put("text", text);
		templateModel.put("senderName", "poc.a5221985@gmail.com");
		emailService.sendMessageUsingThymeleafTemplate(to, subject, templateModel);
		return true;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

}
