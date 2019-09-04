package cn.akira.pojo;

import java.io.Serializable;
import java.util.Date;

public class VerifyCode implements Serializable {
    private Integer userId;

    private String bindEmail;

    private String vrfCode;

    private Integer vrfType;

    private Date genDate;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBindEmail() {
        return bindEmail;
    }

    public void setBindEmail(String bindEmail) {
        this.bindEmail = bindEmail;
    }

    public String getVrfCode() {
        return vrfCode;
    }

    public void setVrfCode(String vrfCode) {
        this.vrfCode = vrfCode;
    }

    public Integer getVrfType() {
        return vrfType;
    }

    public void setVrfType(Integer vrfType) {
        this.vrfType = vrfType;
    }

    public Date getGenDate() {
        return genDate;
    }

    public void setGenDate(Date genDate) {
        this.genDate = genDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", bindEmail=").append(bindEmail);
        sb.append(", vrfCode=").append(vrfCode);
        sb.append(", vrfType=").append(vrfType);
        sb.append(", genDate=").append(genDate);
        sb.append("]");
        return sb.toString();
    }
}