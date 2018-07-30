package com.tianxing.login;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginFormat extends Validator{
	@SuppressWarnings("rawtypes")
	public Map responseM = new HashMap();//用来存放返回的数据（json）
			//进行phone
//			static String phone = "^1[1-9]{10}$";
			static String regUsername = "^[A-Za-z0-9(!@#$%&)]{6,15}$";
			//进行密码验证正则，6-15位数字和字母特殊字符的组合
			static String regPassword="^[A-Za-z0-9(!@#$%&)]{6,15}$";
			//验证码格式
			static String regImgcode = "^[A-Za-z0-9(!@#$%&)]{4}";
	
	@Override
	protected void validate(Controller c) {
		//验证账号和密码
		validateRegex("phone", regUsername, "usernameMsg", "用户名格式不正确！");
		validateRegex("password", regPassword, "passwordMsg", "密码格式为6-15为英文与数字结合！");
		validateRegex("imgcode", regImgcode, "imgcodeMsg", "验证码格式不正确！");
		
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	protected void handleError(Controller c) {
		Enumeration<String>  en =c.getParaNames();
		while (en.hasMoreElements()) {
			//错误键，我们规定，所有的错误为 请求字段加上 Msg 
			String key = en.nextElement().toString()+"Msg";
			if(c.getAttrForStr(key)!=null){
				responseM.put(key, c.getAttrForStr(key));
			}
			
        }
        //这样我们可以将所有的错误作为一个json串返回前端页面
		responseM.put("state", "error");
		c.renderJson(responseM);
		
	}
}