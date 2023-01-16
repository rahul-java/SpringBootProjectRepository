package com.app.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.OrderDetailsNotFoundException;
import com.app.model.PurchaseOrderDetails;
import com.app.repository.PurchaseOrderDetailsRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;

@Service
public class PurchaseOrderDetailsServiceImpl implements IPurchaseOrderDetailsService {

	String GET_URL;
	
	@Autowired
	private PurchaseOrderDetailsRepository PurchaseOrderDetailsRepository;
	
	@Override
	public Integer saveOrderDetails(PurchaseOrderDetails PurchaseOrderDetails) {
		
		return PurchaseOrderDetailsRepository.save(PurchaseOrderDetails).getId();
	}

	@Override
	public List<PurchaseOrderDetails> getAllPurchaseOrderDetailsByOrderId(Integer orderId) {
		
		return PurchaseOrderDetailsRepository.getPurchaseOrderDetailsByOrderId(orderId);
	}

	@Override
	public void updateOrderDetails(PurchaseOrderDetails PurchaseOrderDetails) {
		
		PurchaseOrderDetailsRepository.save(PurchaseOrderDetails);

	}

	@Override
	public PurchaseOrderDetails getOnePurchaseOrderDetails(Integer id) {
		
		return PurchaseOrderDetailsRepository.findById(id).orElseThrow(()->new OrderDetailsNotFoundException("OrderDetails "+id+" does not exist !"));
	}

	@Override
	public void deleteOrderDetails(Integer id) {
		
		
		PurchaseOrderDetailsRepository.deleteById(id);
	}

	

	@Override
	public Integer getCountPurchaseOrderDetailsByOrderId(Integer orderId) {
		
		return PurchaseOrderDetailsRepository.getCountPurchaseOrderDetailsByOrderId(orderId);
	}

	

}
