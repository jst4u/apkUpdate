package com.tianxing.login;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.render.CaptchaRender;
import com.tianxing.common.model.User;

public class LoginController extends Controller {  
	
	@SuppressWarnings("rawtypes")
	
	public Map responseM = new HashMap();//用来存放返回的数据（json）
	//生成验证码
		public void imgcode() {
	        //这个是jfinal自带的验证码生成类，可以直接使用的
			CaptchaRender img = new CaptchaRender();

			render(img);
		}
		
		@SuppressWarnings("unchecked")
		@Before({LoginPwd.class})//先进行格式验证,在验证密码正确性
		
		//登陆action
		public void login(){
			//经过前面的验证，已经确定用户登录成功。
			//查询用户基本信息，放入session（基本信息主要为，用户名，角色，id等，具体看个人的情况，想放什么就放什么）
			String sql = "select id,username from user where username=? limit 1";
			User user = User.dao.findFirst(sql, getPara("username"));
			setSessionAttr("user", user);
//			CacheKit.put("UserLogin", "user", user); 
			responseM.put("state", "success");
			//返回成功登录的标志
			renderJson(responseM);
//			redirect("/filter/"); 
		}
   /* public void login() {  
          
        if (this.getPara("username") == null || this.getPara("username").equals("")) {  
            this.setAttr("msg", "");  
            this.render("login.html");  
        } else {  
            String username = this.getPara("username");  
            String password = this.getPara("password");  
  
            Record user = Db.findFirst(  
                    "select * from user where username=? and password=?",  
                    username, password);  
              
            if(user!=null)  
            {  
                CacheKit.put("UserLogin", "user", user);  
                System.out.println(this.getPara("username")+"登陆成功!");  
                this.redirect("/filter");  
            }else{  
                System.out.println(this.getPara("username")+"尝试登陆!");  
                this.setAttr("msg", "alert(\"用户名或密码错误，请重新登陆！\");");  
                this.render("login.html");  
            }  
        }  
    }  */
      
      
  
}  