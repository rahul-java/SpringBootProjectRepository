package com.app.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.app.model.SaleOrderDetails;
import com.app.model.SaleOrder;

public interface ISaleOrderDetailsService {

	public Integer saveOrderDetails(SaleOrderDetails SaleOrderDetails);
	public List<SaleOrderDetails> getAllSaleOrderDetailsByOrderId(Integer orderId);
	public void updateOrderDetails(SaleOrderDetails SaleOrderDetails);
	public SaleOrderDetails getOneSaleOrderDetails(Integer id);
	public void deleteOrderDetails(Integer id);
	
	public Integer getCountSaleOrderDetailsByOrderId(Integer orderId);
	
	}
