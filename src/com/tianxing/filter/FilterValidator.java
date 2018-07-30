package com.tianxing.filter;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.tianxing.common.model.Blog;

/**
 * BlogValidator.
 */
public class FilterValidator extends Validator {
	
	protected void validate(Controller controller) {
//		validateToken(errorKey, errorMessage);
		if(null==controller.getFile()) validateRequired(0,  "fileMsg", "请选择规则文件!");
		validateRequiredString("blog.filterId", "filterIdMsg", "请输入规则ID!");
		validateRequiredString("blog.version", "versionMsg", "请输入规则版本号!");
		validateRequiredString("blog.content", "contentMsg", "请输入规则说明!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Blog.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/blog/save"))
			controller.render("add.html");
		else if (actionKey.equals("/blog/update"))
			controller.render("edit.html");
	}
}
