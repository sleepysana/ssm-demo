package cn.akira.mapper;

import cn.akira.pojo.User;

import java.util.List;
import java.util.Map;


public interface UserMapper {
    User queryUser(User user);

    List<User> queryAllBaseInfo();

    int insert(User user);
}