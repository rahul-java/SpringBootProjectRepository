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
@Table(name = "orderDetails_tbl_warehouse")
public class PurchaseOrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "prod_id_fk")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "purchase_order_id_fk")
	private PurchaseOrder purchaseOrder;
	
	
	
}
