package com.project.sms.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.sms.entity.User;
import com.project.sms.web.Dto.UserResgistrationDto;

public interface UserService extends UserDetailsService{
	
	public User save(UserResgistrationDto userDto);
}
