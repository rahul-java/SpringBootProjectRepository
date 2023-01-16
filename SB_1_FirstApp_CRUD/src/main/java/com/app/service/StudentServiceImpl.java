package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.StudentNotFoundException;
import com.app.model.Student;
import com.app.repository.StudentRepository;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentRepository repository;
	
	@Override
	public Integer saveStudent(Student student) {
		// TODO Auto-generated method stub
		return repository.save(student).getId();
	}

	@Override
	public Student getStudentById(Integer id) {
		Student student=repository.findById(id).orElseThrow(()-> new StudentNotFoundException("Student "+id+" is NOT Available..")) ;
		return student;
	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void deleteStudentById(Integer id) {
		repository.deleteById(id);
		
	}

}
