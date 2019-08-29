package com.project.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GPS/北斗坐标离线转换成百度坐标
 */
public class BaiduapiOfflineUtils {
	
//	public static void main(String[] args) {
//		System.out.println(transform(121.397952, 31.180792));
//	}

	private static double pi = 3.14159265358979324;

	private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

	private static double a = 6378245.0;

	private static double ee = 0.00669342162296594323;

	/**
	 * GPS/北斗坐标转换成百度坐标
	 * @param wgLon 经度
	 * @param wgLat 纬度
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List transform(double wgLon, double wgLat) {

		double mgLat;

		double mgLon;

		List list = new ArrayList();

		 // 不在中国坐标范围
		if (outOfChina(wgLat, wgLon))

		{

			mgLat = wgLat;

			mgLon = wgLon;

			Map map = new HashMap();

			map.put("x", mgLat);

			map.put("y", mgLon);

			list.add(map);

			return list;

		}

		// GPS坐标先转为火星坐标

		double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);

		double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);

		double radLat = wgLat / 180.0 * pi;

		double magic = Math.sin(radLat);

		magic = 1 - ee * magic * magic;

		double sqrtMagic = Math.sqrt(magic);

		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);

		dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);

		mgLat = wgLat + dLat;

		mgLon = wgLon + dLon;

		// 调用火星转百度方法

		Map map = BaiduapiOfflineUtils.bd_encrypt(mgLon, mgLat);

		list.add(map);

		return list;

	}

	private static boolean outOfChina(double lat, double lon) {

		if (lon < 72.004 || lon > 137.8347)

			return true;

		if (lat < 0.8293 || lat > 55.8271)

			return true;

		return false;

	}

	private static double transformLat(double x, double y) {

		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y

				+ 0.2 * Math.sqrt(Math.abs(x));

		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;

		ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;

		ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;

		return ret;

	}

	private static double transformLon(double x, double y) {

		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1

				* Math.sqrt(Math.abs(x));

		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;

		ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;

		ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0

				* pi)) * 2.0 / 3.0;

		return ret;

	}

	/**
	 * 火星转百度
	 * @param gg_lon 经度
	 * @param gg_lat 纬度
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map bd_encrypt( double gg_lon, double gg_lat) {

		double bd_lat;

		double bd_lon;

		Map map = new HashMap();

		double x = gg_lon, y = gg_lat;

		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);

		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);

		bd_lon = z * Math.cos(theta) + 0.0065;

		bd_lat = z * Math.sin(theta) + 0.006;

		map.put("x", bd_lon);

		map.put("y", bd_lat);

		return map;

	}

}
