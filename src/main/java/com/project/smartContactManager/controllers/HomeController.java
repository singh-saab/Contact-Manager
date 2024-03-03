package com.project.smartContactManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.smartContactManager.entities.User;
import com.project.smartContactManager.helper.Message;
import com.project.smartContactManager.repos.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	@GetMapping("/home")
	String home(Model m) {
		m.addAttribute("myTitle", "Smart Contact Manager - Home");
		return "home";
	}

	@GetMapping("/about")
	String About(Model m) {
		m.addAttribute("myTitle", "Smart Contact Manager - About");
		return "about";
	}

	@GetMapping("/signUp")
	String signUp(Model m) {
		m.addAttribute("myTitle", "Smart Contact Manager - SignUp");
		m.addAttribute("user", new User());
		return "signUp";
	}

	@PostMapping("/doRegister")
	String registerUser( @Valid @ModelAttribute("user") User user, BindingResult result1,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m,HttpSession session) {

		try {

			if (!agreement) {
				
				throw new Exception("please accept Terms and conditions");
			}
			
			if(result1.hasErrors()) {
				System.out.println(result1);
				return "signUp";
			}
//			user.setEnabled(true);
			user.setRole("ROLE_USER");
			user.setImageUrl("blank.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User result = userRepository.save(user);
			
			m.addAttribute("user",new User());
			session.setAttribute("message",new Message("Successfully Registered " ,"alert-success"));
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message",new Message("Failed to Register!!  " + e.getMessage(),"alert-danger"));
			m.addAttribute("user", user);
		}
		return "signUp";
	}
	
	@GetMapping("/user/login")
	public String userLogin() {
		return "user/userLogin";
	}
}
