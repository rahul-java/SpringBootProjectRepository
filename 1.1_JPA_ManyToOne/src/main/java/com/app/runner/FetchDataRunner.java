package com.app.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.repository.ProductRepository;

@Component
public class FetchDataRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;
	
	public void run(String... args) throws Exception {
		
	 List<Object[]> list = productRepository.getProductAndVendorCode();
	 for( Object[] ob:list)
		 System.out.println(ob[0]+" || "+ob[1]);
	}

}
