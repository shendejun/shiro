package com.demo.realm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyPermRealm extends AuthorizingRealm {

	/**
	 * 授权操作
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取用户名
		String username = principals.getPrimaryPrincipal().toString();
		System.out.println("username"+username);
		List<String> perms = new ArrayList<String>();
		//根据用户名从数据库中获取角色信息
		String[] roles = {"role1","role2"};
		//遍历角色，根据角色获取每一个角色具有权限信息
		for(String role : roles){
			System.out.println(role);
			//获取权限信息，并且保存到权限列表中
			perms.add("user:create");
			perms.add("user:delete");
			perms.add("user:update");
		}
		//把权限列表封装到SimpleAuthorizationInfo对象中
		SimpleAuthorizationInfo saif = new SimpleAuthorizationInfo();
		saif.addStringPermissions(perms);
		saif.addRoles(Arrays.asList(roles));
		return saif;
	}

	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();
		//根据用户获取用户密码
		String password = "admin"; //从数据库中获取到的密码的值
		//把用户名和密码封装到AuthenticationInfo的实现类对象中
		SimpleAuthenticationInfo shinfo = new SimpleAuthenticationInfo(username,password,"authRealm");
		return shinfo;
	}

}
