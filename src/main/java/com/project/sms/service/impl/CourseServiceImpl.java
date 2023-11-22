package com.project.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sms.entity.Course;
import com.project.sms.repo.CourseRepository;
import com.project.sms.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepo;

	@Override
	public List<Course> findByFeeLessThan(double fees) {
		return courseRepo.findByFeesLessThan(fees);
	}

}
