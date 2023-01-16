package com.app.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class ProfileRunner implements CommandLineRunner {

	@Value("${spring.p1}")
	private String str;
	
	@Value("${my.t1.a}")
	private Integer a;
	@Value("${my.t1.b}")
	private Integer b;
	@Value("${my.t1.c}")
	private Integer c;
	
	
	@Value("${com.app.orm}")
	private String orm;
	@Value("${com.app.email}")
	private String email;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		//Syntax for run configuration argument: "--spring.profiles.active=qa" 
		System.out.println("ProfileRunner.run() : "+str);
		System.out.println("${my.t1.a}="+a);
		System.out.println("${my.t1.b}="+b);
		System.out.println("${my.t1.c}="+c);
		System.out.println("PROD-ORM : "+orm);
		System.out.println("QA-Email : "+email);
	}

}
