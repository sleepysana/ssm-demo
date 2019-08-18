<%--suppress HtmlUnknownAttribute --%>
<%--
  Created by IntelliJ IDEA.
  User: akira
  Date: 2019/8/18
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>table模块快速使用</title>
    <link rel="stylesheet" href="${path}/static/css/layui.css" media="all">
</head>
<body>

<table id="demo" lay-filter="test"></table>

<script src="${path}/static/js/layui.js"></script>
<script>
    layui.use('table', function () {
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#demo',
            type: "POST"
            , height: 312
            , url: '${path}/user/listUser3' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'}
                , {field: 'uname', title: '用户名', width: 80}
                , {field: 'userInfo', title: '性别', width: 80, templet: '<div>{{d.userInfo.gender}}</div>', sort: true}
                , {field: 'userInfo', title: '电话', width: 80, templet: '<div>{{d.userInfo.phone}}</div>'}
                , {field: 'userInfo', title: '城市', width: 80, templet: '<div>{{d.userInfo.addr}}</div>'}
            ]]
        });

    });
</script>
</body>
</html>