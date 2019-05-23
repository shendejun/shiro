package com.demo.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm{

	/**
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//通过token令牌获取用户账号信息
		String username = (String)token.getPrincipal();
		System.out.println(username);
		//根据用户名从数据库中查询用户密码
		String password = "admin"; //省略数据库查询操作
		if(password == null || "".equals(password)){
			return null;
		}
		//把用户名，密码，域简写类名封装到SimpleAuthenticationInfo对象中
		SimpleAuthenticationInfo sati = new SimpleAuthenticationInfo(username, password, "myRealm");
		
		return sati;
	}

}
