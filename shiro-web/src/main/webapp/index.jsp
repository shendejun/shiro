<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri ="http://shiro.apache.org/tags" prefix="shiro"%>
<html>
<body>
<h2>Hello World!</h2>
	<shiro:notAuthenticated>没有登录时显示该信息</shiro:notAuthenticated>
	<shiro:authenticated>登录后显示该信息！</shiro:authenticated>
</body>
</html>
