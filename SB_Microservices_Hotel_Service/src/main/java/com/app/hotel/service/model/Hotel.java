package com.app.hotel.service.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "micro_hotel_tbl")
public class Hotel {

	@Id
	private String id;
	private String name;
	private String location;
	private String about;
}
