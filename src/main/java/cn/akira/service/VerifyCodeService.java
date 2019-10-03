package cn.akira.service;

import cn.akira.pojo.User;
import cn.akira.pojo.VerifyCode;
import cn.akira.returnable.CommonData;

public interface VerifyCodeService {
    CommonData insertVerifyCode (User user, String vrfCode, int vrfType) throws Exception;

    CommonData insert(VerifyCode verifyCode)throws Exception;
}
