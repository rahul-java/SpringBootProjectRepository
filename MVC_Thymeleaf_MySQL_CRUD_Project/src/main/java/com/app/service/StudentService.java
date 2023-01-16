package com.app.service;

import java.util.List;

import com.app.model.Student;

public interface StudentService {

	public void saveStudent(Student student);
	public List<Student> getAllStudents();
	public Student getOneStudent(long id);
	public void updateStudent(Student student);
	public void deleteStudent(long id);
}
