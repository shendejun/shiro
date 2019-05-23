package com.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.IFunctionDao;
import com.demo.entity.Function;
import com.demo.service.IFunctionService;
@Service
public class FunctionServiceImpl implements IFunctionService {

	@Resource
	IFunctionDao functionDao;
	public List<Function> findByUserId(int userId) {
		// TODO Auto-generated method stub
		return functionDao.findFunctionByUserId(userId);
	}

}
