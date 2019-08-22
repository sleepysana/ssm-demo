<%--suppress HtmlFormInputWithoutLabel,HtmlUnknownAttribute,JSUnusedLocalSymbols --%>
<%--
  Created by IntelliJ IDEA.
  User: akira
  Date: 2019/8/21
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<html>
<head>
    <title>添加用户</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${path}/static/css/layui/layui.css">
</head>
<body>
<form class="layui-form" action="${path}/user/createUser" method="POST">
    <br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="uname" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-inline">
                <select name="role.role" lay-filter="roleLayFilter">
                    <option value="1" selected>普通用户</option>
                    <option value="2">高级用户</option>
                    <option value="3">管理员</option>
                    <option value="99" disabled>开发者(上帝)</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">绑定手机</label>
            <div class="layui-input-inline">
                <input type="tel" name="bindPhone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">绑定邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="bindEmail" lay-verify="required|email" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" id="password" name="password" lay-verify="required|password" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">密码确认</label>
            <div class="layui-input-inline">
                <input type="password" name="rePassword" lay-verify="required|rePassword"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <select name="userInfo.gender" lay-filter="genderLayFilter">
                    <option value="0" selected></option>
                    <option value="1">男</option>
                    <option value="2">女</option>
                    <option value="3">其他</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">生日</label>
            <div class="layui-input-inline">
                <input type="text" name="userInfo.birthday" id="birthday" placeholder="格式:yyyy-MM-dd"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">联系电话</label>
            <div class="layui-input-inline">
                <input type="tel" name="userInfo.phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">联系邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="userInfo.email" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="width: 80%;">
        <label class="layui-form-label">联系地址</label>
        <div class="layui-input-block">
            <input type="text" name="userInfo.addr" lay-verify="addr" autocomplete="off" placeholder="请详细点.."
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;margin: 8% auto">
        <div class="layui-input-block" style="margin: auto">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>  <!--${path}/user/createUser-->
</body>
<script type="text/javascript" src="${path}/static/js/common/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${path}/static/js/layui/layui.js"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form,
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#birthday'
        });

        //自定义验证规则
        form.verify({
            addr: function (value) {
                if (value.length !== 0 && value.length < 5) {
                    return '至少得5个字符啊';
                }
            },
            password: [
                /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/,
                '密码至少包含 数字和英文，长度6-20啊'
            ],
            rePassword: function (value) {
                var opw = $("#password").val();
                console.log(value + "和" + opw + "一样咩? ", value === opw);
                if (value !== opw) {
                    return "两次输入的密码不可以不一样啊(╯°Д°)╯"
                }
            },
            email: [
                /^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-z]{2,}$/,
                "邮箱格式不对啊"
            ]
        });
    });
</script>
</html>
