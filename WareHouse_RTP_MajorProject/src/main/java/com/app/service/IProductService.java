package com.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.model.Product;

public interface IProductService {

	public Integer saveProduct(Product Product) throws Exception;
	public List<Product> getAllProduct();
	public void updateProduct(Product Product) throws Exception;
	public Product getOneProduct(Integer id);
	public void deleteProduct(Integer id);
	
	
	public Map<Integer, String> getProductIdAndProductName();
	
	public Page<Product> findAllProductByPagenation(Pageable pageable);
}
