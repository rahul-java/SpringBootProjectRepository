package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.exception.PurchaseOrderNotFoundException;
import com.app.model.PurchaseOrder;

import com.app.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository PurchaseOrderRepository;
	
	@Override
	public Integer savePurchaseOrder(PurchaseOrder PurchaseOrder) {
		
		return PurchaseOrderRepository.save(PurchaseOrder).getId();
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrder() {
		
		return PurchaseOrderRepository.findAll();
	}

	@Override
	public void updatePurchaseOrder(PurchaseOrder PurchaseOrder) {
		
		PurchaseOrderRepository.save(PurchaseOrder);

	}

	@Override
	public void deletePurchaseOrder(Integer id) {
		
		
		PurchaseOrderRepository.deleteById(id);
	}

	@Override
	public PurchaseOrder getOnePurchaseOrder(Integer id) {
		
		return PurchaseOrderRepository.getById(id);
	}

	@Override
	@Transactional
	public void updateStatus(Integer idInteger, String status) {
		
		PurchaseOrderRepository.upatePurchaseOrderStatusById(idInteger, status);
	}

	@Override
	public Page<PurchaseOrder> findPurchaseOrderByPagenation(Pageable pageable) {
		
		return this.PurchaseOrderRepository.findAll(pageable);
	}

//	@Override
//	public Optional<PurchaseOrder> purchaseOrderDetailsById(Integer id) {
//		
//		Optional<PurchaseOrder> purchaseOrderDataById = PurchaseOrderRepository.findById(id);
//		return purchaseOrderDataById;
//		
//	}
	
	

}
