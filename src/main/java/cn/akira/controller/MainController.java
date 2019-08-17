package cn.akira.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/")
public class MainController {

    @RequestMapping("index")
    public String toIndexPage(){
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("fileDownUpLoad")
    public String toFileUploadPage(){
        return "file/fileUpload";
    }

}
