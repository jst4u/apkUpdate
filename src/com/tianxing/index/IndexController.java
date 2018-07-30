package com.tianxing.index;

import java.util.List;

import com.jfinal.core.Controller;
import com.tianxing.common.model.Filter;

/**
 * IndexController
 */
public class IndexController extends Controller {
	public void index() {
		String jsonStr = "";
		String filterId ="";
		Filter filter = new Filter();
		if(null != getPara()){
			switch (getPara()) {
			case "checkfilterversions":
				filterId = getPara("filterid");
				if(filterId.indexOf(",")>0){
					String sql = "select * from filter where filter.filterid in ("+filterId+")";
					List<Filter> filterList = filter.find(sql);
//				renderJson("filter", filterList);
					jsonStr = "[";
					for (Filter f : filterList) {
						jsonStr += "{\"filterId\":" +f.getFilterId()+",\"timeUpdated\":"+f.getTimeUpdated()+",\"expires\":"+f.getExpires()+",\"version\":\""+f.getVersion()+"\"},";
					}
					jsonStr = jsonStr.substring(0,jsonStr.length()-1);
					jsonStr += "]";
				}else{
					filter = filter.findById(filterId);
//				renderJson(filter);
					if(null!=filter) {
						jsonStr = "[{\"filterId\":" +filter.getFilterId()+",\"timeUpdated\":"+filter.getTimeUpdated()+",\"expires\":"+filter.getExpires()+",\"version\":\""+filter.getVersion()+"\"}]";
					}
				}
				break;
			case "getfilter":
				filterId = getPara("filterid");
				String dirPath= FileUtil.getWebRootAbsolutePath()+"filters/filter_"+filterId+".txt";
				jsonStr = FileUtil.readTxtFile(dirPath);
//			renderJson(new File(dirPath));
				break;
			case "checkupdate":
				filterId = getPara("filterid");
				String dirPath1= FileUtil.getWebRootAbsolutePath()+"filters/filter_"+filterId+".txt";
				jsonStr = FileUtil.readTxtFile(dirPath1);
//			renderJson(new File(dirPath));
				break;
			default:
				break;
			}
		}
		renderText(jsonStr);
		

//		render("index.html");
	}

}





