package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "contact")
@Data
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cid;
	private String name;
	private String secondName;
	private String work;
	private String email;
	private String phoneNo;
	

	private String image;
	
	@Column(length = 5000)
	private String description;
	
	@ManyToOne
	@JsonIgnore
	private User user;

}
