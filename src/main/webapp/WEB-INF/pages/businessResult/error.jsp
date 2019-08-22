<%--
  Created by IntelliJ IDEA.
  User: akira
  Date: 2019/8/21
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<html>
<head>
    <title>错了错了</title>
</head>
<body>
<h3>错了错了(つД`) &nbsp;&nbsp;${message}</h3>
<div>错误信息:${errInfo}</div>
<br>
<div>错误详情:${errDetail}</div>
</body>
</html>
