package com.demo;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroTest {

	@Test
	public void loginTest() {
		// 认证操作
		// 加载资源文件
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:config/shiro.ini");
		// 创建安全管理器
		SecurityManager securityManager = factory.getInstance();
		// 把安全管理器设置到SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		// 通过安全工具类创建subject主体对象
		Subject subject = SecurityUtils.getSubject();
		// 创建用户认证用的令牌
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
		try {
			// 通过subject主体的login方法来进行认证
			subject.login(token);
			System.out.println("通过验证，可以登录系统");
		} catch (Exception e) {
			System.out.println("验证失败。。。。");
		}
		// 判断认证结果
		boolean authenticated = subject.isAuthenticated();
		System.out.println(authenticated);
		// 退出系统
		subject.logout();
	}

	@Test
	public void testRealm() {
		// 加载资源文件
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:config/shiro-ream.ini");
		// 创建安全管理器
		SecurityManager securityManager = factory.getInstance();
		// 把安全管理器设置到SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		// 通过安全工具类创建subject主体对象
		Subject subject = SecurityUtils.getSubject();
		// 创建用户认证用的令牌
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
		try {
			// 通过subject主体的login方法来进行认证
			subject.login(token);
			System.out.println("通过验证，可以登录系统");
		} catch (Exception e) {
			System.out.println("验证失败。。。。");
			e.printStackTrace();
		}
		// 判断认证结果
		boolean authenticated = subject.isAuthenticated();
		System.out.println(authenticated);
		// 退出系统
		subject.logout();
	}

	/**
	 * md5和sha的加密算法应用
	 */
	@Test
	public void testMd5() {
		// md5加密算法测试
		String password = "admin";
		String salt = "abcd";
		int times = 1;
		Md5Hash md = new Md5Hash(password, salt, times);
		System.out.println(md.toString());

		SimpleHash sh = new SimpleHash("sha", password, salt, times);
		System.out.println(sh.toString());
	}

	@Test
	public void testShiroMd5() {
		// 加载资源文件
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:config/shiro-md5.ini");
		// 创建安全管理器
		SecurityManager securityManager = factory.getInstance();
		// 把安全管理器设置到SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		// 通过安全工具类创建subject主体对象
		Subject subject = SecurityUtils.getSubject();
		// 创建用户认证用的令牌
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
		try {
			// 通过subject主体的login方法来进行认证
			subject.login(token);
			System.out.println("通过验证，可以登录系统");
		} catch (Exception e) {
			System.out.println("验证失败。。。。");
			e.printStackTrace();
		}
		// 判断认证结果
		boolean authenticated = subject.isAuthenticated();
		System.out.println(authenticated);
		// 退出系统
		subject.logout();
	}

	@Test
	public void testPerm() {
		// 加载资源文件
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:config/shiro-perm.ini");
		// 创建安全管理器
		SecurityManager securityManager = factory.getInstance();
		// 把安全管理器设置到SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		// 通过安全工具类创建subject主体对象
		Subject subject = SecurityUtils.getSubject();
		// 创建用户认证用的令牌
		UsernamePasswordToken token = new UsernamePasswordToken("test", "test");
		try {
			// 通过subject主体的login方法来进行认证
			subject.login(token);
			System.out.println("通过验证，可以登录系统");
		} catch (Exception e) {
			System.out.println("验证失败。。。。");
			e.printStackTrace();
		}
		// 判断认证结果
		boolean authenticated = subject.isAuthenticated();
		System.out.println(authenticated);

		// 判断当前主体对象是否属于指定的角色
		boolean hasRole = subject.hasRole("role1");
		System.out.println("admin的输入role1 ：" + hasRole);

		// 判断是否具有自定的权限
		boolean permitted = subject.isPermitted("test");
		System.out.println("当前主体具有创建用户的权限：" + permitted);

		// 多角色判断
		boolean flag = subject.hasAllRoles(Arrays.asList("role1", "role2", "role3"));
		System.out.println("当前主体具有role1和role2两个角色:" + flag);
		// 多权限判断
		boolean ppflag = subject.isPermittedAll("user:create", "user:delete");
		System.out.println("ppflag:" + ppflag);
	}

	@Test
	public void testPermRealm() {
		// 加载资源文件
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:config/shiro-perm-realm.ini");
		// 创建安全管理器
		SecurityManager securityManager = factory.getInstance();
		// 把安全管理器设置到SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		// 通过安全工具类创建subject主体对象
		Subject subject = SecurityUtils.getSubject();
		// 创建用户认证用的令牌
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
		try {
			// 通过subject主体的login方法来进行认证
			subject.login(token);
			System.out.println("通过验证，可以登录系统");
		} catch (Exception e) {
			System.out.println("验证失败。。。。");
			e.printStackTrace();
		}
		// 判断认证结果
		boolean authenticated = subject.isAuthenticated();
		System.out.println(authenticated);
		if (authenticated) {
			// 判断当前主体对象是否属于指定的角色
			//boolean hasRole = subject.hasRole("role1");
			//System.out.println("admin的输入role1 ：" + hasRole);

			// 判断是否具有自定的权限
			boolean permitted = subject.isPermitted("user:create");
			System.out.println("当前主体具有创建用户的权限：" + permitted);

			// 多角色判断
			boolean flag = subject.hasAllRoles(Arrays.asList("role1", "role2"));
			System.out.println("当前主体具有role1和role2两个角色:" + flag);
			// 多权限判断
			boolean ppflag = subject.isPermittedAll("user:create", "user:delete");
			System.out.println("ppflag:" + ppflag);
		}
	}
}
