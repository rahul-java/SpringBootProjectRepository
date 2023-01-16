package com.app.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.exception.ShipmentTypeNotFoundException;
import com.app.model.ShipmentType;
import com.app.model.ShipmentType;
import com.app.repository.ShipmentTypeRepository;
import com.app.util.ListToMapConverter;
@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {

	@Autowired
	private ShipmentTypeRepository ShipmentTypeRepository;
	
	@Override
	public Integer saveShipmentType(ShipmentType ShipmentType) {
		
		return ShipmentTypeRepository.save(ShipmentType).getId();
	}

	@Override
	public List<ShipmentType> getAllShipmentType() {
		
		return ShipmentTypeRepository.findAll();
	}

	@Override
	public void updateShipmentType(ShipmentType ShipmentType) {
		
		ShipmentTypeRepository.save(ShipmentType);

	}

	@Override
	public ShipmentType getOneShipmentType(Integer id) {
		
		return ShipmentTypeRepository.findById(id).orElseThrow(()->new ShipmentTypeNotFoundException("ShipmentType "+id+" does not exist !"));
	}

	@Override
	public void deleteShipmentType(Integer id) {
		
		ShipmentType ShipmentType = getOneShipmentType(id);
		ShipmentTypeRepository.delete(ShipmentType);
	}

	@Override
	public Map<Integer, String> getShipmentTypeIdAndShipmentMode(String enabled) {
		
		 List<Object[]> ShipmentTypeIdAndShipmentMode = ShipmentTypeRepository.getShipmentTypeIdAndShipmentMode(enabled);
		Map<Integer,String> map = ListToMapConverter.converterListToMap(ShipmentTypeIdAndShipmentMode);
		return map;
	}

	@Override
	public Page<ShipmentType> findAllShipmentTypeByPagenation(Pageable pageable) {
		
		return ShipmentTypeRepository.findAll(pageable);
	}

}
