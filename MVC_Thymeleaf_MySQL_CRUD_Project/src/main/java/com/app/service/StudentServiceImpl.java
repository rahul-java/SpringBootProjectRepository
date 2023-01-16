package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Student;
import com.app.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public void saveStudent(Student student) {
		studentRepository.save(student);

	}

	@Override
	public List<Student> getAllStudents() {
		
		return studentRepository.findAll();
	}

	@Override
	public Student getOneStudent(long id) {
		Student student = studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Student id : "+id));
		return student;
	}

	@Override
	public void updateStudent(Student student) {
		studentRepository.save(student);

	}

	@Override
	public void deleteStudent(long id) {
		Student oneStudent = getOneStudent(id);
		studentRepository.delete(oneStudent);

	}

}
