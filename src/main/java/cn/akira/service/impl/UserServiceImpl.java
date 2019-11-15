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
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        User dbUser = userMapper.queryActivatedUser(user);
        return dbUser;
    }

    @Override
    @Transactional
    public CommonData createUser(User user) throws Exception {
        if (userMapper.queryIdByUname(user.getUname()) != null) {
            return new CommonData("这个用户名已经被注册过了啊", "uname", false);
        }
        if (userMapper.queryIdByBindEmail(user.getBindEmail()) != null) {
            return new CommonData("这个邮箱已经被注册过了啊", "bindEmail", false);
        }
        if (userMapper.queryIdByBindPhone(user.getBindPhone()) != null) {
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
    @Transactional
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
    @Transactional
    public CommonData deleteUserById(Integer id) throws Exception {
        int effectRows = userMapper.deleteUserById(id);
        return effectRows < 1 ? new CommonData("删除失败", false) : new CommonData("删除成功");
    }

    @Override
    public CommonData getUserByUname(String uname) throws Exception {
        if (userMapper.queryIdByUname(uname) != null) {
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
        return userMapper.queryUsersWithAllPropExceptPassword();
    }

    @Override
    public User getUserDetailWithoutPassword(Integer id) throws Exception {
        return userMapper.queryUserWithAllPropExceptPasswordById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonData updateUserDetail(User user) throws Exception {
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
        }
        Integer id1 = userMapper.queryIdByUname(user.getUname());
        Integer id2 = userMapper.queryIdByBindPhone(user.getBindPhone());
        Integer id3 = userMapper.queryIdByBindEmail(user.getBindEmail());
        if (id1 != null && !user.getId().equals(id1))
            return new CommonData("这个用户名已经被注册过了", "uname", false);
        else if (id2 != null && !user.getId().equals(id2))
            return new CommonData("其他用户已经绑定了这个手机号", "bindPhone", false);
        else if (id3 != null && !user.getId().equals(id3))
            return new CommonData("其他用户已经绑定了这个邮箱", "bindEmail", false);

        userMapper.updateAllById(user);
        userInfoMapper.updateAllById(user.getUserInfo());
        userRoleMapper.updateAllById(user.getRole());
        return new CommonData("修改成功");
    }

    @Override
    public Integer getUserIdByBindEmail(String bindEmail) throws Exception {
        return userMapper.queryIdByBindEmail(bindEmail);
    }
}