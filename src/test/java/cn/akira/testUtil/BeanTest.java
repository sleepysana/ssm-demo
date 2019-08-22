package cn.akira.testUtil;

import cn.akira.pojo.UserRealNameAuth;

public class BeanTest {

    public static void main(String[] args) {
        UserRealNameAuth userRealNameAuth = new UserRealNameAuth();
        userRealNameAuth.setId(12345);
        System.out.println(userRealNameAuth.toString());
    }
}
