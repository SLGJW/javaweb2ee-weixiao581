package com.ujiuye.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ujiuye.utils.MyUtils;

public class UserDao {

	/**
	 * �����Ҫ�û���Ϣ
	 * select * from user where username = ? and password = ?
	 * 
	 * ���ֻ��Ҫ�ж��Ƿ��¼�ɹ� ����Ҫ�û�����Ϣ  Ч�ʸ���
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
			//���ؽ��object  -- int     ��ת��long  ��ת��int
			l = (long)MyUtils.qr.query(sql, new ScalarHandler(),username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (int)l;
	}

}
