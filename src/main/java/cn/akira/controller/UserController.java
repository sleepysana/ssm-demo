package cn.akira.controller;

import cn.akira.pojo.User;
import cn.akira.service.UserService;
import cn.akira.util.ServletUtil;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean unameIsEmpty = user.getUname() == null || user.getUname().equals("");
        boolean phoneIsEmpty = user.getBindPhone() == null || user.getBindPhone().equals("");
        boolean emailIsEmpty = user.getBindEmail() == null || user.getBindEmail().equals("");
        boolean passwordIsEmpty = user.getPassword() == null || user.getPassword().equals("");

        //如果除密码外的三个用作登录凭据的属性都为空 或者 密码为空  均不能登录  懂我意思吧？
        if (unameIsEmpty && phoneIsEmpty && emailIsEmpty || passwordIsEmpty) {
            ServletUtil.redirectOutOfIframe("/login.jsp", request, response);
            return null;
        }
        //数据库检查
        String sha1HexPassword = DigestUtils.sha1Hex(user.getPassword()); //用户密码加密
        user.setPassword(sha1HexPassword);
        User dbUser = userService.getUser(user);
        if (dbUser != null) {
            //将用户信息存储到会话的中
            session.setAttribute("SESSION_USER", dbUser);
            return "redirect:/";
        } else {
            System.out.println("用户名或密码不正确");
            return "redirect:/login.jsp";
        }
    }

    @RequestMapping("userList")

    public String userList(Model model) throws Exception {
        List<User> userBaseInfoList = userService.getUserBaseInfoList();
        model.addAttribute("userList", userBaseInfoList);
        return "/user/userList";
    }
}
