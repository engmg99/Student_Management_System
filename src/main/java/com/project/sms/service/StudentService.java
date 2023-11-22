package com.project.sms.service;

import java.util.List;

import com.project.sms.entity.Student;

public interface StudentService {
	public List<Student> getAllStudents();

	public Student saveStudent(Student student);

	public Student getStudentFromId(Long id);

	public Student updateStudent(Student student);

	public void deleteStudent(Long id);
	
	public List<Student> findStudentByFirstName(String name);
}
