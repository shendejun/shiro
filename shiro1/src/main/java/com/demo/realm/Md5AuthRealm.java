package com.demo.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class Md5AuthRealm extends AuthorizingRealm {

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
		//通过token对象获取用户账号，用户自己输入的账号
		String username = token.getPrincipal().toString();
		//根据获取到的用户账号查询数据表中对应密码和盐值  加密后的密码值
		String password = "50317b958ee25a1e14449aeb95db5245";
		String salt = "abcd";
		//封装到SimpleAuthenticationInfo实现类对象中
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,ByteSource.Util.bytes(salt),"md5AuthRealm");
		return authenticationInfo;
	}

}
