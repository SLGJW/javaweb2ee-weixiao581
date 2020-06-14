package com.service;

import com.dao.UserDao;

public class UserService {
	private UserDao userDao = new UserDao();
	public int loginUser(String username, String password) {
		return userDao.queryCountByUsernameAndPassword(username,password);
		
	}

}
