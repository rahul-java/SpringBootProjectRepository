package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Student_OneToOne_Mapping")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	private Integer id;
	private String name;
	private String address;
	
	//by using mappedBy no column will be generated in Student table for foreign key 
	//Only one foreign key will be generated in Laptop Table
	
	@OneToOne(mappedBy = "student")
	private Laptop laptop;
}
