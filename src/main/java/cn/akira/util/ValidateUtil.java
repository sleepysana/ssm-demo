package cn.akira.util;

import cn.akira.pojo.User;
import cn.akira.returnable.CommonData;

public class ValidateUtil {
    public static CommonData UserFormDataValidate(User user){
        String emailReg = "^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";
        String chineseMainLandPhoneReg = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
        String uname = user.getUname();
        String bindPhone = user.getBindPhone();
        String bindEmail = user.getBindEmail();
        String email = user.getUserInfo().getEmail();
        String addr = user.getUserInfo().getAddr();
        String realName = user.getRealNameAuth().getRealName();
        String cid = user.getRealNameAuth().getCid();
        String certType = user.getRealNameAuth().getCertType();

        if (uname == null) {
            return new CommonData("用户名是必填的哦~", "uname", false);
        } else if (uname.length() < 3) {
            return new CommonData("用户名至少3个字符", "uname", false);
        } else if (uname.contains(" ") || uname.contains("@")) {
            return new CommonData("用户名不能有空格或者艾特符", "uname", false);
        } else if (uname.matches(chineseMainLandPhoneReg)) {
            return new CommonData("不要用疑似手机号格式的用户名嘛", "uname", false);
        }

        if (bindPhone == null && bindEmail == null) {
            return new CommonData("邮箱和手机至少得绑一个吧", false);
        }

        if (bindPhone != null && !bindPhone.matches(chineseMainLandPhoneReg)) {
            return new CommonData("手机号格式不对", "bindPhone", false);
        }

        if (bindEmail != null && !bindEmail.matches(emailReg)) {
            return new CommonData("邮箱格式没写对", "bindEmail", false);
        }

        if (email != null && !email.matches(emailReg)) {
            return new CommonData("邮箱格式不正确", "email", false);
        }
        if (addr != null && (addr.replace(" ", "").length() < 5)) {
            return new CommonData("地址写详细点嘛", "addr", false);
        }
        if (!((/*都为空*/
                realName == null &&
                        cid == null &&
                        certType == null
        ) || (/*都不为空*/
                realName != null &&
                        cid != null &&
                        certType != null
        ))) {
            return new CommonData("如需实名信息,就请将其完善", false);
        } else if (cid != null && certType.equals("1") && !cid.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$")) {
            return new CommonData("身份证格式不对", "cid", false);
        }
        return new CommonData();
    }
}
