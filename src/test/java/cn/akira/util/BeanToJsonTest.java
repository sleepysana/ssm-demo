package cn.akira.util;

import cn.akira.pojo.User;
import cn.akira.pojo.UserInfo;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.util.Date;

public class BeanToJsonTest {
    @Test
    public void 就是类名说的意思(){
        User user = new User();
        user.setId(123);
        user.setUname("小憨包");
        user.setPassword("pw");
        UserInfo userInfo = new UserInfo();
        userInfo.setGender("1");
        userInfo.setAddr("惠水");
        userInfo.setBirthday(null);
        userInfo.setRegDate(new Date());
        userInfo.setHeadIcon(null);
        user.setUserInfo(userInfo);
        JSONArray jsonArray = JSONArray.fromObject(userInfo);
        String s = jsonArray.toString();
        System.out.println(s);
    }
}
