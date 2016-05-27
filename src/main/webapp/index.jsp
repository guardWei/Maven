<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>" />
<title>显示用户名</title>
</head>
<body>
<h2>Hello World!</h2>
<a href="<%=basePath%>userController/showUser?id=1&birthday=1993-03-01">查看用户名</a></br></br>
<a href="<%=basePath%>getUserById.jsp">查询用户信息 </a><br/><br/>
<a href="<%=basePath%>fileUplode.jsp">文件上传 </a>
</body>
</htm