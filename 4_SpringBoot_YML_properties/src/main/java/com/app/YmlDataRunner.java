package com.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class YmlDataRunner implements CommandLineRunner {

	@Value("${com.student.id}")
	private int id;
	@Value("${com.student.name}")
	private String name;
	@Value("${com.student.add}")
	private String add;
	
	
	@Override
	public String toString() {
		return "YmlDataRunner [id=" + id + ", name=" + name + ", add=" + add + "]";
	}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println(this);
	}

}
