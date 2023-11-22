package com.project.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sms.entity.User;

public interface UserRepoistory extends JpaRepository<User, Long> {
	public User findByEmailId(String email);
}
