package com.example.JOBSHOP.JOBSHOP.Registration.config;

import java.util.Properties;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class mailSenderConfig {

	  @Bean
	    public JavaMailSender javaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	        mailSender.setUsername("mohamedehab12080@gmail.com");
	        mailSender.setPassword("saoh drog bpar bgcf");
	        // Additional properties configuration if needed
	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.transport.protocol", "smtp");
	        return mailSender;
	    }
}
