package com.example.JOBSHOP.JOBSHOP.Registration.security;

import java.util.Arrays;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class userRegistrationSecurityConfig {

	
//	 @Autowired
//	    private UserDetailsService userDetailsService;
//
//	    @Override
//	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	    }

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
//	@SuppressWarnings("removal")
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
//	{
//	     http.csrf().disable()
//         .authorizeHttpRequests()
//             .requestMatchers(
//                     new AntPathRequestMatcher("/**"),
//                     new AntPathRequestMatcher("/register/**"),
//                     new AntPathRequestMatcher("/login"),
//                     new AntPathRequestMatcher("/error"),
//                     new AntPathRequestMatcher("/images/**"))
//      		
//             .permitAll()
//             .anyRequest()
//             .authenticated()
//             .and()
//         .formLogin()
//             .loginPage("/login")
//             .usernameParameter("email")
//             .defaultSuccessUrl("/")
//             .permitAll()
//             .and()
//         .logout()
//             .invalidateHttpSession(true)
//             .clearAuthentication(true)
//             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//             .logoutSuccessUrl("/")
//             .and()
//         .cors(); // Configure CORS here
//	     return http.build();
//	}
//
//	@Bean
//	public FilterRegistrationBean<CorsFilter> CorsFilter()
//	{
//		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config=new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("http://localhost:3000");
//		config.setAllowedHeaders(Arrays.asList(
//					HttpHeaders.AUTHORIZATION,
//					HttpHeaders.CONTENT_TYPE,
//					HttpHeaders.ACCEPT
//				));
//		config.setAllowedMethods(Arrays.asList(
//				HttpMethod.GET.name(),
//				HttpMethod.POST.name(),
//				HttpMethod.PUT.name(),
//				HttpMethod.DELETE.name()
//				));
//		config.setMaxAge(3600L);
//		source.registerCorsConfiguration("/**",config);
//		FilterRegistrationBean<CorsFilter> bean=new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
//		bean.setOrder(-102);
//		return bean;
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeHttpRequests(Authorize->Authorize.requestMatchers("/images/**","/api/**","/location/**")
				.authenticated().anyRequest().permitAll()
				).addFilterBefore(new JwtTokenValidator(),BasicAuthenticationFilter.class)
		.csrf().disable()
		.cors().configurationSource(corsConfigurationSource()).and()
		.httpBasic().and().formLogin();
		
		return http.build();
	}
	
	
    public CorsConfigurationSource corsConfigurationSource() {
    	
    	return new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest arg0) {
				CorsConfiguration cfg=new CorsConfiguration();
				cfg.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
				cfg.setAllowedMethods(java.util.Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(java.util.Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authorization"));
				cfg.setMaxAge(3600L);
				return cfg;
			}
		};
    	
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Add localhost:3000 as an allowed origin
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
    }
	
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("Admin > Employer > jobSeeker");
        return roleHierarchy;
    }

}
