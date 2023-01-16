package com.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.model.ShipmentType;

public interface IShipmentTypeService {

	public Integer saveShipmentType(ShipmentType ShipmentType);
	public List<ShipmentType> getAllShipmentType();
	public void updateShipmentType(ShipmentType ShipmentType);
	public ShipmentType getOneShipmentType(Integer id);
	public void deleteShipmentType(Integer id);
	
	
	public Map<Integer, String> getShipmentTypeIdAndShipmentMode(String enabled);
	
	public Page<ShipmentType> findAllShipmentTypeByPagenation(Pageable pageable);
}
