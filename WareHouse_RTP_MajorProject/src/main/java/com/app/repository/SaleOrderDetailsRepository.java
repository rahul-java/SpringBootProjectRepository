package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.SaleOrderDetails;

public interface SaleOrderDetailsRepository extends JpaRepository<SaleOrderDetails, Integer> {

	@Query("select sod from SaleOrderDetails sod where saleOrder.id= :orderId")
	public List<SaleOrderDetails> getSaleOrderDetailsByOrderId(Integer orderId);
	
	@Query("select count(sod.id) from SaleOrderDetails sod where saleOrder.id= :orderId")
	public Integer getCountSaleOrderDetailsByOrderId(Integer orderId);
}
