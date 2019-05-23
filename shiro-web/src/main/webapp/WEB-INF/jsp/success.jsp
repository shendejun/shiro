<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri ="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function logoutSys(){
		document.location.href="logout.do";
	}
</script>
</head>
<body>	
	成功页面！
	
	<shiro:authenticated>
		登录后显示的内容!
	</shiro:authenticated>
	
	用户名：<shiro:principal property="userName"></shiro:principal><br>
	密码：<shiro:principal property="password"></shiro:principal><br>
	email：<shiro:principal property="email"></shiro:principal>
	
	<br>
	权限判断:
	<shiro:hasPermission name="function:add">
		<a href="/addFunction.do">添加菜单</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="function:update">
		<a href="/updateFunction.do">修改菜单</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="function:delete">
		<a href="/deleteFunction.do">删除菜单</a>
	</shiro:hasPermission>


	<br>
	<input type="button" name="btn" value="退出" onclick="logoutSys()">
</body>
</html>