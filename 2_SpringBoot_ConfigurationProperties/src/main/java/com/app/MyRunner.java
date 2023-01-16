package com.app;


import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JSpinner.ListEditor;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
@ConfigurationProperties(prefix = "my.runner")
public class MyRunner implements CommandLineRunner {

	private int id;
	private String name;
	private String add;
	private double sal;
	
	private List<String> list;
	private Set<String> set;
	
	private Map<String, Integer> map;
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println(this);
		//System.out.println(this.id+" : "+this.name+" : "+this.add+" : "+this.sal);
	}


}
