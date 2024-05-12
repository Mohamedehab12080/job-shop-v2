package com.example.JOBSHOP.JOBSHOP.Registration.event.listener;

import java.io.UnsupportedEncodingException;

import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.JOBSHOP.JOBSHOP.Registration.event.registrationCompleteEvent;
import com.example.JOBSHOP.JOBSHOP.User.model.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
/**
 * @author BOBO
 * @Function this class is a listener for the registrationCompleteEvent
 */
@Component
@RequiredArgsConstructor
public class registrationCompleteEventListener 
						implements ApplicationListener<registrationCompleteEvent>
{
	private static final Logger logger = LoggerFactory.getLogger(registrationCompleteEventListener.class);
	@Autowired
	private com.example.JOBSHOP.JOBSHOP.Registration.service.userService userService;
	@Autowired
	private JavaMailSender mailSender;
	
	User theUser;
	String url;
	
	public User getTheUser() {
		return theUser;
	}

	public void setTheUser(User theUser) {
		this.theUser = theUser;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void onApplicationEvent(registrationCompleteEvent event) {
		
		//1- Get the newly registered user
		theUser=event.getUser();
		//2- Create a verification token for the user
		String verificationToken=UUID.randomUUID().toString();
		//3- Save the verification token for the user
		userService.saveUserVerificationToken(theUser,verificationToken);
		//4- Build the verification Url to be sent to the user
		url=event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;
		//5- Send the verification email to the registered user.
		try {
			sendVerificationEmail(url);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		logger.info("Click the link to verify your registration : {}",url);
	}

	public void sendVerificationEmail(String url) throws UnsupportedEncodingException, MessagingException
	{
		String subject="Email verification";
		String senderName="User verification service";
		String mailContent="<p> Hi, "+theUser.getUserName()+", </p>"+
							"<p> Thank you for your registration on JOB SHOP.</p>"+
							"<a href=\""+url+"\">Verify your email to activate your account</a>"+
							"<p>Thank you <br> Users verification Service";
		emailMessage("",subject,senderName,mailContent,mailSender,theUser);
	}
	public void sendVerificationEmailPasswordReset(User resetuser,String url) throws UnsupportedEncodingException, MessagingException
	{
		String subject="Password reset request verification";
		String senderName="Users verification service";
		String mailContent="<p> Hi, "+resetuser.getUserName()+", </p>"+
							"<p> please follow the link below to reset password verification.</p>"+
							"<a href=\""+url+"\">Reset password</a>"+
							"<p>Thank you <br> Users verification Service";
		emailMessage("",subject,senderName,mailContent,mailSender,theUser);
	}
	public void sendMailAfterAcceptedApplyToTheCompany(User resetuser,String url,String applicationId,String postTitle) throws UnsupportedEncodingException, MessagingException
	{
		String subject="Apply for jobs";
		String senderName="Apply for jobs service";
		String mailContent="<p> Hi, "+resetuser.getUserName()+", </p>"+
							"<p> please follow the link below to show the application with Application Id :"+applicationId+" on post : "+postTitle+".</p>"+
							"<a href=\""+url+"\">Show Application</a>"+
							"<p>Thank you <br> Apply for jobs service...";
		System.out.println("User for sending mail : "+resetuser.getEmail());
		emailMessage("",subject,senderName,mailContent,mailSender,resetuser);
	}
	public void sendMailAcceptedApplicationToThejobSeeker(String fromMail,User resetuser,String url,String applicationId,String postTitle) throws UnsupportedEncodingException, MessagingException
	{
		String subject="Apply for jobs";
		String senderName="Apply for jobs service";
		String mailContent="<p> Hi, "+resetuser.getUserName()+", </p>"+
						    "<p><strong>congratulations for your application acceptance...</strong></p>"+
							"<p> please follow the link below to show the application with Application Id :"+applicationId+" on post : "+postTitle+".</p>"+
							"<a href=\""+url+"\">Show Application</a>"+
							"<p>Thank you <br> Apply for jobs service...";
		System.out.println("User for sending mail : "+resetuser.getEmail());
		emailMessage(fromMail,subject,senderName,mailContent,mailSender,resetuser);
	}
	
	public void sendMailRejectedApplicationToTheJobSeeker(String fromMail,User resetuser,String url,String applicationId,String postTitle) throws UnsupportedEncodingException, MessagingException
	{
		String subject="Apply for jobs";
		String senderName="Apply for jobs service";
		String mailContent="<p> Hi, "+resetuser.getUserName()+", </p>"+
						    "<p><strong>congratulations for your applications acceptance...</strong></p>"+
							"<p> please follow the link below to show the application with Application Id :"+applicationId+" on post : "+postTitle+".</p>"+
							"<a href=\""+url+"\">Show Application</a>"+
							"<p>Thank you <br> Apply for jobs service...";
		System.out.println("User for sending mail : "+resetuser.getEmail());
		emailMessage(fromMail,subject,senderName,mailContent,mailSender,resetuser);
	}
	
	private static void emailMessage(String fromMail,String subject,String senderName,String mailContent,JavaMailSender mailSender,User user) throws UnsupportedEncodingException, MessagingException
	{
		MimeMessage message=mailSender.createMimeMessage();
		var messageHelper=new MimeMessageHelper(message);
		
		if(fromMail !=null && !fromMail.equals(""))
		{
			messageHelper.setFrom(fromMail,senderName);
		}else
		{
			messageHelper.setFrom("mohamedehab12080@gmail.com",senderName);
		}
		messageHelper.setTo(user.getEmail());
		messageHelper.setSubject(subject);
		messageHelper.setText(mailContent,true);
		mailSender.send(message);
	}
}
