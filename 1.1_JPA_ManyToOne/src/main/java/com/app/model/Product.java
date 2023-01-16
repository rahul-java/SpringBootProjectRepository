package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="product_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@Column(name="prod_id_col")
	private Integer id;
	@Column(name="prod_code_col")
	private String code;
	@Column(name="prod_model_col")
	private String model;
	@Column(name="prod_cost_col")
	private Double cost;


	@ManyToOne
	@JoinColumn(name="vid_fk")
	Vendor vendor;
	
	
}
