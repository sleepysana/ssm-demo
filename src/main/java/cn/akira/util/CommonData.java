package cn.akira.util;

import java.io.Serializable;
import java.util.Arrays;

public class CommonData implements Serializable {
    private String message = null;
    private boolean flag = true;
    private String errInfo = null;
    private String errDetail = null;
    private Object resource = null;

    public CommonData() {}

    public CommonData(String message) {
        this.message = message;
    }

    public CommonData(String message, boolean flag) {
        this.message = message;
        this.flag = flag;
    }

    public CommonData(String message, boolean flag, Exception e) {
        this.message = message;
        this.flag = flag;
        this.errInfo = e.getLocalizedMessage();
        this.errDetail = Arrays.toString(e.getStackTrace());
    }

    public CommonData(String message, Object resource) {
        this.message = message;
        this.resource = resource;
    }

    public CommonData(String message, Object resource, boolean flag) {
        this.message = message;
        this.flag = flag;
        this.resource = resource;
    }

    public CommonData(Object resource,boolean flag) {
        this.flag = flag;
        this.resource = resource;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public String getErrDetail() {
        return errDetail;
    }

    public void setErrDetail(String errDetail) {
        this.errDetail = errDetail;
    }

    public Object getResource() {
        return resource;
    }

    public void setResource(Object resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "message='" + message + '\'' +
                ", flag=" + flag +
                ", errInfo='" + errInfo + '\'' +
                ", errDetail='" + errDetail + '\'' +
                ", resource=" + resource +
                '}';
    }
}