package com.project.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.sms.service.UserService;
import com.project.sms.web.Dto.UserResgistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrartionController {

	// Constructor Injection
	private UserService userService;
	public UserRegistrartionController(UserService userService) {
		this.userService = userService;
	}

	// create a method which'll return an empty UserDTO Obj so whenever someone
	// enter data on our registration form then that data will be saved to this
	// object. EITHER WE CAN USE THIS APPROACH
	@ModelAttribute("userDTO")
	public UserResgistrationDto userRegDtoEmptyObj() {
		return new UserResgistrationDto();
	}

	// OR WE CAN USE THIS IN WHICH WE ADD EMPTY OBJECT USING MODEL
//	@GetMapping()
//	public String showRegistrationForm(Model m) {
//		m.addAttribute("usrDTO",new UserResgistrationDto());
//		return "registration";
//	}

	@GetMapping()
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping(value = "")
	public String registerUserAccount(@ModelAttribute("userDTO") UserResgistrationDto userDto) {
		userService.save(userDto);
		return "redirect:/registration?success";
	}
}
