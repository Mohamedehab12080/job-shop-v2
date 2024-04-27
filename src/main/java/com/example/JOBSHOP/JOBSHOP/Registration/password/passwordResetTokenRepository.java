package com.example.JOBSHOP.JOBSHOP.Registration.password;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.User.model.User;

@Repository
public interface passwordResetTokenRepository extends JpaRepository<passwordResetToken,Long>{

	
	Optional<passwordResetToken> findByToken(String token);
	Optional<passwordResetToken> findByUser(User user);
	
}
