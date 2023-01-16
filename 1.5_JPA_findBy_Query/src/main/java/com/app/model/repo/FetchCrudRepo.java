package com.app.model.repo;

import org.springframework.data.repository.CrudRepository;

import com.app.model.Employee;

public interface FetchCrudRepo extends CrudRepository<Employee, Integer> {

}
