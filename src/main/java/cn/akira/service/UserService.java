package cn.akira.service;

import cn.akira.pojo.User;
import cn.akira.returnable.CommonData;

import java.util.List;


public interface UserService {
    /**
     * 根据User信息去检查数据库是否存在用户以及登录信息是否正确
     *
     * @param user user from view
     * @return user from db
     */
    User getUser(User user) throws Exception;

    CommonData createUser(User user) throws  Exception;

    CommonData deleteUsers(List<Integer> ids)  throws Exception;

    CommonData deleteUserById(Integer id) throws Exception;

    CommonData getUserByUname(String uname) throws Exception;

    String getUserHeadIcon(int id) throws Exception;

    List<User> getAllUsersInfo() throws Exception;

    User getUserDetailWithoutPassword(Integer id) throws Exception;

    CommonData updateUserDetail(User user) throws Exception;

    Integer getUserIdByBindEmail(String bindEmail) throws Exception;
}