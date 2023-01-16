package com.app.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.OrderDetailsNotFoundException;
import com.app.model.SaleOrderDetails;
import com.app.repository.SaleOrderDetailsRepository;

@Service
public class SaleOrderDetailsServiceImpl implements ISaleOrderDetailsService {

	String GET_URL;
	
	@Autowired
	private SaleOrderDetailsRepository SaleOrderDetailsRepository;
	
	@Override
	public Integer saveOrderDetails(SaleOrderDetails SaleOrderDetails) {
		
		return SaleOrderDetailsRepository.save(SaleOrderDetails).getId();
	}

	@Override
	public List<SaleOrderDetails> getAllSaleOrderDetailsByOrderId(Integer orderId) {
		
		return SaleOrderDetailsRepository.getSaleOrderDetailsByOrderId(orderId);
	}

	@Override
	public void updateOrderDetails(SaleOrderDetails SaleOrderDetails) {
		
		SaleOrderDetailsRepository.save(SaleOrderDetails);

	}

	@Override
	public SaleOrderDetails getOneSaleOrderDetails(Integer id) {
		
		return SaleOrderDetailsRepository.findById(id).orElseThrow(()->new OrderDetailsNotFoundException("OrderDetails "+id+" does not exist !"));
	}

	@Override
	public void deleteOrderDetails(Integer id) {
		
		
		SaleOrderDetailsRepository.deleteById(id);
	}

	

	@Override
	public Integer getCountSaleOrderDetailsByOrderId(Integer orderId) {
		
		return SaleOrderDetailsRepository.getCountSaleOrderDetailsByOrderId(orderId);
	}

	

}
