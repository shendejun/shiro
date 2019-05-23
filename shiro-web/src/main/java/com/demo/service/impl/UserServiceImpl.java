package com.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.IUserDao;
import com.demo.entity.User;
import com.demo.realm.AuthenRealms;
import com.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	AuthenRealms authenRealms;
	@Resource
	IUserDao userDao;
	public User login(String username, String password) {
		User user = userDao.login(username);
		return user;
	}

	
	public int updateUser(User user){
		int result = userDao.updateByPrimaryKey(user);
		//调用清除缓存的方法
		authenRealms.clearCache();
		return result;
	}
}
