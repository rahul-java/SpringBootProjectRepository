package com.app.service;

import java.util.List;

import com.app.entities.Course;

public interface ICourseService {
	
	public List<Course> getCourses();
	public Course getCourse(long courseId);
	public Course addCourse(Course course);
	public Course updateCourse(Course course);
	public void deleteCourse(long courseId);

}
