package cn.akira.pojo;

import java.io.Serializable;

public class User implements Serializable {
    /**
    * 用户id  主键
    */
    private Integer id;

    /**
    * 用户名(可用作登录凭据)
    */
    private String uname;

    /**
    * 绑定邮箱(可用作登录凭据)
    */
    private String bindEmail;

    /**
    * 绑定的电话号码(可用作登录凭据)
    */
    private String bindPhone;

    /**
     * 用户信息
     */
    private UserInfo userInfo;

    /**
     * 用户角色
     */
    private UserRole role;

    /**
    * 密码(已加密)
    */
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getBindEmail() {
        return bindEmail;
    }

    public void setBindEmail(String bindEmail) {
        this.bindEmail = bindEmail;
    }

    public String getBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(String bindPhone) {
        this.bindPhone = bindPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", bindEmail='" + bindEmail + '\'' +
                ", bindPhone='" + bindPhone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}