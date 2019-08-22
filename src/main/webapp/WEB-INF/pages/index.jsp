<%--suppress HtmlUnknownAttribute,JSUnnecessarySemicolon,ELValidationInJSP,JSUnnecessarySemicolon,JSUnusedLocalSymbols,SpellCheckingInspection,HtmlDeprecatedAttribute --%>
<%--
  Created by IntelliJ IDEA.
  User: akira
  Date: 2019/8/11
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理模板</title>
    <script src="${path}/static/js/layui/layui.js"></script>
    <script src="${path}/static/js/common/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="${path}/static/css/layui/layui.css">
    <link rel="stylesheet" href="${path}/static/css/layui/custom1.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <div class="layui-header">
        <div class="layui-logo" id="mainTitle" onclick="iframeLocation('${path}/welcome')">管理系统主页</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">顶部导航1</a></li>
            <li class="layui-nav-item"><a href="">顶部导航2</a></li>
            <li class="layui-nav-item"><a href="">顶部导航3</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">顶部导航4</a>
                <dl class="layui-nav-child">
                    <dd><a href="">列表1</a></dd>
                    <dd><a href="">列表2</a></dd>
                    <dd><a href="">列表3</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img" alt="这里是图片">
                    ${SESSION_USER.uname}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="${path}/user/exit" id="exit">退了</a></li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">后台数据展示</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" onclick="iframeLocation('${path}/user/userList1')" id="list1">
                            后台数据展示(jstl EL表达式方式)
                        </a></dd>
                        <dd><a href="javascript:;" onclick="iframeLocation('${path}/user/userList2')">
                            后台数据展示(ajax异步方式)
                        </a></dd>
                        <dd><a href="javascript:;" onclick="iframeLocation('${path}/user/userList3')">
                            后台数据展示(LayUI组件能力)
                        </a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">导航2</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">导航3</a></li>
                <li class="layui-nav-item"><a href="">导航4</a></li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe id="mainContent" src="https://www.baidu.com" style="height: 99.4%; width: 100%" frameborder="0"></iframe>
    </div>

    <div class="layui-footer" style="height: 40px">
        <!-- 底部固定区域 -->
        @akira
    </div>t
</div>

<%--suppress ES6ConvertVarToLetConst --%>
<script>

    layui.use("element", function () {
        var element = layui.elements;
    });

    $(function () {
        load(1);
        iframeLocation("${path}/welcome");
        loadClose(1);
    });

    function iframeLocation(path) {
        $("#mainContent").attr("src", path);
    }

    function load(i) {
        layui.use('layer', function () {
            layui.layer.load(i);
        });
    }

    function loadClose(i) {
        layui.use('layer', function () {
            layui.layer.close(i);
        });
    }
</script>
</body>
</html>
