package com.tianxing.update;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import com.tianxing.blog.BlogValidator;
import com.tianxing.common.model.Blog;
import com.tianxing.common.model.Filter;
import com.tianxing.index.AllInterceptor;
import com.tianxing.index.FileUtil;

/**
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(AllInterceptor.class)
public class UpdateController extends Controller {
	public void index() {
		setAttr("updatePage", Filter.dao.paginate(getParaToInt(0, 1), 20));
		render("update.html");
	}
	
	@Before(BlogValidator.class)
	public void save() {
		 try {
			 upload();
	        }catch (Exception e){
//	            logger.error(e.getLocalizedMessage(), e);
	        }
	        /*验证是否重复提交*/
//	        if(!checkFormToken()) return;
		getModel(Blog.class).save();
		Filter filter = Filter.dao.findById(getModel(Blog.class).getFilterId());
		if(null != filter){
			filter.setVersion(getModel(Blog.class).getVersion());
			filter.setExpires((long)21600);
			filter.setTimeUpdated(System.currentTimeMillis());
			filter.update();
		}else{
			filter = new Filter();
			filter.setFilterId(getModel(Blog.class).getFilterId());
			filter.setVersion(getModel(Blog.class).getVersion());
			filter.setExpires((long)21600);
			filter.setTimeUpdated(System.currentTimeMillis());
			filter.save();
		}
		redirect("/blog");
	}
	public void upload() {
		String dateStr = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		int maxSize = 10 * 1024 * 1024; // 10M
		UploadFile file = getFile("file", PathKit.getWebRootPath() + "/upload",
				maxSize, "utf-8");
		File source = file.getFile();
		String fileName = file.getFileName();
		String name = fileName.substring(0, fileName.lastIndexOf("."));
		String extension = fileName.substring(fileName.lastIndexOf("."));

		String filtersDir = PathKit.getWebRootPath() + "/filters/";
		String bakDir = PathKit.getWebRootPath() + "/bak/";
		String bakName = name + "_" + dateStr + extension;

		FileUtil.copyFile(filtersDir, fileName, source);
		FileUtil.copyFile(bakDir, bakName, source);

		source.delete();
	}
}


