package cn.akira.mapper;

import cn.akira.pojo.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


public interface UserMapper {
    User queryUser(User user);

    User queryUserByUname(@RequestParam("uname") String uname);

    User queryUserByBindPhone(@RequestParam("bindPhone") String bindPhone);

    User queryUserByBindEmail(@RequestParam("bindEmail") String bindEmail);

    User queryUserWithAllPropExceptPasswordById(@RequestParam("id") Integer id);

    int insert(User user);

    int deleteUserById(int id);

    List<User> queryAll();
}