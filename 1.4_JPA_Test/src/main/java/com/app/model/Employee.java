package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee_table_101")
public class Employee {

	@Id
	@Column(name = "emp_id_col")
	private Integer id;
	@Column(name = "emp_name_col")
	private String name;
	@Column(name = "emp_add_col")
	private String add;
	
}
