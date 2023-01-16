package com.app.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.Product;
import com.app.model.Vendor;
import com.app.repository.ProductRepository;
import com.app.repository.VendorRepository;
//@Component
public class DataRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository prodRepo;
	
	@Autowired
	private VendorRepository venRepo;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Vendor vendor1=new Vendor(10,"Com","VNS");
		Vendor vendor2=new Vendor(11,"In","DLH");
		Vendor vendor3=new Vendor(12,"CTS","VNS");
		Vendor vendor4=new Vendor(13,"CMO","NDLS");

		venRepo.save(vendor1);
		venRepo.save(vendor2);
		venRepo.save(vendor3);
		venRepo.save(vendor4);
		
		Product product1=new Product(101, "ABS", "M1", 10.3, vendor1);
		Product product2=new Product(102, "ACS", "M2", 12.9, null);
		Product product3=new Product(103, "ADS", "M3", 9.5, vendor3);
		Product product4=new Product(104, "AES", "L1", 10.3, vendor1);
		Product product5=new Product(105, "AFS", "L2", 12.9, vendor2);
		Product product6=new Product(106, "AGS", "L3", 9.5, null);
		
		prodRepo.save(product1);
		prodRepo.save(product2);
		prodRepo.save(product3);
		prodRepo.save(product4);
		prodRepo.save(product5);
		prodRepo.save(product6);
	}

}
