package com.project.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sms.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	public List<Course> findByFeesLessThan(double fees);
}
