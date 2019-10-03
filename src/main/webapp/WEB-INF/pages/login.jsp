<!--suppress HtmlUnknownAttribute, SpellCheckingInspection -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统登录</title>
    <link rel="stylesheet" href="https://www.layuicdn.com/layui-v2.5.4/css/layui.css">
    <link rel="stylesheet" href="${path}/static/css/login/login.css">
</head>
<body>
<div id="container">
    <div class="admin-login-background">
        <form class="layui-form">
            <div>
                <i class="layui-icon layui-icon-username admin-icon admin-icon-username"></i>
                <label>
                    <input type="text" name="credential" id="credential"
                           placeholder="用户名/邮箱/手机号" autocomplete="off"
                           class="layui-input admin-input admin-input-username">
                </label>
            </div>
            <div>
                <i class="layui-icon layui-icon-password admin-icon admin-icon-password"></i>
                <label>
                    <input type="password" id="password"
                           placeholder="密码"
                           autocomplete="off"
                           class="layui-input admin-input">
                </label>
            </div>
            <div>
                <label>
                    <input type="text" name="verify"
                           placeholder="验证码"
                           autocomplete="off"
                           class="layui-input admin-input admin-input-verify">
                </label>
                <img class="admin-captcha" src="${path}/static/image/login/yzm.png"
                     onclick="updateVerify()" alt="验证码">
            </div>
            <div class="layui-form-item" pane="">
                <div class="layui-inline">
                    <input type="checkbox" name="like1[write]" lay-skin="primary" title="记住我" checked="">
                </div>
                <div class="layui-inline" style="float: right;margin-top: 9px;">
                    <a href="javascript:">忘记密码了吗?</a>
                </div>
            </div>
            <button type="button" class="layui-btn" id="submit">登&nbsp;&nbsp;&nbsp;录</button>
        </form>
        <div class="register" id="r" pane="">
            <div class="layui-inline" style="margin-top: 9px;">
                <p><a href="javascript:" id="reg" style="color: #2d8cf0">注册一个账号</a></p>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="https://www.layuicdn.com/layui-v2.5.4/layui.js" type="text/javascript"></script>
<!--suppress ES6ConvertVarToLetConst -->
<script>
    layui.use('form', function () {
        var form = layui.form;
        var layer = layui.layer;
        form.render();

        $("#submit").click(function () {
            var _this = this;
            $("#r").attr("style", "margin-top:6px");
            $(this).attr("disabled", "");
            $(this).attr("class", "bushi-layui-btn");
            $(this).html("<i class=\"layui-icon layui-icon-loading layui-anim layui-anim-rotate layui-anim-loop\" " +
                "style=\"color: #fff;font-size: 30px;margin-left:-10px;margin-top: -16px\"></i>");

            var credential = $("#credential").val(),
                password = $("#password").val();
            $.ajax({
                type: "POST",
                url: "${path}/user/doLogin",
                data: {
                    "password": password,
                    "credential": credential
                },
                dataType: "json",
                success: function (data) {
                    if (data.flag)
                        window.location.href = data.resource;
                    else {
                        layer.alert(data.message);
                        $("#r").attr("style", "margin-top:10px");
                        $(_this).removeAttr("disabled");
                        $(_this).attr("class", "layui-btn");
                        $(_this).html("登&nbsp;&nbsp;&nbsp;录");
                    }
                },
                error: function (e) {
                    console.error("请求错误", e);
                    $("#r").attr("style", "margin-top:10px");
                    $(_this).removeAttr("disabled");
                    $(_this).attr("class", "layui-btn");
                    $(_this).html("登&nbsp;&nbsp;&nbsp;录");
                }
            })
        })
    });

    $("#reg").click(function () {
        window.location.href = "${path}/register"
    });

    function updateVerify() {
    }
</script>
</body>
</html>
