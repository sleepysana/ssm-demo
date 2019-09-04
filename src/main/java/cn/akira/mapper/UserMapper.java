package cn.akira.mapper;

import cn.akira.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface UserMapper {

    User queryUser(User user);

    User queryActivatedUser(User user);

    Integer queryIdByUname(@RequestParam("uname") String uname);

    Integer queryIdByBindPhone(@RequestParam("bindPhone") String bindPhone);

    Integer queryIdByBindEmail(@RequestParam("bindEmail") String bindEmail);

    User queryUserWithAllPropExceptPasswordById(@RequestParam("id") Integer id);

    List<User> queryUsersWithAllPropExceptPassword();

    int insert(User user);

    int deleteUserById(int id);

    List<User> queryAll();

    User queryById(@RequestParam("id") Integer id);

    int updateAllById(User user);
}