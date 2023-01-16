package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.PurchaseOrderDetails;

public interface PurchaseOrderDetailsRepository extends JpaRepository<PurchaseOrderDetails, Integer> {

	@Query("select pod from PurchaseOrderDetails pod where purchaseOrder.id= :orderId")
	public List<PurchaseOrderDetails> getPurchaseOrderDetailsByOrderId(Integer orderId);
	
	@Query("select count(pod.id) from PurchaseOrderDetails pod where purchaseOrder.id= :orderId")
	public Integer getCountPurchaseOrderDetailsByOrderId(Integer orderId);
}
