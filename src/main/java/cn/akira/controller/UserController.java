package cn.akira.controller;

import cn.akira.pojo.User;
import cn.akira.service.UserService;
import cn.akira.util.CastUtil;
import cn.akira.returnable.CommonData;
import cn.akira.returnable.LayuiTableData;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("doLogin")
    @ResponseBody
    public CommonData doLogin(User user, HttpSession session, HttpServletRequest request) throws Exception {
        session.removeAttribute("SESSION_USER");
        boolean unameIsEmpty = user.getUname() == null || user.getUname().equals("");
        boolean phoneIsEmpty = user.getBindPhone() == null || user.getBindPhone().equals("");
        boolean emailIsEmpty = user.getBindEmail() == null || user.getBindEmail().equals("");
        boolean passwordIsEmpty = user.getPassword() == null || user.getPassword().equals("");

        //如果除密码外的三个用作登录凭据的属性都为空 或者 密码为空  均不能登录  懂我意思吧？
        if (unameIsEmpty && phoneIsEmpty && emailIsEmpty || passwordIsEmpty) {
            return new CommonData("缺少关键的登录凭据", false);
        }
        //数据库检查
        String sha1HexPassword = DigestUtils.sha1Hex(user.getPassword()); //用户密码加密
        user.setPassword(sha1HexPassword);
        User dbUser = userService.getUser(user);
        if (dbUser != null) {
            //将用户信息存储到会话的中
            dbUser.setPassword(null);
            session.setAttribute("SESSION_USER", dbUser);
            CommonData result = new CommonData();
            result.setResource(request.getContextPath() + "/index");
            return result;
        } else {
            System.out.println("用户名或密码不正确");
            CommonData result = new CommonData();
            result.setResource(request.getContextPath() + "/user/login");
            result.setFlag(false);
            return result;
        }
    }

    @RequestMapping("userList1")
    public String userListPage1(Model model) throws Exception {
        List<User> userBaseInfoList = userService.getUserBaseInfoList();
        List<User> users = new ArrayList<>();
        for (User user : userBaseInfoList) {
            users.add(CastUtil.genderCast(user));
        }
        model.addAttribute("userList", users);
        return "user/userList1";
    }

    @RequestMapping("userList2")
    public String userListPage2() {
        return "user/userList2";
    }

    @RequestMapping("userList3")
    public String userListPage3() {
        return "user/userList3";
    }

    @RequestMapping("listUser2")
    @ResponseBody
    public CommonData listUser2() {
        try {
            List<User> userBaseInfoList = userService.getUserBaseInfoList();
            List<User> users = new ArrayList<>();
            for (User user : userBaseInfoList) {
                users.add(CastUtil.genderCast(user));
            }
            CommonData commonData = new CommonData();
            commonData.setResource(users);
            return commonData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("listUser3")
    @ResponseBody
    public LayuiTableData listUser3() {
        try {
            List<User> userBaseInfoList = userService.getUserBaseInfoList();
            List<User> users = new ArrayList<>();
            for (User user : userBaseInfoList) {
                users.add(CastUtil.genderCast(user));
            }
            return new LayuiTableData(0, users.size(), users);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("showAddUser")
    public String toAddUserPage() {
        return "user/addUser";
    }

    @RequestMapping("createUser")
    @ResponseBody
    public CommonData createUser(
            @ModelAttribute User user,
            @RequestParam("rePassword") String rePassword) {
        String emailReg = "^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";
        String chineseMainLandPhoneReg = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
        String uname = user.getUname();
        String bindPhone = user.getBindPhone();
        String bindEmail = user.getBindEmail();
        String password = user.getPassword();
        String email = user.getUserInfo().getEmail();
        String addr = user.getUserInfo().getAddr();
        String realName = user.getRealNameAuth().getRealName();
        String cid = user.getRealNameAuth().getCid();
        String certType = user.getRealNameAuth().getCertType();
        if (uname == null) {
            return new CommonData("用户名是必填的哦~", "uname",false);
        } else if (uname.length() < 3) {
            return new CommonData("用户名至少3个字符", "uname",false);
        } else if (uname.contains(" ") || uname.contains("@")) {
            return new CommonData("用户名不能有空格或者艾特符","uname", false);
        }else if (uname.matches(chineseMainLandPhoneReg)){
            return new CommonData("不要用疑似手机号格式的用户名嘛","uname", false);
        }

        if (bindPhone == null && bindEmail == null) {
            return new CommonData("邮箱和手机至少得绑一个吧", false);
        }

        if (bindPhone != null && !bindPhone.matches(chineseMainLandPhoneReg)) {
            return new CommonData("手机号格式不对","bindPhone", false);
        }

        if (bindEmail != null && !bindEmail.matches(emailReg)) {
            return new CommonData("邮箱格式没写对", "bindEmail",false);
        }

        // 密码校验
        if (password.replace(" ", "").equals("")) {
            return new CommonData("密码没输", "password", false);
        } else if (password.contains(" ")) {
            return new CommonData("你不能设置有空格的密码", "password", false);
        } else if (!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")) {
            return new CommonData("密码要有英文字母和数字，而且长度至少是6位，最多20位", "password", false);
        } else if (!password.equals(rePassword)) {
            return new CommonData("两次输入的密码都不一样,你要搞哪样嘛", "rePassword", false);
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

        try {
            user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
            return userService.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonData("创建用户时遇到一个错误", e);
        }
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public CommonData deleteUser(@RequestParam("ids[]") List<Integer> ids) {
        try {
            return userService.deleteUsers(ids);
        } catch (Exception e) {
            return new CommonData("批量删除失败了", false);
        }
    }

    @RequestMapping("checkUsername")
    @ResponseBody
    public CommonData checkUsername(@RequestParam("uname") String uname) {
        try {
            return userService.getUserByUname(uname);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonData("检查用户名占用时发生异常", e);
        }
    }

    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.removeAttribute("SESSION_USER");
        return "redirect:/";
    }
}