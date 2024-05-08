package com.example.JOBSHOP.JOBSHOP.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
//import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class auditorAwareImpl implements AuditorAware<String>{

	
	@Override
	public Optional<String> getCurrentAuditor() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return Optional.of(authentication.getName());
        }else 
        {
        	return Optional.empty();
        }
		
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
        
		
	}

}
