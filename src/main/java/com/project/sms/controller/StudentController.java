package com.project.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.sms.entity.Student;
import com.project.sms.service.StudentService;

@Controller // bcoz we need to make this class to handle api requests
@RequestMapping("/student")
public class StudentController {
//	@Autowired
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

//	@RequestMapping(path = "/students", method = RequestMethod.GET) OR
	@GetMapping(path = "/")
	public String listAllStudents(Model m) {
		m.addAttribute("students", studentService.getAllStudents());
		return "students";
	}

	@GetMapping("/new")
	public String createNewStudent(Model m) {
		// create student object to hold student form data
		Student studentObj = new Student();
		m.addAttribute("studentToAdd", studentObj);
		return "create_student";
	}

	@PostMapping("/add-student")
	public String addStudent(@ModelAttribute("studentToAdd") Student student) {
		studentService.saveStudent(student);
		return "redirect:/student/";
	}

	@GetMapping("/edit-student/{id}")
	public String editStudent(@PathVariable("id") Long id, Model m) {
		m.addAttribute("studentToEdit", studentService.getStudentFromId(id));
		return "edit_student";
	}

	@PostMapping("/update-student/{id}")
	public String updateStudent(@PathVariable("id") Long id, @ModelAttribute("studentToEdit") Student student) {
		Student existingStudentObj = studentService.getStudentFromId(id);
		existingStudentObj.setFirstName(student.getFirstName());
		existingStudentObj.setLastName(student.getLastName());
		existingStudentObj.setEmail(student.getEmail());
		studentService.updateStudent(existingStudentObj);
		return "redirect:/student/";
	}

	@GetMapping("/delete-student/{id}")
	public String deleteStudent(@PathVariable("id") Long id) {
		studentService.deleteStudent(id);
		return "redirect:/student/";
	}
}
