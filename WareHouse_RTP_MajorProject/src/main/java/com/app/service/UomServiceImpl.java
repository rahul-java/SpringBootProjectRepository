package com.app.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.exception.UomNotFoundException;
import com.app.model.Uom;
import com.app.repository.UomRepository;

@Service
public class UomServiceImpl implements IUomService {

	@Autowired
	private UomRepository uomRepository;
	
	@Override
	public Integer saveUom(Uom uom) {
		
		return uomRepository.save(uom).getId();
	}

	@Override
	public List<Uom> getAllUom() {
		
		return uomRepository.findAll();
	}

	@Override
	public void updateUom(Uom uom) {
		
		uomRepository.save(uom);

	}

	@Override
	public Uom getOneUom(Integer id) {
		
		return uomRepository.findById(id).orElseThrow(()->new UomNotFoundException("Uom "+id+" does not exist !"));
	}

	@Override
	public void deleteUom(Integer id) {
		
		Uom uom = getOneUom(id);
		uomRepository.delete(uom);
	}

	@Override
	public boolean isUomTypeExist(String uomType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Object[]> getUomTypeAndCount() {
		
		return uomRepository.getUomTypeAndCount();
	}

	@Override
	public Map<Integer, String> getUomIdAndUomModel() {
	
		Map<Integer, String> map=new LinkedHashMap<>();
		List<Object[]> uomIdAndUomModel = uomRepository.getUomIdAndUomModel();
		for(Object[] obj :uomIdAndUomModel)
		{
			map.put(Integer.valueOf(obj[0].toString()), obj[1].toString());
		}
		return map;
	}

	@Override
	public Page<Uom> findAllUomByPagenation(Pageable pageable) {
		
		return this.uomRepository.findAll(pageable);
	}

}
