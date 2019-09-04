<%--suppress JSUnresolvedVariable --%>
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
    <script src="${path}/static/js/common/jquery-3.4.1.min.js"></script>
    <script src="${path}/static/js/common/notiflix-1.3.0.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${path}/static/css/layui/notiflix-1.3.0.min.css">
    <title>checking...</title>
</head>
<%--suppress HtmlDeprecatedAttribute --%>
<body></body>
<script type="text/javascript">
    Notiflix.Loading.Init({
        clickToClose: false
    });

    function random() {
        var x = 500;
        var y = 300;
        return parseInt(Math.random() * (x - y + 1) + y);
    }

    $(function () {
        Notiflix.Loading.Hourglass();
        $.ajax({
            type: "get",
            url: "${path}/check",
            dataType: "json",
            async: "false",
            success: function (data) {
                console.log(data);
                setTimeout(function () {
                    window.location.href = data.resource;
                },random());
            },
            error: function (e) {
                console.log(e);
            }
        })
    });
</script>
</html>