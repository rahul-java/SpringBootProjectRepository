package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.model.SaleOrder;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer> {

	@Modifying
	@Query("update SaleOrder set status=:status where id=:id")
	public void upateSaleOrderStatusById(Integer id,String status);
}
