package com.Demo.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Demo.models.Student;

public interface StudentRepo extends CrudRepository<Student,Long> {

	List<Student> findByName(String name);
}
