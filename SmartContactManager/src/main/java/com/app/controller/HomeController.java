package com.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.helper.Message;
import com.app.model.User;
import com.app.repository.IUserRepository;

import ch.qos.logback.core.joran.action.NewRuleAction;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUserRepository userRepository;
	
	@RequestMapping("/")
	public String homePage(Model model)
	{
		model.addAttribute("title","Home - Smart Contact Manager");
		return "home";
	}
	
	@RequestMapping("/about")
	public String aboutPage(Model model)
	{
		model.addAttribute("title","About - Smart Contact Manager");
		
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signupPage(Model model)
	{
		model.addAttribute("title","Signup - Smart Contact Manager");
		model.addAttribute("user", new User());
		
		return "signup";
	}
	
	@GetMapping("/signin")
	public String loginPage(Model model)
	{
		model.addAttribute("title","Login - Smart Contact Manager");
		
		return "login";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result,
			                   @RequestParam(value="agreement",defaultValue = "false") Boolean agreement,
			                   Model model, HttpSession session)
	{
		
		try {
			if(!agreement)
			{
				System.out.println("You hve not agreed the terms and conditions.");
				throw new Exception("You hve not agreed the terms and conditions.");
			}
			if(result.hasErrors())
			{
				System.out.println("ERROR : "+result.toString());
				model.addAttribute("user",user);
				return "signup";
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			User u = userRepository.save(user);
			System.out.println(u);
			model.addAttribute("user",new User());
			session.setAttribute("message",new Message("Registered Successfully !!","alert-success"));
			return "signup";
			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message",new Message("Something Went Wrong !!"+e.getMessage() ,"alert-danger"));
			return "signup";
		}
		
	}
}
