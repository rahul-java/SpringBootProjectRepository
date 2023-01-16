package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_table")
public class Emp {

	@Id
	@Column(name = "emp_id_col")
	private int e_id;
	@Column(name = "emp_name_col")
	private String e_name;
	@Column(name = "emp_salary_col")
	private double e_salary;
	
}
