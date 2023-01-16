package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.ShipmentType;

public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Integer> {

	@Query("select id, shipmentMode from ShipmentType where enableShipment= :enabled")
	List<Object[]> getShipmentTypeIdAndShipmentMode(String enabled);

}
