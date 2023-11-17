package com.project.sms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.sms.entity.Student;
import com.project.sms.repo.StudentRepository;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	@Autowired
	private StudentRepository stuRepo;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		List<Student> stuListToInsert = new ArrayList<>();
//		Student s1 = new Student("Ramesh", "Goyal", "ramesh@goyal.com");
//		stuListToInsert.add(s1);
//		Student s2 = new Student("Sumit", "Garg", "sumit@garg.com");
//		stuListToInsert.add(s2);
//		Student s3 = new Student("Kamal", "Khan", "kamal@khan.com");
//		stuListToInsert.add(s3);
//		stuRepo.saveAll(stuListToInsert);
	}

}
