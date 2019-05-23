package com.demo.realm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.demo.entity.Function;
import com.demo.entity.User;
import com.demo.service.IFunctionService;
import com.demo.service.IUserService;

public class AuthenRealms extends AuthorizingRealm {

	@Resource
	IUserService userService;
	@Resource
	IFunctionService functionService;
	/**
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取当前用户信息
		User user = (User)principals.getPrimaryPrincipal();
		List<String> perms = new ArrayList<String>();
		//List<String> roles = new ArrayList<String>();
		//根据用户信息获取所属于角色
		
		//根据角色查权限
		List<Function> functions = functionService.findByUserId(user.getUserId());
		for(Function func : functions){
			perms.add(func.getFuncCode());
		}
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//把所有的权限串列表都放到SimpleAuthorizationInfo对象中
		authorizationInfo.addStringPermissions(perms); 
		//authorizationInfo.addRoles(roles);//放置所有的角色信息
		
		return authorizationInfo;
	}
	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户名名称
		String username = token.getPrincipal().toString();
		//通过用户名获取用户对象
		User user = userService.login(username, null);
		//把user对象封装的AuthenticationInfo中返回
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(user.getSalt()),"authenRealm");
		return authenticationInfo;
	}

	/**
	 * 清除缓存
	 */
	public void clearCache(){
		//获取当前主体对象
		PrincipalCollection  principal = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principal); //清除缓存
	}


}
