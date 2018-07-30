package com.tianxing.login;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import com.jfinal.validate.Validator;
import com.tianxing.common.model.User;

public class LoginPwd extends Validator {
	private Map<String, String> responseM = new HashMap<String, String>();

	@Override
	protected void validate(Controller c) {
		// 验证码忽略大小写，这里用到了jfinal 自带的验证验证码的方法
//		boolean b = CaptchaRender.validate(c, c.getPara("imgcode").toUpperCase());
//		if (!b) {
//			addError("imgcodeMsg", "验证码不正确！");
//			return;
//		}
		// 验证码正确，验证密码
		String username = c.getPara("username");
		// MD5加密后与数据库数据进行比较
		String password = HashKit.md5(c.getPara("password"));
		String sql = "select password from user where username =? limit 1";
		User user = User.dao.findFirst(sql, username);

		if (null== user) {
			addError("usernameMsg", "用户名不正确！");
			return;
		}
		if (!password.equals(user.get("password").toString())) {
			addError("passwordMsg", "密码不正确！");
			return;
		}

	}

	@Override
	protected void handleError(Controller c) {
		Enumeration<String> en = c.getParaNames();
		while (en.hasMoreElements()) {
			// 错误键，我们规定，所有的错误为 请求字段加上 Msg
			String key = en.nextElement().toString() + "Msg";
			if (c.getAttrForStr(key) != null) {
				responseM.put(key, c.getAttrForStr(key));
			}

		}
		responseM.put("state", "error");
		c.renderJson(responseM);
	}

}
