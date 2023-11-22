package com.project.sms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.sms.entity.Course;
import com.project.sms.entity.Student;
import com.project.sms.repo.CourseRepository;
import com.project.sms.service.StudentService;

@Controller // bcoz we need to make this class to handle api requests
@RequestMapping("/student")
public class StudentController {
//	@Autowired
//	private StudentService studentService;

	// Constructor Injection, Constructor Injection of Course Repository
	private StudentService studentService;
	private CourseRepository courseRepo;
	public StudentController(StudentService studentService, CourseRepository courseRepo) {
		super();
		this.studentService = studentService;
		this.courseRepo = courseRepo;
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
//		List<Course> courseList = new ArrayList<>();
//		List<Course> existingCourseList = new ArrayList<>();
//		Course courseObj = new Course();
//		courseObj.setAbbreviation("ng");
//		courseObj.setFees(5000);
//		courseObj.setModules(12);
//		courseObj.setTitle("Java");
//		existingCourseList.add(courseObj);
//		courseObj = new Course();
//		courseObj.setAbbreviation("ng");
//		courseObj.setFees(5000);
//		courseObj.setModules(12);
//		courseObj.setTitle("Angular");
//		existingCourseList.add(courseObj);
//		courseObj = new Course();
//		courseObj.setAbbreviation("ng");
//		courseObj.setFees(5000);
//		courseObj.setModules(12);
//		courseObj.setTitle("Python");
//		courseList.add(courseObj);
//		student.setCourses(courseList);
//		existingStudentObj.setCourses(existingCourseList);
//		boolean found=false;
//		for(Course newObj: student.getCourses()) {
//			for (Course obj: existingStudentObj.getCourses()) {
//				System.out.println("New: "+newObj.getTitle());
//				System.out.println("Exist: "+obj.getTitle());
//				if(newObj.getTitle().equals(obj.getTitle())) {
//					found = true;
//				}
//			}
//		}
//		if(!found) {
//			System.out.println("add");
//		}
		studentService.updateStudent(existingStudentObj);
		return "redirect:/student/";
	}

	@GetMapping("/delete-student/{id}")
	public String deleteStudent(@PathVariable("id") Long id) {
		studentService.deleteStudent(id);
		return "redirect:/student/";
	}

	@GetMapping("/find/{name}")
	public List<Student> findStudentsContainingByName(@PathVariable("name") String name) {
		return studentService.findStudentByFirstName(name);
	}

	@GetMapping("/search/{price}")
	public List<Course> findCourseLessThanPrice(@PathVariable("price") double price) {
		return courseRepo.findByFeesLessThan(price);
	}
}
