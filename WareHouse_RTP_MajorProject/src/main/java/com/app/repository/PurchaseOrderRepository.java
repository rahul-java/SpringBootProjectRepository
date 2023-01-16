package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

	@Modifying
	@Query("update PurchaseOrder set status=:status where id=:id")
	public void upatePurchaseOrderStatusById(Integer id,String status);
}
