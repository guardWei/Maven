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
<title>文件上传</title>
</head>
<body>
   <form action="fileController/fileUplode" method="post" enctype="multipart/form-data">
       <input type="file" name="ufile" />
       <input type="submit" value="上传" />
   </form>
</body>
</html>