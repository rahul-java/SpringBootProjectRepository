package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="vendor_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {

	@Id
	@Column(name="vndr_id_col")
	private Integer id;
	@Column(name="vndr_code_col")
	private String code;
	@Column(name="vndr_location_col")
	private String location;
	
	
}
