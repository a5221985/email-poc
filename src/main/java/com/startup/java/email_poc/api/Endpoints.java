package com.startup.java.email_poc.api;

public interface Endpoints {
	boolean sendTextEmail(String to, String subject, String text);
	boolean sendHtmlEmail(String to, String subject, String text);
}
