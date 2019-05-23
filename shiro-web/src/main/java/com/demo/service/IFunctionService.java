package com.demo.service;

import java.util.List;

import com.demo.entity.Function;

public interface IFunctionService {

	
	public List<Function> findByUserId(int userId);
}
