package com.project.smartContactManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.smartContactManager.entities.User;
import com.project.smartContactManager.helper.Message;
import com.project.smartContactManager.repos.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	UserRepository userRepository;

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
	String registerUser(@ModelAttribute("user") User user,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m,HttpSession session) {

		try {

			if (!agreement) {
				System.out.println("please accept Terms and conditions");
				throw new Exception("please accept Terms and conditions");
			}

			user.setEnabled(true);
			user.setRole("ROLE_USER");
			user.setImageUrl("blank.png");
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
}
