package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product_tbl_warehouse")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prodId;

	private String prodCode;
	private String prodName;
	private Double prodLength;
	private Double prodHeight;
	private Double prodWidth;
	private Double prodCost;
	private String prodCurrency;

	//Association
	
	@ManyToOne
	@JoinColumn(name = "uom_id_fk")
	private Uom uom;
	
	@ManyToOne
	@JoinColumn(name = "orderMethod_id_fk")
	private OrderMethod orderMethod;

}
