package com.project.sms.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "students")
public class Student {
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	private Long id;

	@Column(name = "first_name", nullable = false) // if this annotation is not provided then the JSP will automatically
													// give the field name as column name ie firstName
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;
	
	@Column(name = "date_of_birth")
    private LocalDate dob;
	
    @Transient // this field will not be saved in DB
    private Integer age;
    
	// @JoinColumn can be directly used in other entities mapping but not in many to many we've to use @Join table first
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "student_course", 
	joinColumns = {
			@JoinColumn(name="student_id",referencedColumnName = "id")
	}, 
	inverseJoinColumns = {
			@JoinColumn(name="course_id",referencedColumnName = "id")
	})
	private List<Course> courses;

	public Student() {

	}

	public Student(String firstName, String lastName, String email, LocalDate dob, List<Course> courses) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Integer getAge() {
		return Period.between(dob,LocalDate.now()).getYears();
	}

}
