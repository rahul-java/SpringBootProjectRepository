package com.app.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
//@Order(Ordered.LOWEST_PRECEDENCE)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("SecurityRunner.run()");
	}

}
