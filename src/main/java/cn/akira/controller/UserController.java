package cn.akira.controller;

import cn.akira.pojo.User;
import cn.akira.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login(User user, HttpSession session) throws Exception {
        if (user.getUname() == null ||
                user.getUname().equals("") ||
                user.getPassword() == null ||
                user.getPassword().equals("")) {
            return "welcome";
        }

        //数据库检查
        User dbUser = userService.getUser(user);
        if (dbUser != null) {
            //将用户信息存储到会话的中
            session.setAttribute("SESSION_USER", user);
            return "welcome";
        } else {
            System.out.println("用户名或密码不正确");
            return "redirect:/login";
        }
    }

    @RequestMapping("userList")

    public String userList(Model model) throws Exception {
        List<User> userBaseInfoList = userService.getUserBaseInfoList();
        model.addAttribute("userList", userBaseInfoList);
        return "/user/userList";
    }
}
