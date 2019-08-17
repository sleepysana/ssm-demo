package cn.akira.service.impl;

import cn.akira.mapper.UserInfoMapper;
import cn.akira.mapper.UserMapper;
import cn.akira.pojo.User;
import cn.akira.pojo.UserInfo;
import cn.akira.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "UnnecessaryLocalVariable"})
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private UserInfoMapper userInfoMapper;

    @Override
    public User getUser(User user) throws Exception {
        User dbUser = userMapper.queryUser(user);
        return dbUser;
    }

    @Override
    public List<User> getUserBaseInfoList() throws Exception {
        List<User> users = userMapper.queryAllBaseInfo();
        return users;
    }
}