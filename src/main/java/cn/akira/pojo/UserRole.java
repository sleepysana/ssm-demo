package cn.akira.pojo;

public class UserRole {
    /**
     * 用户id (主键)
     */
    private Integer id;

    /**
     * 用户角色(-1:未激活)
     */
    private Integer role;

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
        return "UseRole{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
