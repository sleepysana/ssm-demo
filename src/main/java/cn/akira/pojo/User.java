package cn.akira.pojo;

import java.io.Serializable;

@SuppressWarnings("unused")
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
     * 密码(已加密)
     */
    private String password;

    /**
     * 用户信息
     */
    private UserInfo userInfo;

    /**
     * 用户角色
     */
    private UserRole role;

    /**
     * 实名信息
     */
    private UserRealNameAuth realNameAuth;

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
        this.uname = uname.replace(" ","").length()==0?null:uname;
    }

    public String getBindEmail() {
        return bindEmail;
    }

    public void setBindEmail(String bindEmail) {
        if (bindEmail.replace(" ", "").equals("")) {
            this.bindEmail = null;
        } else {
            this.bindEmail = bindEmail;
        }
    }

    public String getBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(String bindPhone) {
        if (bindPhone.replace(" ", "").equals("")) {
            this.bindPhone = null;
        } else {
            this.bindPhone = bindPhone;
        }
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

    public UserRealNameAuth getRealNameAuth() {
        return realNameAuth;
    }

    public void setRealNameAuth(UserRealNameAuth realNameAuth) {
        this.realNameAuth = realNameAuth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", bindEmail='" + bindEmail + '\'' +
                ", bindPhone='" + bindPhone + '\'' +
                ", password='" + password + '\'' +
                ", userInfo=" + userInfo +
                ", role=" + role +
                ", realNameAuth=" + realNameAuth +
                '}';
    }
}