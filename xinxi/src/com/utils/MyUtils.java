package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyUtils {
	//queryrunner对象
	public static QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
	
	/**
	 * string数组转字符串
	 * @param arr
	 * @return
	 */
	public static String arrayToString(String[] arr) {
		//[唱,跳,rap]   唱,跳,rap
		String strs = "";
		for (String str : arr) {
			strs += "," + str;
		}
		// ,唱,跳,rap
		return strs.substring(1);
	}
}
