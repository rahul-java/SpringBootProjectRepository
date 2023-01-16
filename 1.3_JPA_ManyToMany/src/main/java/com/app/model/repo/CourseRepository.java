package com.app.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
