package com.example.JOBSHOP.JOBSHOP.Registration.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class jwtProvider {

	SecretKey key=Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public String generateToken(Authentication auth)
	{
		String jwt=Jwts.builder()
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+86400000)) // 24 hours expiration
				.claim("email", auth.getName())
				.signWith(key)
				.compact();
		return jwt;
	}
	
	
	public String generateTokenResetPassword(String email) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
                .claim("email", email)
                .signWith(key)
                .compact();
    }

	
	public String getEmailFromToken(String jwt)
	{
		jwt=jwt.substring(7);

		Claims claims=
				 Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(jwt)
				.getBody();
		
		String email=String.valueOf(claims.get("email"));
		
		return email;
	}
}
