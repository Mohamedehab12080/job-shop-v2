package com.example.JOBSHOP.JOBSHOP.Registration.security;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Registration.exceptions.UserNotFoundException;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.service.userRepository;

@Service
public class userRegistrationDetails implements UserDetailsService{

	@Autowired	
	private userRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optionalUser =userRepository.findByEmail(username);
		User user=null;
		if(optionalUser.isPresent())
		{
			user =optionalUser.get();
			if(user.isLogin_with_google())
			{
				throw new UserNotFoundException("username not found with email : "+username);
			}
		}else
		{
			throw new UserNotFoundException("username not found with email : "+username);
		}
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}
	
	
	
//	
//	private static final long serialVersionUID = 1L;
//	private String userName;
//	private String password;
//	private boolean isEnabled;
//	private List<GrantedAuthority> authorities;
//	
//	public userRegistrationDetails(User user) {
//		super();
//		this.userName = user.getEmail();
//		this.password = user.getPassword();
//		this.isEnabled = user.isEnabled();
//		this.authorities=List.of(new SimpleGrantedAuthority(user.getUserType().name()));
//
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		
//		return password;
//	}
//
//	@Override
//	public String getUsername() {
//		return userName;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return isEnabled;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public void setEnabled(boolean isEnabled) {
//		this.isEnabled = isEnabled;
//	}
//
//	public void setAuthorities(List<GrantedAuthority> authorities) {
//		this.authorities = authorities;
//	}

}
