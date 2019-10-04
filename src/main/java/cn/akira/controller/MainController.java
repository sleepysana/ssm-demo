package cn.akira.controller;

import cn.akira.pojo.User;
import cn.akira.pojo.VerifyCode;
import cn.akira.returnable.CommonData;
import cn.akira.service.VerifyCodeService;
import cn.akira.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private VerifyCodeService verifyCodeService;

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

    @RequestMapping("register")
    public String toRegPage() {
        return "register";
    }

    @RequestMapping("generateVerifyCode")
    @ResponseBody
    public CommonData generateVerifyCode() {
        try {
            Map<String, String> map = ValidateUtil.generateVerifyCode("D:\\Common\\AppData\\Tomcat\\deployed_resources\\akira\\");
            String code = map.get("verifyCode");
            VerifyCode verifyCode = new VerifyCode();
            verifyCode.setVrfCode(code);
            verifyCode.setVrfType(11);
            String fileName = map.get("fileName");
            verifyCode.setFileName(fileName);
            CommonData insert = verifyCodeService.insert(verifyCode);
            if (insert.isFlag()) {
                CommonData commonData = new CommonData();
                commonData.setMessage("图片验证码已生成");
                commonData.setResource(fileName);
                return commonData;
            } else {
                return new CommonData("无法将验证码持久化", false);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new CommonData("验证码图片生成失败", e);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonData("未知错误", e);
        }
    }
}