<%--suppress ALL--%>
<%--
  Created by IntelliJ IDEA.
  User: akira
  Date: 2019/8/3
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>注册</title>
    <link href="${path}/static/css/login/bootstrap.min.css" rel="stylesheet">
    <link href="${path}/static/css/login/gloab.css" rel="stylesheet">
    <link href="${path}/static/css/login/register.css" rel="stylesheet">
    <script src="${path}/static/js/common/jquery-3.4.1.min.js"></script>
    <script src="${path}/static/js/common/MiniDialog-es5.min.js"></script>
    <script src="${path}/static/js/common/register.js" type="text/javascript"></script>

</head>
<body class="bgf4">
<div class="login-box f-mt10 f-pb50">
    <div class="main bgf">
        <div class="reg-box-pan display-inline">
            <div class="step">
                <ul>
                    <li class="col-xs-4 on">
                        <span class="num"><em class="f-r5"></em><i>1</i></span>
                        <span class="line_bg lbg-r"></span>
                        <p class="lbg-txt">填写账户信息</p>
                    </li>
                    <li class="col-xs-4">
                        <span class="num"><em class="f-r5"></em><i>2</i></span>
                        <span class="line_bg lbg-l"></span>
                        <span class="line_bg lbg-r"></span>
                        <p class="lbg-txt">验证账户信息</p>
                    </li>
                    <li class="col-xs-4">
                        <span class="num"><em class="f-r5"></em><i>3</i></span>
                        <span class="line_bg lbg-l"></span>
                        <p class="lbg-txt">注册成功</p>
                    </li>
                </ul>
            </div>
            <div class="reg-box" id="verifyCheck" style="margin-top:20px;">
                <div class="part1">
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>用户名：</span>
                        <div class="f-fl item-ifo">
                            <input type="text" maxlength="20" class="txt03 f-r3 required" id="username"
                                   autocomplete="off"/>
                            <span class="ie8 icon-close close hide"></span>
                            <label class="icon-sucessfill blank hide" id="uns"></label>
                            <label class="focus" id="usernametip"><span>三字以上，不带空格就行(*･ω< )</span></label>
                            <label class="focus valid"></label>
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>邮箱地址：</span>
                        <div class="f-fl item-ifo">
                            <input type="text" class="txt03 f-r3 required" id="email" autocomplete="off"/>
                            <span class="ie8 icon-close close hide"></span>
                            <label class="icon-sucessfill blank hide" id="es"></label>
                            <label class="focus" id="emailtip">这里是你要绑定的邮箱账号</label>
                            <label class="focus valid"></label>
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>密码：</span>
                        <div class="f-fl item-ifo">
                            <input type="password" id="password" maxlength="20" class="txt03 f-r3 required" tabindex="3"
                                   style="ime-mode:disabled;" onpaste="return  false" autocomplete="off"/>
                            <span class="ie8 icon-close close hide" style="right:55px"></span>
                            <span class="showpwd" data-eye="password"></span>
                            <label class="icon-sucessfill blank hide" id="ps"></label>
                            <label class="focus" id="pwtip">6-20位英文（区分大小写）、数字、字符的组合</label>
                            <label class="focus valid"></label>
                            <span class="clearfix"></span>
                            <label class="strength">
                                <span class="f-fl f-size12">密码强度：</span>
                                <b><i></i><i></i><i></i></b>
                            </label>
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>确认密码：</span>
                        <div class="f-fl item-ifo">
                            <input type="password" maxlength="20" class="txt03 f-r3 required" tabindex="4"
                                   style="ime-mode:disabled;" onpaste="return  false" autocomplete="off"
                                   id="rePassword"/>
                            <span class="ie8 icon-close close hide" style="right:55px"></span>
                            <span class="showpwd" data-eye="rePassword"></span>
                            <label class="icon-sucessfill blank hide" id="rps"></label>
                            <label class="focus" id="repasswordtip">再输一遍密码以防搞错</label>
                            <label class="focus valid"></label>
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>验证码：</span>
                        <div class="f-fl item-ifo">
                            <input type="text" maxlength="4" class="txt03 f-r3 f-fl required" tabindex="4"
                                   style="width:140px" id="randCode"/>
                            <span class="ie8 icon-close close hide"></span>
                            <label class="f-size12 c-999 f-fl f-pl10">
                                <img id="yzm" class="yzm" src="${path}/static/image/register/yzm.jpg" alt=""/>
                            </label>
                            <label class="icon-sucessfill blank hide" style="left:380px"></label>
                            <label class="focusa">看不清？<a href="javascript:;" class="c-blue"
                                                         id="changeCode">换一张</a></label>
                            <label class="focus valid" style="left:370px"></label>
                        </div>
                    </div>
                    <div class="item col-xs-12" style="height:auto">
                        <span class="intelligent-label f-fl">&nbsp;</span>
                        <p class="f-size14 required">
                            <input type="checkbox" checked/><a href="javascript:showoutc();" class="f-ml5">我已阅读并同意条款</a>
                        </p>
                        <label class="focus valid"></label>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl">&nbsp;</span>
                        <div class="f-fl item-ifo">
                            <a href="javascript:;" class="btn btn-blue f-r3" id="btn_part1">下一步</a>
                        </div>
                    </div>
                </div>
                <div class="part2" style="display:none">
                    <div class="alert alert-info" style="width:700px">一封含有验证码的邮件已发送至您的邮箱，请输入邮件中的验证码，确保您的邮箱地址真实有效。</div>
                    <div class="item col-xs-12 f-mb10" style="height:auto">
                        <span class="intelligent-label f-fl">邮箱地址：</span>
                        <div class="f-fl item-ifo c-blue" id="re_email">
                            15824450934
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>验证码：</span>
                        <div class="f-fl item-ifo">
                            <input type="text" maxlength="6" id="verifyNo" class="txt03 f-r3 f-fl required" tabindex="4"
                                   style="width:167px"/>
                            <span class="btn btn-gray f-r3 f-ml5 f-size13" id="verifyYz"
                                  style="width:97px;">发送验证码</span>
                            <span class="ie8 icon-close close hide" style="right:130px"></span>
                            <label class="icon-sucessfill blank hide"></label>
                            <label class="focus"><span>请查收邮件，并填入其中的验证码（5分钟内有效）</span></label>
                            <label class="focus valid"></label>
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl">&nbsp;</span>
                        <div class="f-fl item-ifo">
                            <a href="javascript:" class="btn btn-blue f-r3" id="btn_part2">注册</a>
                        </div>
                    </div>
                </div>
                <div class="part3" style="display:none">
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>真实姓名：</span>
                        <div class="f-fl item-ifo">
                            <input type="text" maxlength="10" class="txt03 f-r3 required"/>
                            <span class="ie8 icon-close close hide"></span>
                            <label class="icon-sucessfill blank hide"></label>
                            <label class="focus">2-10位，中文真实姓名</label>
                            <label class="focus valid"></label>
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>身份证号：</span>
                        <div class="f-fl item-ifo">
                            <input type="text" class="txt03 f-r3 required" maxlength="18"/>
                            <span class="ie8 icon-close close hide"></span>
                            <label class="icon-sucessfill blank hide"></label>
                            <label class="focus">请填写18位有效的手机号码</label>
                            <label class="focus valid"></label>
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl">&nbsp;</span>
                        <div class="f-fl item-ifo">
                            <a href="javascript:;" class="btn btn-blue f-r3" id="btn_part3">下一步</a>
                        </div>
                    </div>
                </div>
                <div class="part4 text-center" style="display:none">
                    <h3 id="congratulation"></h3>
                    <p class="c-666 f-mt30 f-mb50">页面将在 <strong id="times" class="f-size18">10</strong> 秒钟后，又给你回到 <a
                            href="${path}/register" class="c-blue">注册页面</a>去</p>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="m-sPopBg" style="z-index:998;"></div>
</body>
<%--suppress JSUnresolvedVariable, JSJQueryEfficiency --%>
<script>
    var resentEmailCanBeClicked = true;
    var p1flag = false;
    var errData;

    $(document).ready(function () {
        generateVerifyCode();
    });

    $("#changeCode").click(function () {
        generateVerifyCode();
    });

    $("#yzm").click(function () {
        generateVerifyCode();
    });

    function showErrInfo() {
        console.log("乐乐乐 ",errData);
        // console.log("安放处 ",errDetail);
    }

    function errMsgProc(data) {
        var errDetailStr = "";
        for(var i = 0;i<data.errDetail.length;i++){
            errDetailStr+=("at "+data.errDetail[i].className+"("
                +data.errDetail[i].fileName+":"+
                data.errDetail[i].lineNumber+")\n")
        }
        return {"errInfo": data.errInfo, "errDetail": errDetailStr};
    }

    function generateVerifyCode() {
        $.ajax({
            type: "POST",
            url: "${path}/generateVerifyCode",
            success: function (data) {
                console.log("验证码获取:", data);
                if (data.flag) {
                    $("#yzm").attr("src", "${path}/resource/image/temp/verify_code/" + data.resource);
                } else {
                    errData = errMsgProc(data);
                    Dialog.error("Oops!", "数据异常，请稍候再试");
                }
            },
            error: function (e) {
                console.error(e);
            }
        })
    }

    /*************************
     /******-用户名校验开始-****
     *************************/
    $("#username").keyup(function () {
        usernameCheck();
    });

    $("#username").focus(function () {
        usernameCheck();
    });

    $("#username").change(function () {
        usernameCheck();
        if ($("#username").val().length < 3) {
            uncfail();
        }
    });

    $("#username").blur(function () {
        if (usernameCheck()) {
            if ($("#username").val().length < 3) {
                uncfail();
            } else {
                $.ajax({
                    type: "POST",
                    url: "${path}/register/checkUsername",
                    data: {"username": $("#username").val()},
                    dataType: "json",
                    success: function (data) {
                        console.log("用户名检查成功回调",data);
                        if (data.flag) {
                            $("#usernametip").css("color", "#999");
                            $("#usernametip").html(" ");
                            $("#uns").removeClass();
                            $("#uns").addClass("icon-sucessfill blank");
                            p1flag = true;
                        } else {
                            $("#usernametip").css("color", "#ff2e44");
                            $("#usernametip").html(data.message);
                            $("#uns").removeClass();
                            $("#uns").addClass("icon-sucessfill blank hide");
                            p1flag = false;
                        }
                    },
                    error: function (e) {
                        console.log("用户名占用检查失败回调:",e)
                    }
                })
            }
        }else{
            console.log("用户名前台检查不通过");
        }
    });

    function usernameCheck() {
        var reg = /\r|\n|\s/;
        if ($("#username").val().length > 0) {
            if (reg.test($("#username").val())) {
                uncfail();
                p1flag = false;
                return p1flag;
            } else {
                usernameTipRestore();
                p1flag = true;
                return p1flag;
            }
        } else {
            p1flag = false;
            return p1flag;
        }
    }

    function usernameTipRestore() {
        $("#uns").removeClass("icon-sucessfill blank");
        $("#uns").addClass("icon-sucessfill blank hide");
        $("#usernametip").css("color", "#999");
        $("#usernametip").html("三字以上，不带空格就行(*･ω< )");
    }

    function uncfail() {
        $("#uns").removeClass("icon-sucessfill blank");
        $("#uns").addClass("icon-sucessfill blank hide");
        $("#usernametip").css("color", "#ff2e44");
        $("#usernametip").html("连这么简单的要求都做不到吗?");
    }
    /*************************
     /******用户名校验结束******
     *************************/

    /*************************
     /******-邮箱校验开始-******
     *************************/
    $("#email").keyup(function () {
        $("#email").val(fuckSpaces($("#email").val()));
    });
    $("#email").change(function () {
        $("#email").val(fuckSpaces($("#email").val()));
        emailTipRestore();
    });
    $("#email").blur(function () {
        $("#email").val(fuckSpaces($("#email").val()));
        if (emailCheck()) {
            var emailtip = $("#emailtip"),
                es = $("#es");
            emailtip.css("color", "#999");
            emailtip.html("");
            es.removeClass("icon-sucessfill blank");
            es.addClass("icon-sucessfill blank hide");
            $.ajax({
                type: "POST",
                url: "${path}/register/checkEmail",
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                data: {"email": $("#email").val()},
                dataType: "json",
                success: function (data) {
                    console.log("邮箱占用检查成功回调 - ", data);
                    p1flag = data.flag;
                    if (data.flag === true) {
                        es.removeClass("icon-sucessfill blank hide");
                        es.addClass("icon-sucessfill blank");
                        p1flag = true;
                    } else {
                        emailtip.css("color", "#ff2e44");
                        emailtip.html(data.message);
                    }
                },
                error: function (e) {
                    console.log("邮箱占用检查失败回调 - ", e);
                }
            });
        }
    });

    $("#email").focus(function () {
        $("#email").val(fuckSpaces($("#email").val()));
        emailTipRestore();
    });

    function emailTipRestore() {
        $("#emailtip").css("color", "#999");
        $("#emailtip").html("这里是你要绑定的邮箱账号");
        $("#es").removeClass("icon-sucessfill blank");
        $("#es").addClass("icon-sucessfill blank  hide");
    }

    function emailCheck() {
        var _ = $("#email"),
            __ = _.val(),
            ___ = /^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-z]{2,}$/,
            ____ = $("#emailtip"),
            _____ = $("#es");
        if (__.length > 0) {
            if (!___.test(__)) {
                ____.html("邮箱没写对，兄弟");
                ____.css("color", "#ff2e44");
                _____.removeClass("icon-sucessfill blank");
                _____.addClass("icon-sucessfill blank  hide");
                p1flag = false;
                return false;
            } else {
                ____.css("color", "#999");
                ____.html("");
                _____.removeClass("icon-sucessfill blank hide");
                _____.addClass("icon-sucessfill blank");
                p1flag = true;
                return true;
            }
        } else return false;
    }
    /*************************
     /******-邮箱校验结束-******
     *************************/

    $(function () {
        //第一页的确定按钮
        $("#btn_part1").click(function () {
            usernameCheck();
            emailCheck();
            if ($("#username").val().length < 3 ||
                $("#email").val().length < 3 ||
                $("#password").val().length < 3 ||
                $("#rePassword").val().length < 3
            ) {
                p1flag = false;
            }
            if ($("#rps")[0].className !== "icon-sucessfill blank") {
                p1flag = false;
            }
            if (p1flag) {
                resentEmailCanBeClicked = false;
                var o = $("#yzm")[0].src;
                var index = o.lastIndexOf("/");
                var checkPicCode = $.ajax({
                    type: "POST",
                    url: "${path}/checkPicCode",
                    contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                    cache: false,
                    async: true,
                    data: {
                        "randCode": $("#randCode").val(),
                        "fileName": $("#yzm")[0].src.substring(index + 1, o.length)
                    },
                    dataType: "json",
                    success: function (data) {
                        p1flag = data.flag;
                        if (!data.flag) {
                            Dialog.error("还是不行", data.msg);
                            generateVerifyCode();
                        }
                    },
                    error: function (e) {
                        console.error("图片验证码生成失败回调 - ", e);
                        // Dialog.error("还是不行",data.msg);
                    }
                });
                $.when(checkPicCode).done(function () {
                    submit();
                })
            } else {
                Dialog.error("不行", "请按照要求填写注册信息");
            }
        });
    });

    $("#btn_part2").click(function () {
        // console.log($("#verifyNo").val().length);
        if ($("#verifyNo").val().length === 0) {
            Dialog.warn("(￣Д ￣)┍", "请填写验证码");
        } else if ($("#verifyNo").val().length === 6) {
            $.ajax({
                type: "POST",
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                data: {
                    "username": $("#username").val(),
                    "email": $("#email").val(),
                    "password": $("#password").val(),
                    "verifyCode": $("#verifyNo").val()
                },
                url: "${path}/doReg",
                dataType: "json",
                success: function (data) {
                    console.log("注册按钮成功回调", data);
                    if (data.flag) {
                        $("#congratulation").html("恭喜" + $("#username").val() + ",你已注册成功（但是有什么用呢?）");
                        $(".part2").hide();
                        $(".part4").show();
                        $(".step li").eq(2).addClass("on");
                        countdown({
                            maxTime: 10,
                            ing: function (c) {
                                $("#times").text(c);
                            },
                            after: function () {
                                window.location.href = "${path}/register";
                            }
                        });
                    } else {
                        Dialog.error("∑(っ°Д°;)っ", data.msg);
                    }
                },
                error: function (e) {
                    console.error("注册按钮成功回调失败回调", e);
                }
            });
        } else {
            Dialog.error("ﾍ(;´Д｀ﾍ)", "验证码错误");
        }

    });

    $("#verifyYz").click(function () {
        if (resentEmailCanBeClicked) {
            $.ajax({
                type: "POST",
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                data: {"email": $("#email").val()},
                url: "${path}/verifyEmail",
                dataType: "json",
                success: function (data) {
                    console.log("重发验证码成功回调 -- ", data)
                },
                error: function (e) {
                    console.error("重发验证码失败回调 -- ", e)
                }
            });
            wait(59);
        }
    });

    $("#password").change(function () {
        var z = $("#password").val();
        var p = $("#pwtip");
        if (z.length > 0 && z.length < 6) {
            p.html("太短了，兄弟");
            p.css("color", "#ff2e44");
        }
    });

    $("#rePassword").blur(function () {
        var z = $("#password").val();
        if (z.length >= 6 && $(this).val() !== z) {
            $("#repasswordtip").css("color", "#ff2e44");
            $("#repasswordtip").html("欧亲爱的，你两次输入的密码不一样啊");
        }
    });

    function wait(time) {
        var t = time;
        var btn = $("#verifyYz");
        btn.attr("disabled", "disabled");
        resentEmailCanBeClicked = false;
        var interval = setInterval(function () {
            btn.html(t + "s后可重发");
            t--;
            if (t < 0) {
                clearInterval(interval);
                resentEmailCanBeClicked = true;
                btn.removeAttr("disabled");
                btn.html("重新发送");
            }
        }, 1000);
    }

    function fuckSpaces(bitch) {
        var fucked = '';
        for (var fucking = 0; fucking < bitch.length; fucking++) {
            if (bitch[fucking] !== " ") {
                fucked += bitch[fucking];
            }
        }
        return fucked;
    }

    function submit() {
        console.log("是不是要提交呢?",p1flag);
        if (p1flag) {
            var email = $("#email").val();
            var password1 = $("#password").val();
            var password2 = $("#rePassword").val();
            $.ajax({
                type: "POST",
                url: "${path}/verifyEmail",
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                data: {
                    "email": email,
                    "password1": password1,
                    "password2": password2
                },
                dataType: "json",
                success: function (data) {
                    console.log("邮箱验证ajax成功回调 - ", data);
                },
                error: function (e) {
                    console.error("邮箱验证ajax失败回调 - ", e);
                }
            });
            wait(59);
            $("#re_email").html(email);
            $(".part1").hide();
            $(".part2").show();
            $(".step li").eq(1).addClass("on");
        }
    }

    function showoutc() {
        $(".m-sPopBg,.m-sPopCon").show();
    }

    function closeClause() {
        $(".m-sPopBg,.m-sPopCon").hide();
    }
</script>
</html>