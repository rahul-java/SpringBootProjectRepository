package com.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Department;

public interface DeptRepository extends JpaRepository<Department, Integer> {

	@Query("select d.id,d.dName,d.dCode,e.id,e.name,e.add from Department d inner join d.emp e")
	public List<Object[]> getAllDataDepartmentAndEmp();
}
