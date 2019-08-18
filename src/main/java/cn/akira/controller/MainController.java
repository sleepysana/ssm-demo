package cn.akira.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("index")
    public String toIndexPage(){
        return "index";
    }

    @RequestMapping("check")
    @ResponseBody
    public String checkSession(HttpServletRequest request) {
        Object userSession = request.getSession().getAttribute("SESSION_USER");
        if (userSession == null) {
            return  request.getContextPath()+"/user/login";
        }
        return  request.getContextPath()+"/index";
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("fileDownUpLoad")
    public String toFileUploadPage() {
        return "file/fileUpload";
    }

}
