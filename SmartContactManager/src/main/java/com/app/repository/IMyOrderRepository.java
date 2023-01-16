package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.MyOrder;

public interface IMyOrderRepository extends JpaRepository<MyOrder, Long> {
	
	public MyOrder findByOrderId(String orderId);

}
