package com.tianxing.index;

import java.util.Date;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.tianxing.common.model.User;

/**
 * 主拦截器
 */
public class AllInterceptor implements Interceptor {
	
	public void intercept(Invocation inv) {
//        Record user = CacheKit.get("UserLogin", "user");  
        User user = (User)inv.getController().getSessionAttr("user");
        if (null == user) {  
            // 如果用户为空则跳转登陆，当然还可以进一步的操作  
            inv.getController().redirect("/login/login.html");  
            System.out.println(new Date() + "  未登录用户，拦截成功....");  
        } else {  
            System.out.println(new Date() + "  " + user.getStr("username")  
                    + "已登陆，放行....");  
            inv.invoke();  
        }  
    }  
	 
}
