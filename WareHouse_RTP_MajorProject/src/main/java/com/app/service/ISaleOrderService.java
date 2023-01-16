package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.model.PurchaseOrder;
import com.app.model.SaleOrder;

public interface ISaleOrderService {

	//screen #1
	public Integer saveSaleOrder(SaleOrder SaleOrder);
	public List<SaleOrder> getAllSaleOrder();
	public void updateSaleOrder(SaleOrder SaleOrder);
	public void deleteSaleOrder(Integer id);
	public SaleOrder getOneSaleOrder(Integer id);
	
	//public Optional<SaleOrder> purchaseOrderDetailsById(Integer id);
	
	public void updateStatus(Integer idInteger,String status);
	
	public Page<SaleOrder> findSaleOrderByPagenation(Pageable pageable);
}
