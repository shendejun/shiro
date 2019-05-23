package com.demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entity.User;
import com.demo.service.IUserService;

@Controller
public class LoginController {

	@Resource
	IUserService userService;
	
	@RequestMapping("/toLogin")
	public String toIndex(){
		return "login";
	}
	@RequestMapping("/login.do")
	public String login(String username,String password,HttpServletRequest request){
		//当用户名称和密码都不为空的情况下，通过用户名和密码进行数据库查询校验
//		if(username != null && !"".equals(username) && password != null && !"".equals(password)){
//			User user = userService.login(username, password);
//			password = EncryptUtil.encryptMD5(password);
//			if(user != null && user.getPassword().equals(password)){
//				//登录成功
//				HttpSession session = request.getSession();
//				session.setAttribute("user",user);
//				request.setAttribute("msg","登录成功！");
//				return "success";
//			}else{
//				request.setAttribute("msg","用户名或密码错误！");
//				return "login";
//			}
//		}else{
//			request.setAttribute("msg","用户名或密码为空！");
//			return "login";
//		}
		//获取当前的主体对象
		Subject subject = SecurityUtils.getSubject();
		
		//创建用户登录验证令牌
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try {
			subject.login(token);
			boolean flag = subject.isAuthenticated();
			if(flag){ //验证通过
				//把用户对象信息放入到session中
				User user = (User)subject.getPrincipal();
				subject.getSession().setAttribute("user",user);
				return "success";
			}else{
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "login";
		}
	}
	@RequestMapping("/index.do")
	public String index(){
		return "index";
	}
	@RequestMapping("/userManager.do")
	public String userManager(){
		return "success";
	}
	@RequestMapping("/roleManager.do")
	public String roleManager(){
		return "success";
	}
	
	@RequiresPermissions(value={"function:manager"})
	@RequestMapping("/functionManager.do")
	public String functionManager() {
		return "success";
	}
	@RequestMapping("/logout.do")
	public String logout(){
		//清除session
		return "login";
	}
	@RequestMapping("/updateUser.do")
	public String updateUser(User user){
		if(user == null){
			user.setUserId(2);
			user.setUserName("张三");
			user.setPassword("123456");
		}
		int result = userService.updateUser(user);
		System.out.println(result);
		return "success";
	}
}
