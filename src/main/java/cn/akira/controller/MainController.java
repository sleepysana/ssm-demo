package cn.akira.controller;

import cn.akira.pojo.User;
import cn.akira.returnable.CommonData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("index")
    public String toIndexPage(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("SESSION_USER");
        model.addAttribute("SESSION_USER", sessionUser);
        return "index";
    }

    @RequestMapping("check")
    @ResponseBody
    public CommonData checkSession(HttpServletRequest request) {
        CommonData commonData = new CommonData();
        Object userSession = request.getSession().getAttribute("SESSION_USER");
        if (userSession == null) {
            commonData.setResource(request.getContextPath() + "/user/login");
            return commonData;
        }
        commonData.setResource(request.getContextPath() + "/index");
        return commonData;
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("fileDownUpLoad")
    public String toFileUploadPage() {
        return "file/fileUpload";
    }

    @RequestMapping("test")
    public String toTestPage() {
        return "test/test";
    }

    @RequestMapping("error")
    public String toErrorPage(@ModelAttribute CommonData commonData, Model model) {
        String errDetail = commonData.getErrDetail().replace("\n", "<br>");
        model.addAttribute("message", commonData.getMessage().replace("\n", "<br>"));
        model.addAttribute("errInfo", "<br>" + commonData.getErrInfo().replace("\n", "<br>"));
        model.addAttribute("errDetail", "<br>" + errDetail.substring(1, errDetail.length() - 1));
        return "businessResult/error";
    }

}
