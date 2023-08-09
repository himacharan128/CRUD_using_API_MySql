package com.techmojo.sqlcrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techmojo.sqlcrud.entity.Student;
import com.techmojo.sqlcrud.repository.StudentRepo;

@RestController
public class StudentController {
	@Autowired
	StudentRepo studentrepo;
	@PostMapping("/students")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		//System.out.println(student);
		return new ResponseEntity<>(studentrepo.save(student),HttpStatus.CREATED);
	}
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getStudents(){
		return new ResponseEntity<> (studentrepo.findAll(),HttpStatus.OK);
	}
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable long id){
		Optional<Student> student=studentrepo.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<>(student.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable long id,@RequestBody Student student1){
		Optional<Student> student=studentrepo.findById(id);
		if(student.isPresent()) {
			student.get().setStudentName(student1.getStudentName());
			student.get().setStudentEmail(student1.getStudentEmail());
			student.get().setStudentAddress(student1.getStudentAddress());
			return new ResponseEntity<>(studentrepo.save(student.get()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable long id){
		Optional<Student> student=studentrepo.findById(id);
		if(student.isPresent()) {
			studentrepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
