<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>	
	<h2>登录页面</h2>
	<form action="login.do" method="post" name="loginForm">
		用户名称:<input type="text" name="username" value=""/><br>
		用户密码:<input type="password" name="password" value=""/><br>
		<input type="submit" name="sub" value="登录"/>
	</form>
	${msg}
	
	${user}
</body>
</html>