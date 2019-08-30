package cn.akira.util;

import cn.akira.pojo.User;
import cn.akira.pojo.UserInfo;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

public class BeanTest {
    @Test
    public void toJsonTest(){
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

    @Test
    public void beanForEachTest() throws IllegalAccessException {
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
        for (Field field : user.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getName() + ":" + field.get(user) );
        }
    }
}
