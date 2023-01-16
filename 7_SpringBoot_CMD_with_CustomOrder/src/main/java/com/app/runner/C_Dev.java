package com.app.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.app.custom.ICustomOrder;
@Component
@Order(ICustomOrder.dev)
public class C_Dev implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("C_Dev.run()");
	}

}
