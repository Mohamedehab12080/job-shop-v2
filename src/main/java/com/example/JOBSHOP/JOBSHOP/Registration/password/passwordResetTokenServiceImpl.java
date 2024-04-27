package com.example.JOBSHOP.JOBSHOP.Registration.password;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Registration.exceptions.UserNotFoundException;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.service.userRepository;
@Service
public class passwordResetTokenServiceImpl implements iPasswordResetTokenService {

	@Autowired
	private passwordResetTokenRepository passwordResetRepository;
	
	@Autowired
	private userRepository userRepository;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
	@Override
	public passwordResetToken findByToken(String token) {
		
	return 	passwordResetRepository.findByToken(token).orElseThrow(()-> new UserNotFoundException("Token Not found"));
	}
	
	@Override
	public void saveResetPasswordVerificationToken(String resetToken, User user) {
		var passwordVerificationToken=new passwordResetToken(resetToken, user);
		passwordResetRepository.save(passwordVerificationToken);
	}


	/**
	 * @author BOB
	 * @Function check on the token expiration and if not expired then it will update user to be enabled = true
	 */
	@Override
	public String validateToken(String theToken) {
		Optional<passwordResetToken> token=passwordResetRepository.findByToken(theToken);
		passwordResetToken tokenClass=token.get();
		if(!token.isPresent())
		{
			return "Invalid";
		}
		
		Calendar calendar=Calendar.getInstance(); //getting the instance of the calendar
		if((tokenClass.getExpirationTime().getTime()-calendar.getTime().getTime())<=0) // checking on the expiration of token 
		{ 
			passwordResetRepository.delete(tokenClass);// delete the token if expired 
			return "expired"; //return this message if token expired
		}
		return "valid"; //return valid to use it in the condition of controller method that @GetMapping("/verifyEmail")
	}

	@Override
	public User findUserByVerificationPasswordToken(String token) {
		passwordResetToken tokenClass=findByToken(token);
		return tokenClass.getUser();
	}

	@Override
	public void resetPassword(User user, String newPassword) {
//		user.setPassword(passwordEncoder.encode(newPassword)); 
		user.setPassword(newPassword);
		user.setEnabled(true);
		userRepository.save(user);
	}

	@Override
	public Optional<passwordResetToken> findPasswordTokenByUser(User user) {
		
		return passwordResetRepository.findByUser(user);
	}
}
