package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.entity.User;

public interface IUserDao {

	int deleteByPrimaryKey(Integer userId);

	int insert(User record);

	User selectByPrimaryKey(Integer userId);

	List<User> selectAll();

	int updateByPrimaryKey(User record);

	/**
	 * 登录查询方法
	 * 
	 * @param username
	 * @return
	 * @author wzw
	 * @date 2018-08-02
	 */
	public User login(@Param("username") String username);

	public List<User> findByPage(@Param("page") Integer page, @Param("rows") Integer rows);

	public long totalCount();

	public User findById(@Param("userId") Integer userId);
}