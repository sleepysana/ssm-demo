package cn.akira.pojo;

import java.io.Serializable;

public class UserRole implements Serializable {
    /**
     * 用户id (主键)
     */
    private Integer id;

    /**
     * 用户角色(-1:未激活)
     */
    private Integer role;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}