package com.example.JOBSHOP.JOBSHOP.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
//import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class auditorAwareImpl implements AuditorAware<String>{

	
	@Override
	public Optional<String> getCurrentAuditor() {
////		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            return Optional.of(authentication.getName());
//        }
		
//		Authentication authentication=
//				SecurityContextHolder
//				.getContext()
//				.getAuthentication();
//		if(authentication==null ||
//			!authentication.isAuthenticated() ||
//				authentication instanceof AnonymousAuthenticationToken)
//		{
//			return Optional.empty();
//		} https://www.youtube.com/watch?v=lGULtrZqk-c&list=PLQ_tA3PIufHmfnKg85i_k2Me8TDYgP0KX&index=10
        return Optional.empty();
		
	}

}
