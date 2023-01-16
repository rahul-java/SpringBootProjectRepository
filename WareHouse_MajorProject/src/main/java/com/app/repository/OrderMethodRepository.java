package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Integer> {

	@Query("select id, orderType from OrderMethod")
	public List<Object[]> getOrderMethodIdAndOrderType();
}
