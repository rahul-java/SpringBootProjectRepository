package com.app.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.exception.OrderMethodNotFoundException;
import com.app.model.OrderMethod;
import com.app.model.OrderMethod;
import com.app.repository.OrderMethodRepository;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {

	@Autowired
	private OrderMethodRepository OrderMethodRepository;
	
	@Override
	public Integer saveOrderMethod(OrderMethod OrderMethod) {
		
		return OrderMethodRepository.save(OrderMethod).getId();
	}

	@Override
	public List<OrderMethod> getAllOrderMethod() {
		
		return OrderMethodRepository.findAll();
	}

	@Override
	public void updateOrderMethod(OrderMethod OrderMethod) {
		
		OrderMethodRepository.save(OrderMethod);

	}

	@Override
	public OrderMethod getOneOrderMethod(Integer id) {
		
		return OrderMethodRepository.findById(id).orElseThrow(()->new OrderMethodNotFoundException("OrderMethod "+id+" does not exist !"));
	}

	@Override
	public void deleteOrderMethod(Integer id) {
		
		OrderMethod OrderMethod = getOneOrderMethod(id);
		OrderMethodRepository.delete(OrderMethod);
	}

	@Override
	public Map<Integer, String> getOrderMethodIdAndOrderType() {
		
		Map<Integer, String> map=new LinkedHashMap<>();
		 List<Object[]> orderMethodIdAndOrderType = OrderMethodRepository.getOrderMethodIdAndOrderType();
		for(Object[] obj :orderMethodIdAndOrderType)
		{
			map.put(Integer.valueOf(obj[0].toString()), obj[1].toString());
		}
		return map;
	}

	@Override
	public Page<OrderMethod> findAllOrderMethodByPagenation(Pageable pageable) {
	
		return this.OrderMethodRepository.findAll(pageable);
	}

	


}
