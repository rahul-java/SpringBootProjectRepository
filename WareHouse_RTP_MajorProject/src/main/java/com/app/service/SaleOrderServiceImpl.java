package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.app.model.SaleOrder;

import com.app.repository.SaleOrderRepository;

@Service
public class SaleOrderServiceImpl implements ISaleOrderService {

	@Autowired
	private SaleOrderRepository SaleOrderRepository;
	
	@Override
	public Integer saveSaleOrder(SaleOrder SaleOrder) {
		
		return SaleOrderRepository.save(SaleOrder).getId();
	}

	@Override
	public List<SaleOrder> getAllSaleOrder() {
		
		return SaleOrderRepository.findAll();
	}

	@Override
	public void updateSaleOrder(SaleOrder SaleOrder) {
		
		SaleOrderRepository.save(SaleOrder);

	}

	@Override
	public void deleteSaleOrder(Integer id) {
		
		
		SaleOrderRepository.deleteById(id);
	}

	@Override
	public SaleOrder getOneSaleOrder(Integer id) {
		
		return SaleOrderRepository.getById(id);
	}

	@Override
	@Transactional
	public void updateStatus(Integer idInteger, String status) {
		
		SaleOrderRepository.upateSaleOrderStatusById(idInteger, status);
	}

	@Override
	public Page<SaleOrder> findSaleOrderByPagenation(Pageable pageable) {
		
		return this.SaleOrderRepository.findAll(pageable);
	}

//	@Override
//	public Optional<SaleOrder> saleOrderDetailsById(Integer id) {
//		
//		Optional<SaleOrder> saleOrderDataById = SaleOrderRepository.findById(id);
//		return saleOrderDataById;
//		
//	}
	
	

}
