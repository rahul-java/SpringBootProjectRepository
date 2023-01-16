package com.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Department_table_101")
public class Department {
	@Id
	@Column(name = "dept_id_col")
	private Integer id;
	@Column(name = "dept_name_col")
	private String dName;
	@Column(name = "dept_code_col")
	private String dCode;
	
	@OneToMany
	@JoinColumn(name = "dId_fk")
	List<Employee> emp;

}
