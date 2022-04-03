package com.Student.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.Student.model.Student;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Student.repo.StudentRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api")
public class CustomerController {

	@Autowired
	StudentRepository repository;

	@GetMapping("students-list")
	public ResponseEntity<List<Student>> allstudents() {
		List<Student> students = new ArrayList<>();
		try {
			repository.findAll().forEach(students::add);
			if (students.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("student/{student_id}")
	public ResponseEntity<List<Student>>  allstudentByID(@PathVariable("student_id") long id) {
		try {
			List<Student> students = new ArrayList<>();
			Optional<Student> student =repository.findById(id);
			students.add(student.get());
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "save-student")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		try {
			repository.save(student);
			return new ResponseEntity<>(student, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
    @PostMapping(value = "update-student/{student_id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable("student_id") Long student_id) {
            try {
                Optional<Student> student1= repository.findById(student_id);
                student1.get().setStudent_branch(student.getStudent_branch());
				student1.get().setStudent_email(student.getStudent_email());
				student1.get().setStudent_name(student.getStudent_name());
                repository.save(student1.get());
                return new ResponseEntity<>(student1.get(), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
            }
    }
	@DeleteMapping("delete-student/{student_id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("student_id") Long student_id) {
		try {
			repository.deleteById(student_id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}



}



