package cn.akira.mapper;

import cn.akira.pojo.UserInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserInfoMapper {
    List<UserInfo> queryBaseInfo();

    int insert(UserInfo userInfo);

    String queryHeadIconById(@RequestParam("id") int id);

    List<UserInfo> queryAll();

    UserInfo queryAllById(@RequestParam("id") Integer id);

    int updateAllById(UserInfo userInfo);
}