package cn.akira.service.impl;

import cn.akira.mapper.VerifyCodeMapper;
import cn.akira.pojo.User;
import cn.akira.pojo.VerifyCode;
import cn.akira.returnable.CommonData;
import cn.akira.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings({"RedundantThrows", "SpringJavaAutowiredFieldsWarningInspection"})
@Service("VerifyCodeService")
public class VerifyCodeServiceImpl implements VerifyCodeService {

	@Autowired
	private VerifyCodeMapper verifyCodeMapper;

	@Override
	@Transactional
	public CommonData insertVerifyCode(User user, String vrfCode, int vrfType) throws Exception {
		if (vrfType >= 20 && user.getBindEmail() == null) {
			return new CommonData("绑定邮箱不能为空", false);
		}
		VerifyCode verifyCode = new VerifyCode();
		if (user != null) {
			verifyCode.setUserId(user.getId());
			verifyCode.setBindEmail(user.getBindEmail());
		}
		verifyCode.setVrfCode(vrfCode);
		verifyCode.setVrfType(vrfType);
		verifyCodeMapper.insertSelective(verifyCode);
		return new CommonData("验证码已持久化");
	}

	@Override
	@Transactional
	public CommonData insert(VerifyCode verifyCode) throws Exception {
		int i = verifyCodeMapper.insertSelective(verifyCode);
		if (i == 1) {
			return new CommonData("验证码持久化成功");
		} else
			return new CommonData("我...失败了吗...", false);
	}

	@Override
	public CommonData getAllByFileName(String fileName) throws Exception {
		VerifyCode verifyCode = verifyCodeMapper.queryAllByFileName(fileName);
		CommonData commonData = new CommonData();
		commonData.setResource(verifyCode.getVrfCode());
		return commonData;
	}

	@Override
	@Transactional
	public int deleteByEmail(String email) throws Exception{
		return verifyCodeMapper.deleteByEmail(email);
	}

	@Override
	public VerifyCode getAllByEmail(String email) throws Exception {
		return verifyCodeMapper.queryAllByBindEmail(email);
	}
}