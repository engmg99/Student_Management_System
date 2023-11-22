package com.project.sms.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.sms.entity.Role;
import com.project.sms.entity.User;
import com.project.sms.repo.UserRepoistory;
import com.project.sms.service.UserService;
import com.project.sms.web.Dto.UserResgistrationDto;

@Service
public class UserServiceImpl implements UserService {

//	Autowired injecting
//	@Autowired
//	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// Constructor injection
	private UserRepoistory userRepo;

	public UserServiceImpl(UserRepoistory userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User save(UserResgistrationDto userDto) {
		Role role = new Role("ROLE_USER");
		String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
		User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), encryptedPassword,
				Arrays.asList(role));
		return userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmailId(username);// as we enter the email at login page so username is email here
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(),
				mapRolesToAuthorities(user.getRole()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
