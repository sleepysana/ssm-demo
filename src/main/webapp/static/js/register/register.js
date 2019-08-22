var rePasswordTip = false;
/**
 * 功能说明:        输入验证
 * @author:        luott <tiny99@yeah.net>
 * @time:        2019-7-25 16:15:30
 * @version:        V1.1.0
 * @使用方法:
 * <input class="required" type="text" data-valid="isNonEmpty||isEmail" data-error="email不能为空||邮箱格式不正确" id="" />
 * 1、需要验证的元素都加上【required】样式
 * 2、@data-valid        验证规则，验证多个规则中间用【||】隔开，更多验证规则，看rules和rule，后面遇到可继续增加
 * 3、@data-error        规则对应的提示信息，一一对应
 *
 * @js调用方法：
 * verifyCheck({
 *  	formId:'verifyCheck',		<验证formId内class为required的元素
 *	onBlur:null,				<被验证元素失去焦点的回调函数>
 *	onFocus:null,				<被验证元素获得焦点的回调函数>
 *	onChange: null,				<被验证元值改变的回调函数>
 *	successTip: true,			<验证通过是否提示>
 *	resultTips:null,			<显示提示的方法，参数obj[当前元素],isRight[是否正确提示],value[提示信息]>
 *	clearTips:null,				<清除提示的方法，参数obj[当前元素]>
 *	code:true					<是否需要手机号码输入控制验证码及点击验证码倒计时,目前固定手机号码ID为phone,验证码两个标签id分别为time_box，resend,填写验证框id为code>
 *	phone:true					<改变手机号时是否控制验证码>
 * })
 * $("#submit-botton").click(function(){		<点击提交按钮时验证>
 *  	if(!common.verify.btnClick()) return false;
 * })
 *
 * 详细代码请看register.src.js
 */
(function ($) {
    // noinspection JSUnusedLocalSymbols
    var h, timerC = 60,
        opt;
    var j = function (a) {
        a = $.extend(require.defaults, a || {});
        opt = a;
        return (new require())._init(a)
    };

    // noinspection JSUnusedLocalSymbols
    function require(f) {
        var g = {
            phone: /^1(3\d|5[0-35-9]|8[025-9]|47)\d{8}$/,
            company: /^[一-龥a-zA-Z][一-龥a-zA-Z0-9\s-,-.]*$/,
            uname: /^[一-龥a-zA-Z][一-龥a-zA-Z0-9_]*$/,
            zh: /^[一-龥]+$/,
            card: /^((1[1-5])|(2[1-3])|(3[1-7])|(4[1-6])|(5[0-4])|(6[1-5])|71|(8[12])|91)\d{4}(((((19|20)((\d{2}(0[13-9]|1[012])(0[1-9]|[12]\d|30))|(\d{2}(0[13578]|1[02])31)|(\d{2}02(0[1-9]|1\d|2[0-8]))|(([13579][26]|[2468][048]|0[48])0229)))|20000229)\d{3}(\d|X|x))|(((\d{2}(0[13-9]|1[012])(0[1-9]|[12]\d|30))|(\d{2}(0[13578]|1[02])31)|(\d{2}02(0[1-9]|1\d|2[0-8]))|(([13579][26]|[2468][048]|0[48])0229))\d{3}))$/,
            int: /^[0-9]*$/,
            s: ''
        };
        // noinspection JSUnusedGlobalSymbols
        this.rules = {
            isNonEmpty: function (a, b) {
                b = b || " ";
                if (!a.length) return b
            },
            minLength: function (a, b, c) {
                c = c || " ";
                if (a.length < b) return c
            },
            maxLength: function (a, b, c) {
                c = c || " ";
                if (a.length > b) return c
            },
            isRepeat: function (a, b, c) {
                c = c || " ";
                if (a !== $("#" + b).val()) return c
            },
            between: function (a, b, c) {
                c = c || " ";
                var d = parseInt(b.split('-')[0]);
                var e = parseInt(b.split('-')[1]);
                if (a.length < d || a.length > e) return c
            },
            level: function (a, b, c) {
                c = c || " ";
                var r = j.pwdStrong(a);
                if (b > 4) b = 3;
                if (r < b) return c
            },
            isPhone: function (a, b) {
                b = b || " ";
                if (!g.phone.test(a)) return b
            },
            isCompany: function (a, b) {
                b = b || " ";
                if (!g.company.test(a)) return b
            },
            isInt: function (a, b) {
                b = b || " ";
                if (!g.int.test(a)) return b
            },
            isUname: function (a, b) {
                b = b || " ";
                if (!g.uname.test(a)) return b
            },
            isZh: function (a, b) {
                b = b || " ";
                if (!g.zh.test(a)) return b
            },
            isCard: function (a, b) {
                b = b || " ";
                if (!g.card.test(a)) return b
            },
            isChecked: function (c, d, e) {
                d = d || " ";
                var a = $(e).find('input:checked').length,
                    b = $(e).find('.on').length;
                if (!a && !b) return d
            }
        }
    }

    require.prototype = {
        _init: function (b) {
            // noinspection JSUnusedGlobalSymbols
            this.config = b;
            // noinspection JSUnusedGlobalSymbols
            this.getInputs = $('#' + b.formId).find('.required:visible');
            // noinspection JSUnusedLocalSymbols
            var c = false;
            var d = this;
            if (b.code) {
                // $("#verifyYz").click(function () {
                //     $("#time_box").text("60 s后可重发");
                //     d._sendVerify()
                // })
            }
            // noinspection JSJQueryEfficiency,JSUnusedLocalSymbols
            $('body').on({
                blur: function (a) {
                    d.formValidator($(this));
                    if (b.phone && $(this).attr("id") === "phone") d._change($(this));
                    b.onBlur ? b.onBlur($(this)) : ''
                },
                focus: function (a) {
                    b.onFocus ? b.onFocus($(this)) : $(this).parent().find("label.focus").not(".valid").removeClass("hide").siblings(".valid").addClass("hide") && $(this).parent().find(".blank").addClass("hide") && $(this).parent().find(".close").addClass("hide")
                },
                keyup: function (a) {
                    if (b.phone && $(this).attr("id") === "phone") d._change($(this))
                },
                change: function (a) {
                    b.onChange ? b.onChange($(this)) : ''
                }
            }, "#" + b.formId + " .required:visible");
            // noinspection JSJQueryEfficiency
            $('body').on("click", ".close", function () {
                var p = $(this).parent(),
                    input = p.find("input");
                input.val("").focus()
            })
        },
        formValidator: function (a) {
            var b = a.attr('data-valid');
            if (b === undefined) return false;
            var c = b.split('||');
            var d = a.attr('data-error');
            if (d === undefined) d = "";
            var e = d.split("||");
            var f = [];
            for (var i = 0; i < c.length; i++) {
                f.push({
                    strategy: c[i],
                    errorMsg: e[i]
                })
            }
            return this._add(a, f)
        },
        _add: function (a, b) {
            var d = this;
            // noinspection JSUnusedAssignment
            for (var i = 0, rule; rule === b[i++];) {
                // noinspection JSUnusedAssignment
                var e = rule.strategy.split(':');
                // noinspection JSUnusedAssignment
                var f = rule.errorMsg;
                var g = e.shift();
                e.unshift(a.val());
                e.push(f);
                e.push(a);
                var c = d.rules[g].apply(a, e);
                if (c) {
                    opt.resultTips ? opt.resultTips(a, false, c) : j._resultTips(a, false, c);
                    return false
                }
            }
            opt.successTip ? (opt.resultTips ? opt.resultTips(a, true) : j._resultTips(a, true)) : j._clearTips(a);
            return true
        },
        // _sendVerify: function () {
        //     var a = this;
        //     $("#verifyYz").text("发送验证码").hide();
        //     $("#time_box").text("10 s后可重发").show();
        //     if (timerC === 0) {
        //         clearTimeout(h);
        //         timerC = 60;
        //         var b = /^1([^01269])\d{9}$/;
        //         if (!b.test($("#phone").val())) {
        //             $("#time_box").text("发送验证码")
        //         } else {
        //             $("#verifyYz").show();
        //             $("#time_box").hide()
        //         }
        //         return
        //     }
        //     $("#time_box").text(timerC + " s后可重发");
        //     timerC--;
        //     h = setTimeout(function () {
        //         a._sendVerify()
        //     }, 1000)
        // },
        _change: function (a) {
            // noinspection JSUnusedLocalSymbols
            var b = this;
            // if (a.val().length != 11) {
            //     $("#verifyYz").hide();
            //     $("#time_box").show();
            //     if (timerC === 60) $("#time_box").text("发送验证码");
            //     $("#verifyNo").val("");
            //     this.config.clearTips ? this.config.clearTips($("#verifyNo")) : j._clearTips($("#verifyNo"));
            //     return
            // }
            var c = /^1([^01269])\d{9}$/;
            if (!c.test(a.val())) return false;
            // if (timerC === 60) {
            //     $("#verifyYz").show();
            //     $("#time_box").hide()
            // } else {
            //     $("#verifyYz").hide();
            //     $("#time_box").show()
            // }
        }
    };
    // noinspection DuplicatedCode
    j._click = function (c) {
        c = c || opt.formId;
        // noinspection JSUnusedLocalSymbols
        var d = $("#" + c).find('.required:visible'),
            self = this,
            result = true,
            t = new require(),
            r = [];
        $.each(d, function (a, b) {
            result = t.formValidator($(b));
            if (result) r.push(result)
        });
        if (d.length !== r.length) result = false;
        return result
    };
    j._clearTips = function (a) {
        a.parent().find(".blank").addClass("hide");
        a.parent().find(".valid").addClass("hide");
        a.removeClass("v_error")
    };
    j._resultTips = function (a, b, c) {
        a.parent().find("label.focus").not(".valid").addClass("hide").siblings(".focus").removeClass("hide");
        a.parent().find(".close").addClass("hide");
        a.removeClass("v_error");
        c = c || "";
        if (c.length > 21) c = "<span>" + c + "</span>";
        var o = a.parent().find("label.valid");
        if (!b) {
            o.addClass("error");
            a.addClass("v_error");
            if ($.trim(a.val()).length > 0) a.parent().find(".close").removeClass("hide")
        } else {
            a.parent().find(".blank").removeClass("hide")
        }
        o.text("").append(c)
    };
    j.textChineseLength = function (a) {
        var b = /[一-龥]|[、-。]|[：-？]|[《-』]|[【-〕]|[–-”]|[！-．]|[〈-〉]|[…]|[￥]/g;

        if (b.test(a)) return a.match(b).length;
        else return 0
    };
    j.pwdStrong = function (a) {
        var b = 0;
        if (a.match(/[a-z]/g)) {
            b++
        }
        if (a.match(/[A-Z]/g) || a.match(/[0-9]/g)) {
            b++
        }
        if (a.match(/(.[^a-z0-9A-Z])/g) && a.length > 10) {
            b++
        }
        if (b > 4) {
            b = 4
        }
        if (b === 0) return false;
        return b
    };
    require.defaults = {
        formId: 'verifyCheck',
        onBlur: null,
        onFocus: null,
        onChange: null,
        successTip: true,
        resultTips: null,
        clearTips: null,
        code: true,
        phone: false
    };
    window.verifyCheck = $.verifyCheck = j
})(jQuery);
(function ($) {
    // noinspection JSUnusedLocalSymbols
    var f;
    var g = function () {
        return (new require())._init()
    };

    function require(a) {
    }

    require.prototype = {
        _init: function () {
            var b = this;
            // noinspection JSUnusedLocalSymbols
            $('body').on({
                click: function (a) {
                    b._click($(this))
                }
            }, ".showpwd:visible")
        },
        _click: function (a) {
            var c = a.attr('data-eye');
            if (c === undefined) return false;
            var d = $("#" + c),
                cls = !d.attr("class") ? "" : d.attr("class"),
                value = !d.val() ? "" : d.val(),
                type = d.attr("type") === "password" ? "text" : "password",
                b = d.parent().find("b.placeTextB"),
                isB = b.length !== 0;
            var s = d.attr("name") ? " name='" + d.attr("name") + "'" : "";
            s += d.attr("data-valid") ? " data-valid='" + d.attr("data-valid") + "'" : "";
            s += d.attr("data-error") ? " data-error='" + d.attr("data-error") + "'" : "";
            s += d.attr("placeholder") ? " placeholder='" + d.attr("placeholder") + "'" : "";
            var e = '<input readonly type="' + type + '" class="' + cls + '" value="' + value + '" id="' + c + '"' + s + ' />';
            if (type === "text") {
                if (isB) b.hide();
                d.parent().find(".icon-close.close").addClass("hide");
                d.removeAttr("id").hide();
                d.after(e);
                a.addClass("hidepwd")
            } else {
                d.prev("input").attr("id", c).val(value).show();
                if (isB && $.trim(value) === "") {
                    d.prev("input").hide();
                    b.show()
                }
                d.remove();
                a.removeClass("hidepwd")
            }
            $('body').on("click", "#" + c, function () {
                $(this).parent().find(".hidepwd").click();
                if (isB && $.trim($(this).val()) === "") {
                    d.show();
                    b.hide()
                }
                d.focus()
            })
        }
    };
    require.defaults = {};
    window.togglePwd = $.togglePwd = g
})(jQuery);
(function ($) {
    var b, timerC, opt;
    var d = function (a) {
        a = $.extend(require.defaults, a || {});
        opt = a;
        d._clear();
        return (new require())._init()
    };

    function require(a) {
    }

    require.prototype = {
        _init: function () {
            timerC = opt.maxTime;
            this._sendVerify()
        },
        _sendVerify: function () {
            var a = this;
            if (timerC === 0) {
                d._clear();
                opt.after();
                timerC = opt.maxTime;
                return
            }
            timerC--;
            opt.ing(timerC);
            b = setTimeout(function () {
                a._sendVerify()
            }, 1000)
        }
    };
    d._clear = function () {
        clearTimeout(b)
    };
    require.defaults = {
        maxTime: 60,
        minTime: 0,
        ing: function (c) {
        },
        after: function () {
        }
    };
    window.countdown = $.countdown = d
})(jQuery);
$(function () {
    togglePwd();
    verifyCheck();
    var body = $('body');
    body.on("keyup", "#password", function () {
        m(this);
        var t = $(this).val(),
            o = $(this).parent().find(".strength"),
            p = $("#pwtip"),
            h = $(this),
            x = "",
            l;
        p.html("6-20位英文（区分大小写）、数字、字符的组合");
        p.css("color", "#999");
        for (var z = 0; z < t.length; z++) {
            if (t[z] !== " ") {
                x += t[z];
            }
        }
        h.val(x);
        l = verifyCheck.pwdStrong(x);
        if (x.length >= 6) {
            rePasswordTip = true;
            o.show();
            o.find("b i").removeClass("on");
            for (var i = 0; i < l; i++) {
                o.find("b i").eq(i).addClass("on");
                // l=0;
                switch (l) {
                    case 0:
                        fuckOthersStrength(o, i + 1, "不可用");
                        o.find("b i").eq(i).css("background", "#dbdbdb");
                        break;
                    case 1:
                        fuckOthersStrength(o, i, "弱");
                        o.find("b i").eq(i).css("background", "#ff6160");
                        break;
                    case 2:
                        fuckOthersStrength(o, i, "一般");
                        o.find("b i").eq(i).css("background", "#cfc331");
                        break;
                    case 3:
                        fuckOthersStrength(o, i, "强");
                        o.find("b i").eq(i).css("background", "#21bc4c");
                        break;
                    default:
                        o.find("b i").eq(i).css("background", "#dbdbdb");
                }
            }
        } else {
            rePasswordTip = false;
            o.hide();
        }
    });

    body.on("change", "#password", function () {
        var t = $(this).val(),
            h = $(this),
            x = "";
        for (var z = 0; z < t.length; z++) {
            if (t[z] !== " ") {
                x += t[z];
            }
        }
        h.val(x);
        rePasswordTip = x.length >= 6;
        m(this);
    });

    body.on("keyup", "#rePassword", function () {
        if (rePasswordTip)
            s(this);
        m(this);
    });

    body.on("focus", "#rePassword", function () {
        if (rePasswordTip)
            s(this);
        m(this);
    });

    body.on("blur", "#password", function () {
        var t = $(this).val(),
            h = $(this),
            x = "";
        for (var z = 0; z < t.length; z++) {
            if (t[z] !== " ") {
                x += t[z];
            }
        }
        h.val(x);
    });

    function s(_this) {
        var t = $(_this).val(),
            h = $(_this),
            x = "";
        for (var z = 0; z < t.length; z++) {
            if (t[z] !== " ") {
                x += t[z];
            }
        }
        h.val(x);
    }

    function m(_this) {
        var another;
        if (_this.id === "rePassword") {
            another = "#password";
        } else if (_this.id === "password") {
            another = "#rePassword";
        } else {
            alert("fuck you")
        }

        var fTip = $("#repasswordtip"),
            sIcon = $("#rps");

        if ($(another).val().length >= 6) {
            if ($(_this).val().length < $(another).val().length) {
                sIcon.removeClass("icon-sucessfill blank");
                sIcon.addClass("icon-sucessfill blank hide");
                fTip.css("color", "#999");
                fTip.html("再输一遍密码以防搞错");
            } else if ($(_this).val().length === $(another).val().length) {
                if ($(_this).val() === $(another).val()) {
                    sIcon.removeClass("icon-sucessfill blank hide");
                    sIcon.addClass("icon-sucessfill blank");
                    fTip.css("color", "#999");
                    fTip.html("");
                } else {
                    sIcon.removeClass("icon-sucessfill blank");
                    sIcon.addClass("icon-sucessfill blank hide");
                    fTip.css("color", "#ff2e44");
                    fTip.html("两次输入的密码不一样!");
                }
            } else {
                sIcon.removeClass("icon-sucessfill blank");
                sIcon.addClass("icon-sucessfill blank hide");
                fTip.css("color", "#ff2e44");
                fTip.html("两次输入的密码不一样!");
            }
        } else {
            sIcon.removeClass("icon-sucessfill blank");
            sIcon.addClass("icon-sucessfill blank hide");
            fTip.css("color", "#999");
            fTip.html("再输一遍密码以防搞错");
        }
    }

    body.on("change", "#rePassword", function () {
        var t = $(this).val(),
            h = $(this),
            x = "";
        for (var z = 0; z < t.length; z++) {
            if (t[z] !== " ") {
                x += t[z];
            }
        }
        h.val(x);
    });

    body.on("blur", "#rePassword", function () {
        var t = $(this).val(),
            h = $(this),
            x = "";
        for (var z = 0; z < t.length; z++) {
            if (t[z] !== " ") {
                x += t[z];
            }
        }
        h.val(x);

    });


    function fuckOthersStrength(o, i, inner) {
        o.find("b i").eq(i).html(inner);
        o.find("b i").eq(i + 1).html("&nbsp;");
        // o.find("b i").eq(i+2).html("&nbsp;");
        // o.find("b i").eq(i+3).html("&nbsp;");
        o.find("b i").eq(i - 1).html("&nbsp;");
        // o.find("b i").eq(i-2).html("&nbsp;");
        // o.find("b i").eq(i-3).html("&nbsp;");
        o.find("b i").eq(i + 1).css("background", "#dbdbdb");
        o.find("b i").eq(i + 2).css("background", "#dbdbdb");
        o.find("b i").eq(i + 3).css("background", "#dbdbdb");
    }

    /**
     * 莫新店
     * */
});