package com.startup.java.email_poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@SpringBootApplication
public class EmailPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailPocApplication.class, args);
	}

	@Bean
	public SpringResourceTemplateResolver thymeleafTemplateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	@Bean
	public ResourceBundleMessageSource emailMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("/mailMessages");
		return messageSource;
	}

	@Bean
	public SpringTemplateEngine thymeleafTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(thymeleafTemplateResolver());
		templateEngine.setTemplateEngineMessageSource(emailMessageSource());
		return templateEngine;
	}

}
