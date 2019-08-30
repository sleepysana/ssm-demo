<%--suppress HtmlFormInputWithoutLabel,HtmlUnknownAttribute--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<html>
<head>
    <title>编辑用户</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${path}/static/css/layui/layui.css">
    <style type="text/css">
        .c1 {
            color: blue;
            text-align: center;
            float: right;
            margin-right: 121px;
            margin-top: -9px;
            font-size: 13px;
        }

        .icon {
            width: 270px;
            height: 300px;
            float: left;
            margin-bottom: -280px;
            margin-left: 50px;
            display: -moz-box; /*兼容Firefox*/
            display: -webkit-box; /*兼容FSafari、Chrome*/
            -moz-box-align: center; /*兼容Firefox*/
            -webkit-box-align: center; /*兼容FSafari、Chrome */
            -moz-box-pack: center; /*兼容Firefox*/
            -webkit-box-pack: center; /*兼容FSafari、Chrome */
        }

        p {
            text-align: center;
            color: #bbb9b0;
            margin: 0 0 6px 0;
            width: 663px;
        }

        label, a, p {
            vertical-align: middle;
            overflow: hidden;
            -moz-user-select: none;
            -o-user-select: none;
            -khtml-user-select: none; /* you could also put this in a class */
            -webkit-user-select: none; /* and add the CSS class here instead */
            -ms-user-select: none;
            user-select: none; /**禁止选中文字*/
        }

        img:hover {
            cursor: pointer;
        }
    </style>
</head>
<body>
<form class="layui-form">
    <br>
    <div class="icon" name="user.UserInfo.headIcon">
        <img class="layui-nav-img"
             src="${path}/resource/image/head/${user.userInfo.headIcon}"
             id="headIcon"
             style="width: 170px;height: 170px"
             alt="此时无法显示你可爱的头像，具体为什么我也不知道">
    </div>
    <div style="margin:0 0 0 323px;">
        <p>-账户关键信息-</p>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" name="uname" id="uname" lay-verify="required|uname" autocomplete="off"
                           class="layui-input" value="${user.uname}">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-inline">
                    <select name="role.role" id="role" lay-filter="roleLayFilter">
                        <option value="1" selected>普通用户</option>
                        <option value="2">高级用户</option>
                        <option value="3">管理员</option>
                        <option value="99" disabled>开发人员</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">绑定手机</label>
                <div class="layui-input-inline">
                    <input type="tel" name="bindPhone" id="bindPhone" value="${user.bindPhone}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">绑定邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" name="bindEmail" id="bindEmail" value="${user.bindEmail}"
                           lay-verify="required|email" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item" id="changePw">
            <div class="layui-inline">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="password" name="password" lay-verify="required|password"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">密码确认</label>
                <div class="layui-input-inline">
                    <input type="password" name="rePassword" id="rePassword" lay-verify="required|rePassword"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item"><a href="javascript:return;" class="c1" id="showChangePw">修改密码</a></div>
        <p>-基本信息-</p>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <select name="userInfo.gender" id="gender" lay-filter="genderLayFilter">
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
                    <input type="text" name="userInfo.birthday"
                           value="<fmt:formatDate value="${user.userInfo.birthday}" pattern="yyyy-MM-dd"/>"
                           id="birthday"
                           autocomplete="off" class="layui-input"
                           readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系电话</label>
                <div class="layui-input-inline">
                    <input type="tel" name="userInfo.phone" id="phone" value="${user.userInfo.phone}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">联系邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" name="userInfo.email" id="email" value="${user.userInfo.email}"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="width: 947px">
        <label class="layui-form-label">联系地址</label>
        <div class="layui-input-block">
            <input type="text" name="userInfo.addr" id="addr" lay-verify="addr" value="${user.userInfo.addr}"
                   autocomplete="off" placeholder="请详细点.."
                   class="layui-input">
        </div>
    </div>
    <p style="width: 947px;">-实名信息-</p>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" id="realName"
                       value="${user.realNameAuth.realName}"
                       lay-verify="realName" autocomplete="off"
                       class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">证件号码</label>
            <div class="layui-input-inline">
                <input type="text" id="cid"
                       lay-verify="cid" value="${user.realNameAuth.cid}"
                       autocomplete="off"
                       class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">证件类型</label>
            <div class="layui-input-inline">
                <select id="certType" lay-verify="certType"
                        lay-filter="certTypeLayFilter" disabled>
                    <option selected></option>
                    <option value="1">大陆居民身份证</option>
                    <option value="2">港澳居民来往内地通行证</option>
                    <option value="3">台湾居民来往大陆通行证</option>
                    <option value="4">外国人永久居留身份证</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;bottom: 4px;width: 100%;position: fixed">
        <div class="layui-input-block" style="margin: auto">
            <button type="button" class="layui-btn" id="submit">确认修改</button>
        </div>
    </div>
</form>
</body>
<script type="text/javascript" src="${path}/static/js/common/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${path}/static/js/layui/layui.js"></script>
<%--suppress JSUnfilteredForInLoop --%>
<script>
    $(function () {
        // $("input").attr("disabled", "");
        // $("select").attr("disabled", "");
        $("#changePw").hide();
        $("#role").find("option[value='${user.role.role}']").prop("selected", true);
        $("#gender").find("option[value='${user.userInfo.gender}']").prop("selected", true);
        $("#certType").find("option[value='${user.realNameAuth.certType}']").prop("selected", true);
    });
    layui.use(['form', 'layedit', 'laydate', 'upload'], function () {
        var form = layui.form,
            layer = layui.layer,
            laydate = layui.laydate,
            upload = layui.upload;
        //日期
        laydate.render({
            elem: '#birthday',
            min: '1900-01-01',
            max: getNowFormatDate()
            // show:true
        });

        upload.render({
            elem: "#headIcon",
            url: "${path}/user/headIconUpload",
            acceptMime: 'image/*',
            choose: function () {
                layer.load(1);
            },
            done: function (data) {
                if (data.flag) {
                    $("#headIcon").attr("src", "${path}/resource/image/head/cache/" + data.resource)
                } else if (data.errInfo !== null) {
                    goToErrorPage(data);
                } else {
                    layer.alert(data.message);
                }
                layer.close(layer.index);
            }, error: function () {
                layer.alert("上传失败了呀!");
                layer.close(layer.index);
            }
        });

        /**
         * 自定义验证规则
         */
        form.verify({
            uname: function (value) {
                // unameFlag=true;
                var len = value.length;
                if (!unameFlag) {
                    return "用户名不合法啊";
                }
                if (value.indexOf(" ") !== -1) {
                    return '用户名不能有空格啊';
                }
                if (len < 3) {
                    return '用户名至少有三个字符啊';
                } else if (len > 10) {
                    return '用户名不能超10和字符啊'
                } else if (value.indexOf("@") !== -1) {
                    return '用户名不能有艾特符啊'
                }
            },
            password: [
                /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/,
                '密码至少包含 数字和英文，长度6-20<br><br>啊！！！！！！！'
            ],
            rePassword: function (value) {
                var opw = $("#password").val();
                if (value !== opw) {
                    return "两次输入的密码不可以不一样啊(╯°Д°)╯";
                }
            },
            email: [
                /^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-z]{2,}$/,
                "邮箱格式不对啊"
            ],
            addr: function (value) {
                if (value.length !== 0 && value.length < 5) {
                    return '至少得5个字符啊';
                }
            },
            rnAuth: function () {
                var check = realNameAuthCheck();
                if (!check.flag) {
                    return check.message;
                }
            },
            cid: function () {
                var check = realNameAuthCheck();
                if (!check.flag) {
                    return check.message;
                }
            },
            certType: function () {
                var check = realNameAuthCheck();
                if (!check.flag) {
                    return check.message;
                }
            }
        });
        var uname = $("#uname");
        uname.change(function () {
            var _this = this;
            $(_this).removeAttr("style");
            if ($(_this).val().length < 3) {
                $(_this).attr("style", "border-color:red;color:red");
                layer.msg('用户名太短了兄弟', {icon: 5});
                return;
            }
            if ($(_this).val().indexOf("@") !== -1) {
                $(_this).attr("style", "border-color:red;color:red");
                layer.msg('用户名不要包含"@"好不好', {icon: 5});
                return;
            }
            if ($(_this).val().indexOf(" ") !== -1) {
                $(_this).attr("style", "border-color:red;color:red");
                layer.msg('用户名不要有空格好不好嘛', {icon: 5});
                return false;
            }
        });

        uname.keydown(function () {
            $(this).removeAttr("style");
        });

        $("#submit").click(function () {
            var headIconSrc = $("#headIcon").attr("src");
            var id = ${user.id},
                uname = $("#uname").val(),
                role = $("#role").val(),
                bindPhone = $("#bindPhone").val(),
                bindEmail = $("#bindEmail").val(),
                password = $("#password").val(),
                gender = $("#gender").val(),
                birthday = $("#birthday").val(),
                phone = $("#phone").val(),
                email = $("#email").val(),
                addr = $("#addr").val(),
                headIcon = headIconSrc.substr(headIconSrc.lastIndexOf("/") + 1, headIconSrc.length),
                realName = $("#realName").val(),
                cid = $("#cid").val(),
                certType = $("#certType").val();
            layer.load(1);
            $.ajax({
                type: "post",
                url: "${path}/user/editUser",
                data: {
                    "id": id,
                    "uname": uname,
                    "bindPhone": bindPhone,
                    "bindEmail": bindEmail,
                    "password": password,
                    "role.role": role,
                    "userInfo.gender": gender,
                    "userInfo.birthday": birthday,
                    "userInfo.phone": phone,
                    "userInfo.email": email,
                    "userInfo.addr": addr,
                    "userInfo.headIcon": headIcon,
                    "realNameAuth.realName": realName,
                    "realNameAuth.cid": cid,
                    "realNameAuth.certType": certType
                },
                async: true,
                dataType: "json",
                success: function (data) {
                    console.log("编辑用户请求成功回调:", data);
                    if (!data.flag) {
                        layer.alert(data.message, {
                            end: function () {
                                layer.closeAll();
                            }
                        });
                        if (data.resource !== null) {
                            $("#" + data.resource).attr("style", "border-color:red;color:red");
                        }
                        if (data.errInfo !== null) {
                            console.log("返回带异常的数据", data);
                            goToErrorPage(data);
                        }
                    } else {
                        var index = parent.layer.getFrameIndex(window.name);
                        layer.alert(data.message, {
                            end: function () {
                                layer.closeAll();
                                parent.layui.table.reload("userListData");
                            }
                        });
                    }
                }, error: function (e) {
                    layer.alert("创建失败,因为后台没有响应", {
                        yes: function () {
                            layer.close(layer.index - 1);
                            layer.close(layer.index);
                        }
                    });

                    console.log("创建用户请求失败回调:", e);
                }
            })
        });

        $("input").keydown(function () {
            $(this).removeAttr("style");
        });

        function realNameAuthCheck() {
            var cidReg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;
            var realName = $("#realName").val();
            var cid = $("#cid").val();
            var certType = $("#certType").val();
            if (!((realName.replace(" ", "") === "" &&
                cid.replace(" ", "") === "" &&
                certType.replace(" ", "") === "")
                ||
                (realName.replace(" ", "") !== "" &&
                    cid.replace(" ", "") !== "" &&
                    certType.replace(" ", "") !== ""))
            ) {
                return {"flag": false, "message": "如需填写实名信息，就请将其完善"}
            } else if (certType === "1" && !cidReg.test(cid)) {
                return {"flag": false, "message": "身份证号码格式不对"}
            }
        }

        function getNowFormatDate() {
            var date = new Date();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            return date.getFullYear() + "-" + month + "-" + strDate
        }

        function goToErrorPage(errData) {
            //创建form表单
            var temp_form = document.createElement("form");
            temp_form.action = "${path}/error";
            //如需打开新窗口，form的target属性要设置为'_blank'
            temp_form.target = "_self";
            temp_form.method = "post";
            temp_form.style.display = "none";
            //添加参数
            for (var key in errData) {
                var opt = document.createElement("textarea");
                opt.name = key;
                opt.value = errData[key];
                temp_form.appendChild(opt);
            }
            document.body.appendChild(temp_form);
            //提交数据
            temp_form.submit();
        }
    });

    var showChgPwFlag = false;
    $("#showChangePw").click(function () {
        if (!showChgPwFlag) {
            showChgPwFlag = true;
            $(this).html("不修改密码(隐藏)");
            $("#changePw").show();
        } else {
            showChgPwFlag = false;
            $(this).html("修改密码");
            $("#changePw").hide();
        }
    })
</script>
</html>