package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select prodId , prodName from Product")
	List<Object[]> getProductIdAndProductName();

	
}
