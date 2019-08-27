package cn.akira.mapper;

import cn.akira.pojo.UserInfo;

import java.util.List;

public interface UserInfoMapper {
    List<UserInfo> queryBaseInfo();

    int insert(UserInfo userInfo);
}