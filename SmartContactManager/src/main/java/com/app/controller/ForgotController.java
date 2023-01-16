package com.app.controller;

import java.text.DecimalFormat;
import java.util.Random;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.helper.Message;
import com.app.model.User;
import com.app.repository.IUserRepository;
import com.app.service.EmailService;

@Controller
public class ForgotController {

	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping("/forgot")
	public String openEmailForm()
	{
		return "forgot_email_form";
	}
	
	@PostMapping("/send-otp")
	public String sendOTPForm(@RequestParam("email")String email,HttpSession session)
	{
		System.out.println(email);
		
		//generationg OTP of 6 digit
		String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));
		System.out.println("OTP : "+otp);
		
		String subject="OTP from SCM";
		String message=""
				+ "<div style='border:1px solid grey; padding:20px'  >"
				+ "<h1>"
				+ "OTP : "
				+ "<b>"+"SCM-"+otp
				+ "</n>"
				+ "</h1>"
				+ "<div>";
		String to=email;
		
		boolean flag = this.emailService.sendEmail(message, subject, to);
		
		if(flag)
		{
			session.setAttribute("email", to);
			session.setAttribute("emailOTP", otp);
			return "verify-otp";
		}
		else 
			{
				session.setAttribute("message", "Invalid email !!!!");
				return "forgot_email_form";
			}
	
	   }
	
	
	@PostMapping("/verify-otp")
	public String verifyOTP(@RequestParam("otp") String otp,HttpSession session)
	{
		String emailOTP=(String) session.getAttribute("emailOTP");
		String email=(String) session.getAttribute("email");
		
		if(emailOTP.equals(otp))
		{
			User user = this.userRepository.getUserByUserName(email);
			if(user==null)
			{
				session.setAttribute("message", "the email you have entered does not exist...");
				return "forgot_email_form";
			}
			else
			{
				return "password-change-form";
			}
			
		}
		else 
		{
			session.setAttribute("message", "You have entered wrong otp !!!");
			return "verify-otp";
		}
		
	}
	
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("newPassword") String newPassword,HttpSession session)
	{
		User user = this.userRepository.getUserByUserName((String)session.getAttribute("email"));
		user.setPassword(this.encoder.encode(newPassword));
		this.userRepository.save(user);
		
		return "redirect:/signin?change=Password Changed Successfully...";
	}
	
	
}
