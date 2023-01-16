package com.app.runner;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix = "com.app")
public class BuldDataRunner implements CommandLineRunner {

	
	private Integer id;
	private String name;
	private String add;
	
	List<String> list;
	Map<String, Integer> map;
	
	Student std;
	
	public List<String> getList() {
		return list;
	}


	public Student getStd() {
		return std;
	}


	public void setStd(Student std) {
		this.std = std;
	}


	public void setList(List<String> list) {
		this.list = list;
	}


	public Map<String, Integer> getMap() {
		return map;
	}


	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAdd() {
		return add;
	}


	public void setAdd(String add) {
		this.add = add;
	}


	


	@Override
	public String toString() {
		return "BuldDataRunner [id=" + id + ", name=" + name + ", add=" + add + ", list=" + list + ", map=" + map
				+ ", std=" + std + "]";
	}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println(this);
	}

}
