package com.project.sms.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping(value = "/login")
public class LoginController {
	@GetMapping("")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String homePage() {
		return "redirect:/sms/student/";
	}
}
