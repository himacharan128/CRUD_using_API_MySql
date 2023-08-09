package com.techmojo.sqlcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techmojo.sqlcrud.entity.Student;

public interface StudentRepo extends JpaRepository<Student,Long> {
	
}
