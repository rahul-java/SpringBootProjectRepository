package com.app.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="order_tbl_warehouse")
public class OrderMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String orderMode;
	private String orderCode;
	private String orderType;
	private String orderDescription;
	
	@ElementCollection
	@CollectionTable(name = "order_accept_warehouse",joinColumns = @JoinColumn(name="order_id"))
	List<String> orderAcceptList;
	
}
