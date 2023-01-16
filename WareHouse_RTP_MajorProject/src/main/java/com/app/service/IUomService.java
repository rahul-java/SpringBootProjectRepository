package com.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.model.Uom;

public interface IUomService {

	public Integer saveUom(Uom uom);
	public List<Uom> getAllUom();
	public void updateUom(Uom uom);
	public Uom getOneUom(Integer id);
	public void deleteUom(Integer id);
	
	public boolean isUomTypeExist(String uomType);
	public List<Object[]> getUomTypeAndCount();
	
	public Map<Integer, String> getUomIdAndUomModel();
	
	public Page<Uom> findAllUomByPagenation(Pageable pageable);
}
