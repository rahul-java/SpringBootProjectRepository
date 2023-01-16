package com.app.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_tbl_orm")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String userName;
	private String userEmail;
	private String userPassword;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roles",
	    joinColumns = @JoinColumn(name="uid"))
	private Set<String> roles;
}
