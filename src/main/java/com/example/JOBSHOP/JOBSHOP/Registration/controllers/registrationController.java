package com.example.JOBSHOP.JOBSHOP.Registration.controllers;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Registration.tokens.verificationToken;
import com.example.JOBSHOP.JOBSHOP.User.model.Role;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.DTO.jobSeekerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.JOBSHOP.JOBSHOP.Registration.event.registrationCompleteEvent;
import com.example.JOBSHOP.JOBSHOP.Registration.event.listener.registrationCompleteEventListener;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import com.example.JOBSHOP.JOBSHOP.Registration.password.iPasswordResetTokenService;
@Controller
@RequestMapping("/register")
public class registrationController {

	@Autowired
	private  userServiceInterface userServiceI;
	
	@Autowired
	private  iPasswordResetTokenService iPasswordResetTokenService;
	
	@Autowired
	private registrationCompleteEventListener registrationCompleteEventListener;
	
	@Autowired
	private ApplicationEventPublisher applicationPublisher; //application publisher that publish the event for specific class
	
	private String companyAdminEmail;

	@GetMapping("/form") //(Tested)
	public String showRegistrationForm(Model model)
	{
		jobSeeker jobSeeker=new jobSeeker();
		model.addAttribute("jobSeeker",jobSeeker);
		
		companyAdministrator companyAdministrator=new companyAdministrator();
		companyAdministrator.setEmail(companyAdminEmail);
		model.addAttribute("companyAdministrator",companyAdministrator);
		
		return "signup";
	}
	
	@PostMapping("/save-company")
	public String saveCompanyAdmin(@ModelAttribute("companyAdministrator") companyAdministratorDTO companyAdministrator
								  ,@RequestParam("logo") MultipartFile profileImage
								  ,Model model
								  ,HttpServletRequest request) throws IOException
	{
		Optional<User> oldCompany=userServiceI.findByEmail(companyAdministrator.getEmail()); 
		
		if(oldCompany.isPresent())
		{

			return "redirect:/register/form?company_exists";			

		}else 
		{
			companyAdministratorDTO newcompany=companyAdministrator;
			newcompany.setPicture(profileImage.getBytes());
			newcompany.setUserType(Role.Admin);
			companyAdminEmail=companyAdministrator.getEmail();
			User theUser =userServiceI.registerCompanyAdministrator(newcompany); // service that save the user Info
			// publish the event for the registration that include the user object and the url for email Verification after save the user info
			applicationPublisher.publishEvent(new registrationCompleteEvent(theUser, applicationUrl(request))); // this event contains listener that sending email verification to the user gmail
			return "redirect:/register/form?company_success";	
		}
	
		
	}
	
	@PostMapping("/save-jobSeeker") //(Tested)
	public String saveJobSeeker(@ModelAttribute("jobSeeker") jobSeekerDTO jobSeeker
								  ,@RequestParam("profileImage") MultipartFile profileImage
								  ,Model model,HttpServletRequest request)
	{
	
		Optional<User> oldUser=userServiceI.findByEmail(jobSeeker.getEmail()); 
		if(oldUser.isPresent())
		{
			return "redirect:/register/form?jobSeeker_exists";	
		}else 
		{
			jobSeekerDTO newJobSeeker=jobSeeker;
			newJobSeeker.setUserType(Role.jobSeeker);
			newJobSeeker.setPicture(jobSeeker.getPicture());
			User theUser =userServiceI.registerJobSeeker(jobSeeker); // service that save the user Info
			// publish the event for the registration that include the user object and the url for email Verification after save the user info
			applicationPublisher.publishEvent(new registrationCompleteEvent(theUser, applicationUrl(request))); // this event contains listener that sending email verification to the user gmail
			return "redirect:/register/form?jobSeeker_success";
		}
		
	}
	
//	// show the forgot password form after click on forgot password at the login page
//	@GetMapping("/forgot-password-request")
//	public String forgotPasswordForm()
//	{
//		return "forgot-password-form"; //display the forgot password form
//	}
	
//	// verifing the email by sending gmail for the user including verification link
//	@PostMapping("/forgot-password")
//	public String resetPasswordRequest(HttpServletRequest request,Model model)
//	{
//		passwordResetToken alreadyToken;
//		//getting the input email from the forgot password page
//		String email=request.getParameter("email");
//		User user=userServiceI.findByEmail(email); // find the user by the email entered
//		if(user==null) //check if the user not found
//		{
//			return "redirect:/register/forgot-password-request?not_found"; //display not found at the forgot password form
//		}else 
//		{
//			if(iPasswordResetTokenService.findPasswordTokenByUser(user).isPresent())
//			{
//				alreadyToken=iPasswordResetTokenService.findPasswordTokenByUser(user).get();
//				return "redirect:/register/password-reset-form?token="+alreadyToken.getToken();
//			}else 
//			{
//				String ResetToken=UUID.randomUUID().toString();//generate random token
//				iPasswordResetTokenService.saveResetPasswordVerificationToken(ResetToken, user); //save the token generated for the user found
//				String url =applicationUrl(request)+"/register/password-reset-form?token="+ResetToken; //making the url including the created token at the parameter token
//				try {
//					registrationCompleteEventListener.sendVerificationEmailPasswordReset(user,url); //sending the email from the listener including the created url
//				} catch (UnsupportedEncodingException | MessagingException e) {
//					model.addAttribute("error",e.getMessage());//display the error if occured
//				}
//			}
//		}
//		return "redirect:/register/forgot-password-request?success"; //display message in the page of sending the verification to the user on Gmail.
//	}
//	 
//	@GetMapping("/password-reset-form") //invoked when the user click on the link sent on Gmail
//	public String resetPasswordForm(@RequestParam("token") String token,Model model)
//	{
//		model.addAttribute("token",token); // sending the token from the parameter to the hidden input in password-reset-form
//		return "password-reset-form";
//	}
	
//	/**
//	 * 
//	 * @author BOBO
//	 * @Function reset the password replace the old with the new
//	 */
//	@PostMapping("/reset-password")
//	public String resetPassword(HttpServletRequest request)
//	{
//		String theToken=request.getParameter("token"); //getting the hidden input token from the password reset form
//		String newPassword=request.getParameter("password");
//		String verificationPasswordTokenValidateResult=iPasswordResetTokenService.validateToken(theToken);
//		if(!verificationPasswordTokenValidateResult.equalsIgnoreCase("valid")) //validate the token if it is expired or invalid not found
//		{
//			return "redirect:/error?invalid_token"; 
//		}
//		User theUser=iPasswordResetTokenService.findUserByVerificationPasswordToken(theToken);//Find the user by the token if present
//		if(theUser!=null)
//		{
//			// set the new password of the user and then update the user. 
//			iPasswordResetTokenService.resetPassword(theUser, newPassword);
//			return"redirect:/login?reset_success";
//		}
//		return "redirect:/error?not_found";
//	}
//	
	
	
	@GetMapping("/resend/{resendemail}") //(Tested)
	public String resendEmailVerification(@PathVariable("resendemail")String resendemail)
	{
		return "resendEmail";
	}
	
	@PostMapping("/resend")
	public String resendEmailPostMethod(@RequestParam("email") String email,HttpServletRequest request)
	{
		Optional<User> theUser=userServiceI.findByEmail(email);
		if(theUser.isPresent())
		{
			applicationPublisher.publishEvent(new registrationCompleteEvent(theUser.get(),applicationUrl(request))); // this event contains listener that sending email verification to the user gmail
			return "redirect:/register/resend/"+email+"?sent";
		}else 
		{
			return "redirect:/register/resend/"+email+"?email_notFound";
		}
	}
	
	/**
	 * 
	 * @param token
	 * @return successfully verified if the token not expired
	 */
	@GetMapping("/verifyEmail") //(Tested)
	public String verifyEmail(@RequestParam("token") String token)
	{
		verificationToken theToken=userServiceI.findVerificationToken(token);
		
		if(theToken!=null)
		{
			if(theToken.getUser().isEnabled())
			{
				if(theToken.getUser().getUserType()== Role.Admin)
				{
					return "redirect:/companyAdmin/add-field/"+theToken.getUser().getId()+"?verified";//Must be the email
				}else 
				{
					return "redirect:/jobSeeker/add-skills/"+theToken.getUser().getId()+"?verified";
				}
				
			}
			String verificationResult=userServiceI.validateToken(token); //getting the returned value depend on the token sent.
	 
			switch(verificationResult.toLowerCase())
			{
				case "expired":
					return 	"redirect:/register/resend/"+theToken.getUser().getEmail()+"?token_expired";// if param is token_expired : show button resend email and show the form send email that contains email value 
				case "valid"://it will include the if condition
					if(theToken.getUser().getUserType() == Role.Admin) // it will be the user type of companyAdministrator
					{
						return "redirect:/companyAdmin/add-field/"+theToken.getUser().getId()+"?valid";//Must be the email
					}else 
					{
						return "redirect:/jobSeeker/add-skills/"+theToken.getUser().getId()+"?valid";
					}
				default:
					return "redirect:/error?invalid";
			}

		}else 
		{
			return "redirect:/register/resend/"+"Not"+"?invalid_token";
		}
	}

	@GetMapping("/error") //()
	public String errorDisplay()
	{
		return "errors";
	}
	/**
	 * 
	 * @param HttpServletRequest request
	 * @return return url  "http://"+request.serverName+":"+request.getServerPort()+request.getContextPath() (for email verification)
	 */ 
	public String applicationUrl(HttpServletRequest request) {
		// getting the url back for the application event
		return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); 
	}
}
