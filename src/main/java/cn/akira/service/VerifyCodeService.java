package cn.akira.service;

import cn.akira.pojo.User;
import cn.akira.returnable.CommonData;

public interface VerifyCodeService {
    CommonData insertVerifyCode (User user, String vrfCode, int vrfType) throws Exception;
}
