package com.demo.service;

import com.demo.entity.User;

public interface IUserService {

	public User login(String username,String password);
	
	public int updateUser(User user);
}
