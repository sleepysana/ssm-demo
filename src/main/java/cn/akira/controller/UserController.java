package cn.akira.controller;

import cn.akira.pojo.User;
import cn.akira.service.UserService;
import cn.akira.util.ReturnableData;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("login.do")
    @ResponseBody
    public ReturnableData login(User user, HttpSession session, HttpServletRequest request) throws Exception {
        session.removeAttribute("SESSION_USER");
        boolean unameIsEmpty = user.getUname() == null || user.getUname().equals("");
        boolean phoneIsEmpty = user.getBindPhone() == null || user.getBindPhone().equals("");
        boolean emailIsEmpty = user.getBindEmail() == null || user.getBindEmail().equals("");
        boolean passwordIsEmpty = user.getPassword() == null || user.getPassword().equals("");

        //如果除密码外的三个用作登录凭据的属性都为空 或者 密码为空  均不能登录  懂我意思吧？
        if (unameIsEmpty && phoneIsEmpty && emailIsEmpty || passwordIsEmpty) {
            return new ReturnableData("缺少关键的登录凭据",false);
        }
        //数据库检查
        String sha1HexPassword = DigestUtils.sha1Hex(user.getPassword()); //用户密码加密
        user.setPassword(sha1HexPassword);
        User dbUser = userService.getUser(user);
        if (dbUser != null) {
            //将用户信息存储到会话的中
            dbUser.setPassword(null);
            session.setAttribute("SESSION_USER", dbUser);
            ReturnableData result = new ReturnableData();
            result.setResource(request.getContextPath() + "/");
            return result;
        } else {
            System.out.println("用户名或密码不正确");
            ReturnableData result = new ReturnableData();
            result.setResource(request.getContextPath() + "/");
            result.setFlag(false);
            return result;
        }
    }

    @RequestMapping("userList")
    public String userList(Model model) throws Exception {
        List<User> userBaseInfoList = userService.getUserBaseInfoList();
        model.addAttribute("userList", userBaseInfoList);
        return "/user/userList";
    }

    @RequestMapping("listUser")
    @ResponseBody
    public List<User> listUser() {
        try {
            List<User> userBaseInfoList = userService.getUserBaseInfoList();
            List<User> users = new ArrayList<>();
            for (User user : userBaseInfoList) {
                switch (user.getUserInfo().getGender()) {
                    case "1":
                        user.getUserInfo().setGender("男");
                        break;
                    case "2":
                        user.getUserInfo().setGender("女");
                        break;
                    default:
                        user.getUserInfo().setGender("未知");
                        break;
                }
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("exit")
    public String exit(HttpSession session){
        session.removeAttribute("SESSION_USER");
        return "redirect:/";
    }
}