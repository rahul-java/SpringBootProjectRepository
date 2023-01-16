package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select id , userCode from User where userType= :userType")
	List<Object[]> getUserIdAndUserCode(String userType);

	
}
