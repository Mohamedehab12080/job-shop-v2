package com.example.JOBSHOP.JOBSHOP.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class auditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		//should get userName from security
		return Optional.of("test user");
	}

}
