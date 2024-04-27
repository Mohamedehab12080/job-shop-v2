package com.example.JOBSHOP.JOBSHOP.Registration.tokens;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.User.model.User;


@Repository
public interface verificationTokenRepository extends JpaRepository<verificationToken, Long>{

	Optional<verificationToken> findByToken(String token);
	int deleteByUserId(Long id); 
	verificationToken findByUserId(Long id);
}
