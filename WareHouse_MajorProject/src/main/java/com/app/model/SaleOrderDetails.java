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
@Table(name = "saleOrderDetails_tbl_warehouse")
public class SaleOrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "prod_id_fk")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "sale_order_id_fk")
	private SaleOrder saleOrder;
	
	
	
}
