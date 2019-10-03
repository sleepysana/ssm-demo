package cn.akira.controller;

import cn.akira.pojo.User;
import cn.akira.pojo.UserInfo;
import cn.akira.returnable.CommonData;
import cn.akira.service.UserService;
import cn.akira.util.ImgResizeUtil;
import cn.akira.util.RSAUtil;
import cn.akira.util.ValidateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
@RequestMapping("/user/")
public class UserController {

    private static final String RESOURCE_PATH = "D:\\Common\\AppData\\Tomcat\\deployed_resources\\akira\\";

    @Autowired
    private UserService userService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("doLogin")
    @ResponseBody
    public CommonData doLogin(User user, @RequestParam("credential") String credential, HttpSession session, HttpServletRequest request) throws Exception {
        String phoneReg = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
        if (credential.contains("@")) {
            user.setBindEmail(credential);
        } else if (credential.matches(phoneReg)) {
            user.setBindPhone(credential);
        } else {
            user.setUname(credential);
        }
        boolean unameIsEmpty = user.getUname() == null || user.getUname().equals("");
        boolean phoneIsEmpty = user.getBindPhone() == null || user.getBindPhone().equals("");
        boolean emailIsEmpty = user.getBindEmail() == null || user.getBindEmail().equals("");
        boolean passwordIsEmpty = user.getPassword() == null || user.getPassword().equals("");

        //如果除密码外的三个用作登录凭据的属性都为空 或者 密码为空  均不能登录  懂我意思吧？
        if (unameIsEmpty && phoneIsEmpty && emailIsEmpty || passwordIsEmpty) {
            return new CommonData("缺少关键的登录凭据", false);
        }
        //数据库检查
        String sha1HexPassword = DigestUtils.sha1Hex(user.getPassword()); //用户密码加密
        user.setPassword(sha1HexPassword);
        User dbUser = userService.getUser(user);
        if (dbUser != null) {
            //将用户信息存储到会话的中
            String password = user.getPassword();
            session.removeAttribute("SESSION_USER");

            /*
            数据库中的密码已经是经过sha1加密过了的，考虑到一些简单密码的sha1校验值的唯一性，可能会存在安全隐患
            遂再将校验值进行RSA加密再存入Session
             */
            String encryptedPassword = RSAUtil.encrypt(password);
            user.setPassword(encryptedPassword);
            String userHeadIcon = userService.getUserHeadIcon(dbUser.getId());
            UserInfo userInfo = user.getUserInfo() == null ? new UserInfo() : user.getUserInfo();
            userInfo.setHeadIcon(userHeadIcon);
            user.setUserInfo(userInfo);
            user.setUname(user.getUname() == null ? dbUser.getUname() : user.getUname());
            session.setAttribute("SESSION_USER", user);
            CommonData result = new CommonData();
            result.setResource(request.getContextPath() + "/index");
            return result;
        } else {
            System.out.println("用户名或密码不正确");
            CommonData result = new CommonData();
            result.setResource(request.getContextPath() + "/user/login");
            result.setFlag(false);
            result.setMessage("登录失败");
            return result;
        }
    }

    @RequestMapping("userList")
    public String userListPage3(Model model) {
        try {
            List<User> allUsersInfo = userService.getAllUsersInfo();
            model.addAttribute("user", allUsersInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/userList";
    }

    @RequestMapping("listUser")
    @ResponseBody
    public CommonData listUser() {
        try {
            CommonData data = new CommonData();
            List<User> allUsersInfo = userService.getAllUsersInfo();
            String s = JSON.toJSONString(allUsersInfo, SerializerFeature.WriteNullStringAsEmpty);

            data.setResource(JSONObject.parse(s));
            data.setStatus(0);
            data.setCustomProp(allUsersInfo.size());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonData("获取数据时发生错误了", e);
        }
    }

    @RequestMapping("showAddUser")
    public String toAddUserPage() {
        return "user/addUser";
    }

    @RequestMapping("showEditUser/{userId}")
    public String toEditUserPage(@PathVariable int userId, Model model) {
        try {
            User user = userService.getUserDetailWithoutPassword(userId);
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/editUser";
    }

    @RequestMapping("headIconUpload")
    @ResponseBody
    public CommonData uploadUserIcon(@RequestParam("file") MultipartFile multipartFile) {
        String uploadFilePath = RESOURCE_PATH + "image\\head\\cache\\";
        try {
            File cacheDir = new File(uploadFilePath);
            if (!cacheDir.exists() && !cacheDir.mkdir()) {
                return new CommonData("文件上传失败,因为不能创建缓存目录", false);
            }
            System.out.println("缓存路径:\n" + uploadFilePath);
            if (multipartFile.isEmpty()) {
                return new CommonData("请不要试图上传一个空文件", false);
            }
            //获取原始文件名
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename == null) {
                return new CommonData("没有文件名?你是怎么办到的?", false);
            }
            System.out.println("原始文件名 - " + originalFilename);

            //新文件名前缀 = 当前时间戳
            String newPrefixFileName = String.valueOf(new Date().getTime());

            //新文件名 = 新文件名前缀 + 原始文件名后缀(文件格式)
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = newPrefixFileName + suffix;

            String fullPath = uploadFilePath + newFileName; //文件全路径
            //文件名去重
            for (int i = 1; ; i++) {
                if (new File(fullPath).exists()) {
                    newFileName += i;
                } else {
                    break;
                }
            }
            //构建文件流对象
            File uploadFile = new File(fullPath);

            //上传至缓存目录
            multipartFile.transferTo(uploadFile);
            //图片宽高自适应处理
            ImgResizeUtil.selfAdapt(uploadFile);

            CommonData data = new CommonData();
            data.setResource(newFileName);
            data.setMessage("文件上传成功");
            return data;
        } catch (Exception e) {
            System.err.println("文件上传失败");
            e.printStackTrace();
            return new CommonData("文件上传失败了", e);
        }
    }

    @RequestMapping("createUser")
    @ResponseBody
    public CommonData createUser(
            @ModelAttribute User user,
            @RequestParam("rePassword") String rePassword) {
        String headIcon = user.getUserInfo().getHeadIcon();
        String password = user.getPassword();
        String iconCachePath = RESOURCE_PATH + "image\\head\\cache\\";
        String iconCacheFileFullPath = iconCachePath + headIcon;
        CommonData checkResult = ValidateUtil.UserFormDataValidate(user);
        if (!checkResult.isFlag()) {
            return checkResult;
        }
        // 密码校验
        if (password.replace(" ", "").equals("")) {
            return new CommonData("密码没输", "password", false);
        } else if (password.contains(" ")) {
            return new CommonData("你不能设置有空格的密码", "password", false);
        } else if (!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")) {
            return new CommonData("密码要有英文字母和数字，而且长度至少是6位，最多20位", "password", false);
        } else if (!password.equals(rePassword)) {
            return new CommonData("两次输入的密码都不一样,你要搞哪样嘛", "rePassword", false);
        }

        try {
            //sha1校验，将上传的头像存入目标路径
            File iconCacheFile = new File(iconCacheFileFullPath);
            if (!headIcon.equals("default_head_icon.png")) {
                iconUploadConfirm(user);
            }
            user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
            CommonData createResult = userService.createUser(user);
            if (createResult.isFlag() && iconCacheFile.exists()) {
                Files.delete(iconCacheFile.toPath());
            }
            return createResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonData("创建用户时遇到一个错误", e);
        }
    }

    @RequestMapping("editUser")
    @ResponseBody
    public CommonData editUser(@ModelAttribute User user) {
        String headIcon = user.getUserInfo().getHeadIcon();
        String iconCacheFileFullPath = RESOURCE_PATH + "image\\head\\cache\\" + headIcon;
        CommonData checkResult = ValidateUtil.UserFormDataValidate(user);
        if (!checkResult.isFlag()) {
            return checkResult;
        }
        try {
            File iconCacheFile = new File(iconCacheFileFullPath);
            String userHeadIcon = userService.getUserHeadIcon(user.getId());
            if (!headIcon.equals(userHeadIcon)) {
                iconUploadConfirm(user);
                user.getUserInfo().setId(user.getId());
                user.getRole().setId(user.getId());
                user.setRealNameAuth(null);
                CommonData editResult = userService.updateUserDetail(user);
                if (editResult.isFlag()) {
                    Files.delete(iconCacheFile.toPath());
                }
                return editResult;
            } else {
                user.getUserInfo().setId(user.getId());
                user.getRole().setId(user.getId());
                user.setRealNameAuth(null);
                return userService.updateUserDetail(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonData("修改用户时发生了错误", e);
        }
    }

    @RequestMapping("deleteUsers")
    @ResponseBody
    public CommonData deleteUsers(@RequestParam("ids[]") List<Integer> ids) {
        try {
            return userService.deleteUsers(ids);
        } catch (Exception e) {
            return new CommonData("批量删除失败了", false);
        }
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public CommonData deleteUser(@RequestParam("id") Integer id) {
        try {
            return userService.deleteUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonData("删除用户时遇到了点错误", e);
        }
    }

    @RequestMapping("checkUsername")
    @ResponseBody
    public CommonData checkUsername(@RequestParam("uname") String uname) {
        try {
            return userService.getUserByUname(uname);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonData("检查用户名占用时发生异常", e);
        }
    }

    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.removeAttribute("SESSION_USER");
        return "redirect:/";
    }

    private void iconUploadConfirm(User user) throws Exception {
        String headIcon = user.getUserInfo().getHeadIcon();
        String iconCachePath = RESOURCE_PATH + "image\\head\\cache\\";
        String iconCacheFileFullPath = iconCachePath + headIcon;
        File iconCacheFile = new File(iconCacheFileFullPath);
        FileInputStream fileInputStream = new FileInputStream(iconCacheFile);
        String suffix = headIcon.substring(headIcon.lastIndexOf("."));
        String hexedFileName = DigestUtils.sha1Hex(fileInputStream) + suffix;
        fileInputStream.close();
        String hexedFilePath = iconCachePath + "..\\" + hexedFileName;
        File hexedFileNamePath = new File(hexedFilePath);
        if (!hexedFileNamePath.exists()) {
            Files.copy(iconCacheFile.toPath(), hexedFileNamePath.toPath());
        }
        UserInfo userInfo = user.getUserInfo() == null ? new UserInfo() : user.getUserInfo();
        userInfo.setHeadIcon(hexedFileName);
        user.setUserInfo(userInfo);
    }
}