<%--suppress HtmlUnknownAttribute,JSValidateJSDoc,JSUnresolvedVariable --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>table模块快速使用</title>
    <link rel="stylesheet" href="${path}/static/css/layui/layui.css" media="all">
</head>
<body>
<table id="userList" lay-filter="userListLayFilter" style="height: 100%"></table>
<%--lay-filter ： 事件过滤器（公用属性），主要用于事件的精确匹配，跟选择器类似--%>
<div class="layui-btn-group getSelectedRowDetail">
    <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
    <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
    <button class="layui-btn" data-type="isAll">验证是否全选</button>
</div>
<script src="${path}/static/js/layui/layui.js"></script>
<%--suppress JSUnfilteredForInLoop --%>
<script>
    var headPath = "${path}/resource/image/head/";
    layui.use('table', function () {
        var table = layui.table;
        // var layer = layui.layer;
        /**
         * 表格参数配置开始
         */
        var tableIns = table.render({
            id: 'userListData',
            even: true,
            width: "auto",
            skin: "row",
            elem: '#userList',
            method: "POST",
            toolbar: 'default',
            loading: true,
            height: 'full-58',
            url: '${path}/user/listUser3', //数据接口
            page: true,  //开启分页
            parseData: function (data) {
                console.log("表数据:", data);
                for (var i in data.resource) {
                    var b = data.resource[i].userInfo.birthday;
                    data.resource[i].userInfo.birthday = b === undefined ? "" : b;
                    switch (data.resource[i].userInfo.gender) {
                        case "0":
                            data.resource[i].userInfo.gender = "";
                            break;
                        case "1":
                            data.resource[i].userInfo.gender = "男";
                            break;
                        case "2":
                            data.resource[i].userInfo.gender = "女";
                            break;
                        case "3":
                            data.resource[i].userInfo.gender = "其他性别";
                            break;
                        default:
                            data.resource[i].userInfo.gender = "鬼知道哦";
                            break;
                    }
                    switch (data.resource[i].realNameAuth.certType) {
                        case "0":
                            data.resource[i].realNameAuth.certType = "";
                            break;
                        case "1":
                            data.resource[i].realNameAuth.certType = "大陆居民身份证";
                            break;
                        case "2":
                            data.resource[i].realNameAuth.certType = "港澳居民来往内地通行证";
                            break;
                        case "3":
                            data.resource[i].realNameAuth.certType = "台湾居民来往大陆通行证";
                            break;
                        case "4":
                            data.resource[i].realNameAuth.certType = "外国人永久居留身份证";
                            break;
                    }
                }
                return {
                    "code": data.status,
                    "msg": data.message,
                    "count": data.customProp,
                    "data": data.resource
                };
            },
            cols: [[ //表头
                {field: 'chk', type: 'checkbox', fixed: 'left'},
                {
                    field: 'id', title: 'ID', templet: '<div>' +
                        '<img alt="icon" src=' + headPath + '{{d.userInfo.headIcon}} class="layui-nav-img" style="width:25px;height:25px"> ' +
                        '{{d.id}}</div>',
                    width: 120, sort: true, fixed: 'left'
                },
                {field: 'uname', title: '用户名', width: 130},
                {field: 'role', title: '权限等级', width: 110, templet: '<div>{{d.role.role}}</div>', sort: true},
                {field: 'bindPhone', title: '绑定手机', width: 120},
                {field: 'bindEmail', title: '绑定邮箱', width: 190},
                {field: 'gender', title: '性别', templet: '<div>{{d.userInfo.gender}}</div>', sort: true, width: 80},
                {field: 'birthday', title: '生日', templet: "<div>{{d.userInfo.birthday}}</div>", sort: true, width: 130},
                {field: 'phone', title: '电话', templet: '<div>{{d.userInfo.phone}}</div>', width: 120},
                {field: 'addr', title: '城市', templet: '<div>{{d.userInfo.addr}}</div>', width: 200},
                {field: 'addr', title: '注册时间', templet: '<div>{{d.userInfo.regDate}}</div>', width: 165},
                {field: 'addr', title: '姓名', templet: '<div>{{d.realNameAuth.realName}}</div>', width: 130},
                {field: 'addr', title: '证件号', templet: '<div>{{d.realNameAuth.cid}}</div>', width: 180},
                {field: 'addr', title: '证件类型', templet: '<div>{{d.realNameAuth.certType}}</div>', width: 200},
                {
                    field: 'operation', title: '操作', fixed: 'right',
                    templet: '<div><div class="layui-btn-group">\n' +
                        '  <button type="button" class="layui-btn layui-btn-xs" onclick="showEditUser({{d.id}})">\n' +
                        '    <i class="layui-icon">&#xe642;</i>\n' +
                        '  </button>' +
                        '  <button type="button" class="layui-btn layui-btn-xs layui-btn-danger">\n' +
                        '    <i class="layui-icon">&#xe640;</i>\n' +
                        '</div></div>',
                    width:85
                }
            ]]
        });
        // console.log("表格参数: ", tableIns);

        //监听表格复选框点击
        table.on('checkbox(userListLayFilter)', function (data) {
            console.log("单行数据(这是单击该行时获取到的该行的数据):", data); //这是单击该行时获取到的该行的数据
            console.log("所有行数据", table.checkStatus('userListData'));
        });

        /**
         * layui按钮点击监听
         * @type {*|jQuery|layUI}
         */
        var $ = layui.$, active = {
            getCheckData: function () { //获取选中数据
                var checkStatus = table.checkStatus('userListData'),
                    data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            },
            getCheckLength: function () { //获取选中数目
                var checkStatus = table.checkStatus('userListData'),
                    data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个');
            },
            isAll: function () { //验证是否全选
                var checkStatus = table.checkStatus('userListData');
                layer.msg(checkStatus.isAll ? '全选' : '未全选')
            }
        };
        //点击事件参数配置
        $('.getSelectedRowDetail .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        /**
         * 工具栏监听
         */
        table.on('toolbar(userListLayFilter)', function (data) {
            // layer.alert("工具栏点击监听成功");
            console.log(data);
            switch (data.event) {
                case 'add':
                    layer.open({
                        type: 2,
                        moveOut: true,
                        scrollbar: false,
                        title: '添加一个用户',
                        closeBtn: 1,
                        area: ['80%', '100%'],
                        <%--content: '${path}/user/showAddUser'--%>
                        content: '${path}/user/showAddUser'
                    });
                    break;
                case 'update':
                    layer.alert("编辑");
                    break;
                case 'delete':
                    // layer.alert("删除");
                    var selectedData = table.checkStatus("userListData").data;
                    if (selectedData.length < 1) {
                        layer.alert("你没有选择任何数据");
                        return;
                    }
                    var ids = [];
                    for (var i = 0; i < selectedData.length; i++) {
                        ids[i] = selectedData[i].id
                    }
                    layer.confirm('确定要删除这些吗', {
                            btn: ['当然', '再考虑'] //可以无限个按钮
                        },
                        function (index) {
                            layer.close(index);
                            layer.load(1);
                            $.ajax({
                                type: "post",
                                url: "${path}/user/deleteUsers",
                                data: {"ids": ids},
                                dataType: "json",
                                success: function (data) {
                                    if (data.flag) {
                                        layer.close(layer.index);
                                        layer.alert(data.message, {
                                            yes: function () {
                                                tableIns.reload();
                                                layer.close(layer.index);
                                            }
                                        });
                                    } else {
                                        layer.close(layer.index);
                                        layer.alert(data.message);
                                    }
                                }, error: function (e) {
                                    console.log(e);
                                    layer.close(layer.index);
                                    layer.alert("请求失败了");
                                }
                            });
                        },
                        function (index) {
                            layer.close(index);
                        });
                    break;
            }
        });
    });
    function showEditUser(id) {
        layer.open({
            type: 2,
            moveOut: true,
            scrollbar: false,
            title: '编辑用户 '+id,
            closeBtn: 1,
            area: ['80%', '100%'],
            content: '${path}/user/showEditUser/'+id
        });
    }
</script>
</body>
</html>