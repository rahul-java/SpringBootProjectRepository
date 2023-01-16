package com.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.exception.ProductNotFoundException;
import com.app.model.Product;
import com.app.model.Product;
import com.app.repository.ProductRepository;
import com.app.util.CurrencyExchange;
import com.app.util.ListToMapConverter;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ProductRepository ProductRepository;
	
	@Override
	public Integer saveProduct(Product Product) throws Exception {
		Double finalAmount = Double.valueOf(CurrencyExchange.sendHttpRequestToGetCurrencyConverter(Product.getProdCurrency(), "INR", Product.getProdCost()));
		Product.setProdCost(finalAmount);
		Product.setProdCurrency("INR");
		return ProductRepository.save(Product).getProdId();
	}

	@Override
	public List<Product> getAllProduct() {
		
		return ProductRepository.findAll();
	}

	@Override
	public void updateProduct(Product Product) throws Exception {
		
		saveProduct(Product);

	}

	@Override
	public Product getOneProduct(Integer id) {
		
		return ProductRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product "+id+" does not exist !"));
	}

	@Override
	public void deleteProduct(Integer id) {
		
		Product Product = getOneProduct(id);
		ProductRepository.delete(Product);
	}

	@Override
	public Map<Integer, String> getProductIdAndProductName() {
		
		List<Object[]> productIdAndProductName = ProductRepository.getProductIdAndProductName();
		Map<Integer,String> map = ListToMapConverter.converterListToMap(productIdAndProductName);
		return map;
	}

	@Override
	public Page<Product> findAllProductByPagenation(Pageable pageable) {
		
		return ProductRepository.findAll(pageable);
	}

	

}
