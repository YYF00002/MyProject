package com.project.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


/**
 * 集合(List,Map,Set)辅助类。
 * 
 * @date 2018年07月30日
 * @author YYF
 */
public class CollectionUtil {
	/**
	 * map根据KEY排序
	 * @param mapRes
	 * @return map
	 */
	public static Map<String, Object> sortMap(Map<String, Object> mapRes) {
		if (mapRes == null || mapRes.isEmpty()) {
			return null;
		}

		Map<String, Object> sortMap = new TreeMap<String, Object>(new Comparator<String>() {

			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		});

		sortMap.putAll(mapRes);

		return sortMap;
	}

}
