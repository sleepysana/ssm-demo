package cn.akira.pojo;

import java.io.Serializable;

@SuppressWarnings("StringBufferReplaceableByString")
public class UserRealNameAuth implements Serializable {
    /**
    * 用户ID
    */
    private Integer id;

    /**
    * 用户真实名字,和user表中的uname(用户名)无关
    */
    private String realName;

    /**
    * 用户身份证号/护照/学生证号 你想是什么就是什么
    */
    private String cid;

    /**
    * 证件类型(预留用的)
    */
    private String certType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", realName=").append(realName);
        sb.append(", cid=").append(cid);
        sb.append(", certType=").append(certType);
        sb.append("]");
        return sb.toString();
    }
}