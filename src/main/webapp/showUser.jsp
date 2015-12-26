<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="wh" uri="/WEB-INF/tlb/tm.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示用户名</title>
</head>
<body>
   用户名：${user.name} &nbsp;&nbsp;&nbsp; 性别：${wh:sex("1")}
 
   <form action="saveUser" method="post">
             姓名：<input type="text" name="name" value=""/><br/>
             年龄：<input type="text" name="age" value=""/><br/>
             生日：<input type="date" name="birthday" value=""/><br/>
     <input type="submit" value="保存" />
   </form>
</body>
</html>