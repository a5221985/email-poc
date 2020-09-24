package com.startup.java.email_poc.mail;

import java.util.Map;

public interface EmailService {
	void sendSimpleMessage(String to, String subject, String text);
	void sendHtmlMessage(String to, String subject, String htmlBody);
	void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel);
}