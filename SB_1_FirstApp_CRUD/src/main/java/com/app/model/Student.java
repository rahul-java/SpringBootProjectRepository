package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "student_tbl_sb2_7m")
public class Student {

	@Id
	@GeneratedValue
	@Column(name = "s_id")
	private Integer id;
	@Column(name = "s_name")
	private String name;
	@Column(name = "s_add")
	private String add;
	@Column(name = "s_fee")
	private double fee;
}
