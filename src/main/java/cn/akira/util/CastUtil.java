package cn.akira.util;

import cn.akira.pojo.User;


public class CastUtil {
    public static User genderCast(User user){
        switch (user.getUserInfo().getGender()) {
            case "1":
                user.getUserInfo().setGender("男");
                break;
            case "2":
                user.getUserInfo().setGender("女");
                break;
            default:
                user.getUserInfo().setGender("未知");
                break;
        }
        return user;
    }
}
