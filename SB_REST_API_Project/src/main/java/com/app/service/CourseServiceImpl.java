package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Course;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class CourseServiceImpl implements ICourseService {

	List<Course> list;
	
	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return list;
	}
	
	public CourseServiceImpl()
	{
	  list=new ArrayList<>();
	  list.add(new Course(123,"Core Java Course","This course contains basics of java"));

	  list.add(new Course(234,"Spring Boot Course","Creating REST API using Spring boot"));
	
	}

	@Override
	public Course getCourse(long courseId) {
		// TODO Auto-generated method stub
		Course course=null;
		for(Course c:list)
		{
			if(c.getId()==courseId)
			{
				course=c;
				break;
			}
		}
		return course;
	}

	@Override
	public Course addCourse(Course course) {
		
		list.add(course);
		return course;
	}

	@Override
	public Course updateCourse(Course course) {
		
		for(Course c:list)
		{
			if(c.getId()==course.getId())
			{
				c.setTitle(course.getTitle());
				c.setDescription(course.getDescription());
			}
		}
		return course;
	}

	@Override
	public void deleteCourse(long courseId) {
	
		this.list.stream().filter(c->c.getId()!=courseId).collect(Collectors.toList());
		
	}

}
