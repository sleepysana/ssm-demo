package cn.akira.controller;

import cn.akira.pojo.User;
import cn.akira.returnable.CommonData;
import cn.akira.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	@RequestMapping("/checkEmail")
	@ResponseBody
	public CommonData checkRegEmail(@RequestParam("email") String email) {
		try {
			User user = new User();
			user.setBindEmail(email);
			Integer userId = userService.getUserIdByBindEmail(email);
			if (userId != null) {
				return new CommonData("这个邮箱已经被注册过了啊", false);
			} else
				return new CommonData();
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonData("出现异常了", e);
		}
	}

	@RequestMapping("/checkUsername")
	@ResponseBody
	public CommonData checkUsername(@RequestParam("username") String username) {
		try {
			return userService.getUserByUname(username);
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonData("检查用户名占用时出现异常", e);
		}
	}
}