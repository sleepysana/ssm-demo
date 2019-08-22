package cn.akira.controller;

import cn.akira.returnable.CommonData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("index")
    public String toIndexPage() {
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
    public String toTestPage(){
        return "test/test";
    }

  @RequestMapping("success")
    public String toSuccessPage(){
        return "error/success";
    }

    @RequestMapping("error")
    public String toErrorPage(){
        return "error/error";
    }
}
