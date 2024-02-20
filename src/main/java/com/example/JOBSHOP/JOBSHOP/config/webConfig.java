package com.example.JOBSHOP.JOBSHOP.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class webConfig {
	
	@Bean
	AuditorAware<String> auditorAware()
	{
		return new auditorAwareImpl();
	}
}
