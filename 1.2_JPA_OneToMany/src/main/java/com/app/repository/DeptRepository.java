package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Dept;
@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {

}
