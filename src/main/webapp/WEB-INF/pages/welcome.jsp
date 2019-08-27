<%--suppress ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${path}/static/css/layui/layui.css">
</head>
<body>
welcome - ${SESSION_USER.uname}
<br>
<center>
    <button type="button" class="layui-btn" id="fileUpload">
        <i class="layui-icon">&#xe67c;</i>上传图片
    </button>
</center>

<script src="${path}/static/js/layui/layui.js"></script>
<script>
    layui.use('upload', function () {
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#fileUpload' //绑定元素
            , url: '/upload/' //上传接口
            , done: function (res) {
                //上传完毕回调
            }
            , error: function () {
                //请求异常回调
            }
        });
    });
</script>
</body>
</html>