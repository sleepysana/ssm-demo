package cn.akira.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error/")
public class ErrorController {
    @RequestMapping("400")
    public String e400(){
        return "error/400";
    }

    @RequestMapping("404")
    public String e404(){
        return "error/404";
    }

    @RequestMapping("500")
    public String e500(){
        return "error/500";
    }
}
