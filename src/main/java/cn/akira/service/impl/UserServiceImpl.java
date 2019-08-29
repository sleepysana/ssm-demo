package cn.akira.service.impl;

import cn.akira.mapper.UserInfoMapper;
import cn.akira.mapper.UserMapper;
import cn.akira.mapper.UserRealNameAuthMapper;
import cn.akira.mapper.UserRoleMapper;
import cn.akira.pojo.User;
import cn.akira.pojo.UserInfo;
import cn.akira.pojo.UserRealNameAuth;
import cn.akira.pojo.UserRole;
import cn.akira.returnable.CommonData;
import cn.akira.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "UnnecessaryLocalVariable", "RedundantThrows"})
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRealNameAuthMapper userRealNameAuthMapper;

    @Override
    public User getUser(User user) throws Exception {
        User dbUser = userMapper.queryUser(user);
        return dbUser;
    }


    @Override
    public CommonData createUser(User user) throws Exception {
        if (userMapper.queryUserByUname(user.getUname()) != null) {
            return new CommonData("这个用户名已经被注册过了啊", "uname", false);
        }
        if (userMapper.queryUserByBindEmail(user.getBindEmail()) != null) {
            return new CommonData("这个邮箱已经被注册过了啊", "bindEmail", false);
        }
        if (userMapper.queryUserByBindPhone(user.getBindPhone()) != null) {
            return new CommonData("这个手机号已经被注册过了啊", "bindPhone", false);
        }
        if (userRealNameAuthMapper.queryInfoByCidAndCertType(
                user.getRealNameAuth().getCid(),
                user.getRealNameAuth().getCertType()
        ) != null) {
            return new CommonData("这个证件号码已经被认证过了啊", "cid", false);
        }
        int effectRow1 = userMapper.insert(user);
        User insertedUser = userMapper.queryUser(user);
        Integer id = insertedUser.getId();
        UserInfo userInfo = user.getUserInfo();
        userInfo = userInfo == null ? new UserInfo() : userInfo;
        userInfo.setId(id);
        int effectRow2 = userInfoMapper.insert(userInfo);
        UserRole role = user.getRole();
        role = role == null ? new UserRole() : role;
        role.setId(id);
        int effectRow3 = userRoleMapper.insert(role);
        UserRealNameAuth realNameAuth = user.getRealNameAuth();
        realNameAuth = realNameAuth == null ? new UserRealNameAuth() : realNameAuth;
        realNameAuth.setId(id);
        int effectRow4 = userRealNameAuthMapper.insert(realNameAuth);
        if (effectRow1 == 1 &&
                effectRow2 == 1 &&
                effectRow3 == 1 &&
                effectRow4 == 1) {
            return new CommonData("注册成功");
        } else {
            return new CommonData("注册失败", false);
        }
    }

    @Override
    public CommonData deleteUsers(List<Integer> ids) throws Exception {
        if (ids.size() == 0) {
            return new CommonData("没有选择任何数据", false);
        }
        try {
            int effectRows = 0;
            for (Integer id : ids) {
                effectRows += userMapper.deleteUserById(id);
            }
            if (effectRows == 0) {
                return new CommonData("没有数据被删除", false);
            }
            return new CommonData("删除了" + effectRows + "条数据");
        } catch (Exception e) {
            return new CommonData("批量删除失败", e);
        }
    }

    @Override
    public CommonData getUserByUname(String uname) throws Exception {
        if (userMapper.queryUserByUname(uname) != null) {
            return new CommonData("这个用户名已经被注册过了啊", false);
        } else return new CommonData();
    }

    @Override
    public String getUserHeadIcon(int id) throws Exception {
        String icon = userInfoMapper.queryHeadIconById(id);
        return icon;
    }

    @Override
    public List<User> getAllUsersInfo() throws Exception {
        List<User> users = userMapper.queryAll();
        List<User> allUsersDetails = new ArrayList<>();
        List<UserInfo> infoList = userInfoMapper.queryAll();
        List<UserRole> roleList = userRoleMapper.queryAll();
        List<UserRealNameAuth> authList = userRealNameAuthMapper.queryAll();
        for (User user : users) {
            for (UserInfo userInfo : infoList) {
                if (user.getId().equals(userInfo.getId())) {
                    user.setUserInfo(userInfo);
                    break;
                }
            }
            for (UserRole role : roleList) {
                if (user.getId().equals(role.getId())) {
                    user.setRole(role);
                    break;
                }
            }
            for (UserRealNameAuth auth : authList) {
                if (user.getId().equals(auth.getId())) {
                    user.setRealNameAuth(auth);
                    break;
                }
            }
            allUsersDetails.add(user);
        }
        return allUsersDetails;
    }

    @Override
    public User getUserDetailWithoutPassword(Integer id) throws Exception {
        return userMapper.queryUserWithAllPropExceptPasswordById(id);
    }
}