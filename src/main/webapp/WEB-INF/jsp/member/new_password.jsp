<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>晒库网-新密码</title>

</head>
<body>
	<form method="post" action="reset.shtml">
		<input type="hidden" name="loginname" value="${loginname}"/> <br/>
		新密码：<input type="password" name="password" /> <br/>
		确认密码：<input type="password" /> <br/>
		<input type="submit" value="提交" />
	</form>
</body>
</html>
