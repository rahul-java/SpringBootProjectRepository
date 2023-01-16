package com.app.service;

import java.util.List;

import com.app.model.Student;

public interface IStudentService {

	public Integer saveStudent(Student student);
	public Student getStudentById(Integer id);
	public List<Student> getAllStudent();
	public void deleteStudentById(Integer id);

}
