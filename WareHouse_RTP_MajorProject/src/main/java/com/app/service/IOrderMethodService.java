package com.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.model.OrderMethod;

public interface IOrderMethodService {

	public Integer saveOrderMethod(OrderMethod OrderMethod);
	public List<OrderMethod> getAllOrderMethod();
	public void updateOrderMethod(OrderMethod OrderMethod);
	public OrderMethod getOneOrderMethod(Integer id);
	public void deleteOrderMethod(Integer id);
	
	public Map<Integer, String> getOrderMethodIdAndOrderType();
	public Page<OrderMethod> findAllOrderMethodByPagenation(Pageable pageable);
}
