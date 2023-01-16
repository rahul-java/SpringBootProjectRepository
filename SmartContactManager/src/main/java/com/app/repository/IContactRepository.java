package com.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Contact;
import com.app.model.User;

public interface IContactRepository extends JpaRepository<Contact, Integer>{

	/*
	@Query("from Contact as c where c.user.id =:userId")
	public List<Contact> findContactsByUser(@Param("userId")Integer id);
	*/
	
	//Pagination
	//Pageable : 1.currentPage, 2.NoOfContactPerPage(5)
	
	@Query("from Contact as c where c.user.id =:userId")
	public Page<Contact> findContactsByUser(@Param("userId")Integer id,Pageable pageable);
	
	//search contact	
	public List<Contact> findByNameContainingAndUser(String name,User user);
	
}
