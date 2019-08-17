<%--
  Created by IntelliJ IDEA.
  User: akira
  Date: 2019/8/11
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>文件上传</title>
    <link href="${path}/static/css/layui.css" rel="stylesheet">
    <script src="${path}/static/js/layui.js" type="text/javascript"></script>
</head>
<body>
<h3>单文件上传</h3>
<form action="${path}/file/upload" method="post" enctype="multipart/form-data" >
    <input type="file" id="file" name="file" value="上传文件">
    <input type="submit" value="提交">
</form>
<br><br><br>
<h3>多文件上传</h3>
<form action="${path}/file/upload" method="post" enctype="multipart/form-data" >
    <input type="file" id="file1" name="file" value="上传文件"><br>
    <input type="file" id="file2" name="file" value="上传文件"><br>
    <input type="file" id="file3" name="file" value="上传文件"><br>
    <input type="file" id="file4" name="file" value="上传文件">
    <input type="submit" value="提交">
    <p>算了,不想写,在Controller里面把入参搞成MultipartFile[] （数组） 再遍历就行了</p>
</form>
</body>
<script type="text/javascript">
    var upload = layui.upload;
</script>
</html>