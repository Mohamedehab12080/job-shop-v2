package com.example.JOBSHOP.JOBSHOP.Registration.security;

import org.springframework.beans.factory.annotation.Autowired;


//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.User.service.userRepository;

@Service
public class userRegistrationDetailsService /*implements UserDetailsService*/{

//	@Autowired
//	private userRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return userRepository.findByEmail(username)
//				.map(userRegistrationDetails::new) 
//				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
//	}

}
