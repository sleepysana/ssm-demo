<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form  action="${path}/user/login" method="post">
    用户名:<input type="text" name="uname"><br>
    密码: &nbsp;<input type="password" name="password"><br>
    <input type="submit" value="提交"><br>
</form>
</body>
</html>