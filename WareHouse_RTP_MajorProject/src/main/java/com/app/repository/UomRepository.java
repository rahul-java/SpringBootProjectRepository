package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Uom;

public interface UomRepository extends JpaRepository<Uom, Integer> {
	
	@Query("select count(uomType) from Uom where uomType= :uomType")
	Integer getUomTypeCount(String uomType);
	
	@Query("select uomType , count(uomType) from Uom group by uomType")
	List<Object[]> getUomTypeAndCount();
	
	@Query("select id , uomModel from Uom")
	List<Object[]> getUomIdAndUomModel();

}
