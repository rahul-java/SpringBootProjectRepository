package com.app.user.service.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "micro_user_tbl")
public class User {

	@Id
	private String userId;
	private String name;
	private String email;
	private String about;
	
	//dont want to save in db then we use transient
	@Transient
	private List<Rating> ratings=new ArrayList<>();
}
