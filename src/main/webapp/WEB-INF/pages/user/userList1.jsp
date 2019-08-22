<%--
  Created by IntelliJ IDEA.
  User: akira
  Date: 2019/8/11
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <script src="${path}/static/js/common/jquery-3.4.1.min.js"></script>
    <script src="${path}/static/js/layui/layui.js"></script>
    <link href="${path}/static/css/layui/layui.css" rel="stylesheet">
    <title>用户列表</title>
</head>
<body>
<div>
    <table class="layui-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>性别</th>
            <th>生日</th>
            <th>电话</th>
            <th>地址</th>
        </tr>
        </thead>
        <tbody id="userList">
                        <c:forEach items="${userList}" var="userList">
                            <tr>
                                <td>${userList.id}</td>
                                <td>${userList.uname}</td>
                                <td>${userList.userInfo.gender}</td>
                                <td>
                                    <fmt:formatDate value="${userList.userInfo.birthday}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td>${userList.userInfo.phone}</td>
                                <td>${userList.userInfo.addr}</td>
                            </tr>
                        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<%--suppress JSUnresolvedVariable --%>
<script type="text/javascript">
    var laylay;
    $(function () {
        layui.use("layer", function () {
            var layer = layui.layer;
            laylay = layer;
        });
    });

</script>
</html>