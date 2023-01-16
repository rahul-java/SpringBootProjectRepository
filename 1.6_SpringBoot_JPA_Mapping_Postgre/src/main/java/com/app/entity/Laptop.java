package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Laptop_OneToOne_Mapping")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Laptop {

	@Id
	private Integer laptopId;
	private String laptopName;
	private String laptopModel;
	
	@OneToOne
	@JoinColumn(name = "student_id_fk")
	private Student student;
}
