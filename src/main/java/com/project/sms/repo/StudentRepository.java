package com.project.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sms.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	// param1 -> datatype of entity, param2-> datatype of primary key

	// we don't need to add @Repository annotation on top of this interface because
	// this JPA repo has the default implementation of the Simple Repository class
	// and if we go to the Simple Repository class we'll see that the @ repo
	// annotation is implemented. Also by default this simple repo class provides
	// the @transactional for all its method. so we dont need to add this
	// @transactional annotation in the Service classes
}
