package com.project.sms.service;

import java.util.List;

import com.project.sms.entity.Course;

public interface CourseService {
	public List<Course> findByFeeLessThan(double fees);
}
