package cn.akira.controller;

import cn.akira.pojo.*;
import cn.akira.returnable.CommonData;
import cn.akira.service.UserService;
import cn.akira.service.VerifyCodeService;
import cn.akira.util.OthersUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private VerifyCodeService verifyCodeService;
    @Autowired
    private UserService userService;

    @RequestMapping("/checkEmail")
    @ResponseBody
    public CommonData checkRegEmail(@RequestParam("email") String email) {
        try {
            User user = new User();
            user.setBindEmail(email);
            Integer userId = userService.getUserIdByBindEmail(email);
            if (userId != null) {
                return new CommonData("这个邮箱已经被注册过了啊", false);
            } else
                return new CommonData();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonData("出现异常了", e);
        }
    }

    @RequestMapping("/checkUsername")
    @ResponseBody
    public CommonData checkUsername(@RequestParam("username") String username) {
        try {
            return userService.getUserByUname(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonData("检查用户名占用时出现异常", e);
        }
    }

    @RequestMapping("verifyPicCode")
    @ResponseBody
    public CommonData verifyPicCode(
            @RequestParam("randCode") String randCode,
            @RequestParam("fileName") String fileName) {
        try {
            randCode = randCode.toUpperCase();
            CommonData vrfCodeData = verifyCodeService.getAllByFileName(fileName);
            String vrfCode = (String) vrfCodeData.getResource();
            vrfCode = vrfCode.toUpperCase();
            if (randCode.equals(vrfCode)) {
                return vrfCodeData;
            } else return new CommonData("验证码没对", false);
        } catch (Exception e) {
            return new CommonData("遇到错误!", e);
        }
    }

    @RequestMapping("sendVerifyEmail")
    @ResponseBody
    public CommonData verifyEmail(@RequestParam("email") String email) {
        try {
            int i = verifyCodeService.deleteByEmail(email);
            System.out.println("删除了" + i + "条邮箱注册验证码脏数据");
            int vrfCode = (int) ((Math.random() * 9 + 1) * 100000);
            String content = "您本次的邮箱注册验证码为: " + vrfCode;
            OthersUtil.sendVerifyEmail(email, "不知道取什么标题好", content);
            VerifyCode verifyCode = new VerifyCode();
            verifyCode.setVrfCode(String.valueOf(vrfCode));
            verifyCode.setVrfType(21);
            verifyCode.setBindEmail(email);
            return verifyCodeService.insert(verifyCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonData("遇到错误了", e);
        }
    }

    @RequestMapping("doReg")
    @ResponseBody
    public CommonData doReg(@ModelAttribute User user, @RequestParam("verifyCode") String vrfCode) {
        try {
            String bindEmail = user.getBindEmail();
            VerifyCode vrfCodeBean = verifyCodeService.getAllByEmail(bindEmail);
            String vrfCode1 = vrfCodeBean.getVrfCode();
            if (!vrfCode.equals(vrfCode1))
                return new CommonData("验证码不正确", false);
            if (user.getRealNameAuth() == null)
                user.setRealNameAuth(new UserRealNameAuth());
            if (user.getUserInfo() == null)
                user.setUserInfo(new UserInfo());
            if (user.getRole() == null)
                user.setRole(new UserRole());
            user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
            user.setStatus(1);
            userService.createUser(user);
            return new CommonData("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonData("注册失败了", e);
        }
    }
}