package cn.akira.service;

import cn.akira.pojo.User;
import cn.akira.pojo.VerifyCode;
import cn.akira.returnable.CommonData;
import org.springframework.transaction.annotation.Transactional;

public interface VerifyCodeService {
    @Transactional
    CommonData insertVerifyCode (User user, String vrfCode, int vrfType) throws Exception;

    @Transactional
    CommonData insert(VerifyCode verifyCode)throws Exception;

    CommonData getAllByFileName(String fileName) throws Exception;

    @Transactional
    int deleteByEmail(String email) throws Exception;

    VerifyCode getAllByEmail(String email) throws Exception;
}
