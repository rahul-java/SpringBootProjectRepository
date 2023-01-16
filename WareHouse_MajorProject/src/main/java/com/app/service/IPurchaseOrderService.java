package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.model.PurchaseOrder;

public interface IPurchaseOrderService {

	//screen #1
	public Integer savePurchaseOrder(PurchaseOrder PurchaseOrder);
	public List<PurchaseOrder> getAllPurchaseOrder();
	public void updatePurchaseOrder(PurchaseOrder PurchaseOrder);
	public void deletePurchaseOrder(Integer id);
	public PurchaseOrder getOnePurchaseOrder(Integer id);
	
	//public Optional<PurchaseOrder> purchaseOrderDetailsById(Integer id);
	
	public void updateStatus(Integer idInteger,String status);
	
	public Page<PurchaseOrder> findPurchaseOrderByPagenation(Pageable pageable);
}
