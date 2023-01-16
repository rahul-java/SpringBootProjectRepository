package com.app.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Name field is required !")
	@Size(min = 2,max = 20,message = "Length must be in between 2 to 20")
	private String name;
	
	@NotBlank(message = "Email field is required !")
	@Size(min = 10,max = 30,message = "Length must be in between 10 to 30")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = "Password field is required !")
	//@Size(min = 5,max = 20,message = "Length must be in between 5 to 20")
	private String password;
	
	private String role;
	private boolean enabled;
	private String imageUrl;
	
	@NotBlank(message = "About field is required !")
	@Size(min = 5,message = "Length must be in between 5 to 500")
	@Column(length = 500)
	private String about;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "u_id_fk")
	private List<Contact> contacts;

}
