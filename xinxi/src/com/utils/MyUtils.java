package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyUtils {
	//queryrunner����
	public static QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
	
	/**
	 * string����ת�ַ���
	 * @param arr
	 * @return
	 */
	public static String arrayToString(String[] arr) {
		//[��,��,rap]   ��,��,rap
		String strs = "";
		for (String str : arr) {
			strs += "," + str;
		}
		// ,��,��,rap
		return strs.substring(1);
	}
}
