package com.app.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.app.model.PurchaseOrderDetails;
import com.app.model.PurchaseOrder;

public interface IPurchaseOrderDetailsService {

	public Integer saveOrderDetails(PurchaseOrderDetails PurchaseOrderDetails);
	public List<PurchaseOrderDetails> getAllPurchaseOrderDetailsByOrderId(Integer orderId);
	public void updateOrderDetails(PurchaseOrderDetails PurchaseOrderDetails);
	public PurchaseOrderDetails getOnePurchaseOrderDetails(Integer id);
	public void deleteOrderDetails(Integer id);
	
	public Integer getCountPurchaseOrderDetailsByOrderId(Integer orderId);
	
	}
