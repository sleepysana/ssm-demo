package cn.akira.pojo;

import java.io.Serializable;
import java.util.Date;

public class VerifyCode implements Serializable {
    private Integer userId;

    private String bindEmail;

    private String vrfCode;

    private Integer vrfType;

    private String fileName;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getGenDate() {
        return genDate;
    }

    public void setGenDate(Date genDate) {
        this.genDate = genDate;
    }

    @Override
    public String toString() {
        return "VerifyCode{" +
                "userId=" + userId +
                ", bindEmail='" + bindEmail + '\'' +
                ", vrfCode='" + vrfCode + '\'' +
                ", vrfType=" + vrfType +
                ", fileName='" + fileName + '\'' +
                ", genDate=" + genDate +
                '}';
    }
}