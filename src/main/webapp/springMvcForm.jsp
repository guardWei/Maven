<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringMvc的《form:form》和《form:input》标签使用实现form数据双向绑定</title>
</head>
<body>
    <!--  modelAttribute属性指定该form绑定的是哪个Model，当指定了对应的Model后就可以在form标签内部其它表单标签上通过为path指定Model属性的名称来绑定Model中的数据了，method属性指定form的提交方式如GET、POST等。 -->
    <form:form modelAttribute="user" method="post" action="userController/saveUser">
           <form:hidden path="id"/>
                姓名：<form:input path="name" />
                年龄： <form:input path="age" />
                生日： <form:input path="birthday" />         
      <input type="submit" value="保存" />
    </form:form>
</body>
</html>