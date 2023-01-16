package com.app.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public interface ListToMapConverter {

	public static Map<Integer, String> converterListToMap(List<Object[] > list)
	{
		Map<Integer, String> map = list.stream().collect(Collectors.toMap(obj->Integer.valueOf(obj[0].toString()), obj->obj[1].toString()));
		return map;
	}
}
