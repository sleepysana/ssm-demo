<%--
  Created by IntelliJ IDEA.
  User: akira
  Date: 2019/8/17
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <script src="${path}/static/js/layui.js"></script>
    <script src="${path}/static/js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="${path}/static/css/layui.css">
    <title>载入中</title>
</head>
<body>
        <i class="layui-icon">&#xe63e;</i>
</body>
<script type="text/javascript">

    $(function () {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.load(1);
            // layer.close(1);
        });
        $.ajax({
            type:"get",
            url:"${path}/init",
            dataType:"text",
            async:"false",
            success:function (data) {
                console.log(data);
                window.location.href=data;
            },
            error:function (e) {
                console.log(e);
            }
        })
    });
</script>
</html>
