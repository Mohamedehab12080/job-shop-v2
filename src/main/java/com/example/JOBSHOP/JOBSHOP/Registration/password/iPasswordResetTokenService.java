package com.example.JOBSHOP.JOBSHOP.Registration.password;

import java.util.Optional;

import com.example.JOBSHOP.JOBSHOP.User.model.User;


public interface iPasswordResetTokenService {

	
	passwordResetToken findByToken(String token);
	void saveResetPasswordVerificationToken(String resetPasswordToken,User user);
	String validateToken(String token);
	User findUserByVerificationPasswordToken(String token);
	void resetPassword(User user,String newPassword);
	Optional<passwordResetToken> findPasswordTokenByUser(User user);
}
