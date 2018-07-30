package com.tianxing.filter;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.tianxing.common.model.Filter;
import com.tianxing.index.AllInterceptor;

/**
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(AllInterceptor.class)
public class FilterController extends Controller {
	public void index() {
		setAttr("filterPage", Filter.dao.paginate(getParaToInt(0, 1), 20));
		render("filter.html");
	}
	
	/* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}


