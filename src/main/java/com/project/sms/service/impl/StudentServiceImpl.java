package com.project.sms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.sms.entity.Student;
import com.project.sms.repo.StudentRepository;
import com.project.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
//	@Autowired
	private StudentRepository studentRepo;

	// this below steps is used to inject dependency of StudentRepositary interface
	// using Constructor DI
	// if the service class has only one constructor then we dont need to use the
	// @Autowired annotation
	public StudentServiceImpl(StudentRepository studentRepo) {
		super();
		this.studentRepo = studentRepo;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		Optional<Student> studentByEmail = studentRepo.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email already present");
        }
		return studentRepo.save(student);
	}

	@Override
	public Student getStudentFromId(Long id) {
		return studentRepo.findById(id).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return saveStudent(student);
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepo.deleteById(id);
	}

	@Override
	public List<Student> findStudentByFirstName(String name) {
		return studentRepo.findByFirstNameContaining(name);
	}

}
