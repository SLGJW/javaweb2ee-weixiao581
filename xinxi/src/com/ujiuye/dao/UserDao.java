package com.ujiuye.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ujiuye.utils.MyUtils;

public class UserDao {

	/**
	 * 如果需要用户信息
	 * select * from user where username = ? and password = ?
	 * 
	 * 如果只需要判断是否登录成功 不需要用户的信息  效率更高
	 * select count(*) from user where username = ? and password = ?
	 * 
	 * 
	 * @param username
	 * @param password
	 */
	public int queryCountByUsernameAndPassword(String username, String password) {
		String sql = "select count(*) from user where username = ? and password = ?";
		long l = 0;
		try {
			//返回结果object  -- int     先转成long  在转成int
			l = (long)MyUtils.qr.query(sql, new ScalarHandler(),username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (int)l;
	}

}
