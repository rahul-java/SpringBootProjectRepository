package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CourseDao;
import com.app.entities.Course;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class CourseServiceImpl implements ICourseService {

	List<Course> list;
	
	@Autowired
	private CourseDao courseDao;
	
	
	@Override
	public List<Course> getCourses() {
		
		return courseDao.findAll();
	}
	

	@Override
	public Course getCourse(long courseId) {
	
		return courseDao.findById(courseId).get();
	}

	@Override
	public Course addCourse(Course course) {
		
		return courseDao.save(course);
	}
	
	public Course updateCourse(Course course)
	{
		
		return courseDao.save(course);
	}
	
	public void deleteCourse(long courseId)
	{
		Course course = courseDao.findById(courseId).get();
	    System.err.println("Deleting..."+course);
		courseDao.delete(course);
		//courseDao.deleteById(courseId);
	}

	
}
