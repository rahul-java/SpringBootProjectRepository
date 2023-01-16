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
@Table(name = "purchase_order_tbl_warehouse")
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String orderCode;
	
	@ManyToOne
	@JoinColumn(name = "user_id_fk")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "shipment_id_fk")
	private ShipmentType shipmentType;
	
	private String referenceNo;
	private String status;
	private String qualityCheck;
	private String description;
}
