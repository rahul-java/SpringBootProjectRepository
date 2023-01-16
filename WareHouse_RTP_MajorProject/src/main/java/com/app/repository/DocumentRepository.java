package com.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

	@Query("select id , docName from Document")
	List<Object[]> getDocumentIdAndName();

	
	//NOT WORKING
	//@Query("select id , docName from Document")
	//Page<Object[]> getDocumentIdAndNameByPagenation(Pageable pageable);
	
}
