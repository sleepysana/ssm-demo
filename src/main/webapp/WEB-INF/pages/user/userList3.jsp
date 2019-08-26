<%--suppress HtmlUnknownAttribute,JSValidateJSDoc,JSUnresolvedVariable --%>
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
    <link rel="stylesheet" href="${path}/static/css/layui/layui.css" media="all">
</head>
<body>
<table id="userList" lay-filter="userListLayFilter" style="height: 100%"></table>
<%--lay-filter ： 事件过滤器（公用属性），主要用于事件的精确匹配，跟选择器类似
--%>
<div class="layui-btn-group getSelectedRowDetail">
    <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
    <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
    <button class="layui-btn" data-type="isAll">验证是否全选</button>
</div>
<script src="${path}/static/js/layui/layui.js"></script>
<script>
    layui.use('table', function () {
        var table = layui.table;
        // var layer = layui.layer;
        /**
         * 表格参数配置开始
         */
        var tableIns = table.render({
            id: 'userListData',
            even: true,
            skin: "row",
            elem: '#userList',
            method: "POST",
            toolbar: 'default',
            loading: true,
            height: 'full-58',
            url: '${path}/user/listUser3', //数据接口
            page: true,  //开启分页
            cols: [[ //表头
                {field: 'chk', type: 'checkbox', fixed: 'left'},
                {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'},
                {field: 'uname', title: '用户名'},
                {field: 'gender', title: '性别', templet: '<div>{{d.userInfo.gender}}</div>', sort: true},
                {field: 'phone', title: '电话', templet: '<div>{{d.userInfo.phone}}</div>'},
                {field: 'addr', title: '城市', templet: '<div>{{d.userInfo.addr}}</div>'}
            ]]
        });
        console.log("表格参数: ", tableIns);

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
                        content: '${path}/user/showAddUser',
                        end:function () {
                            tableIns.reload();
                        }
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
                                url: "${path}/user/deleteUser",
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
                default:
                    layer.alert("我不知道你点的是什么鬼玩意");
                    break;
            }
        })
    });
</script>
</body>
</html>