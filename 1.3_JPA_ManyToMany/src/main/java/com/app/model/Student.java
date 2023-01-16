package com.app.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="student_tbl")
public class Student {

	@Id
	private Integer sid;
	private String sname;
	private String sadd;
	
	@ManyToMany
	@JoinTable(name = "stu_course_table",
	joinColumns = @JoinColumn(name="sid_fk"),
	inverseJoinColumns = @JoinColumn(name="cid_fk"))
	private List<Course> listCourses;
}
