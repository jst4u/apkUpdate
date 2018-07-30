package com.tianxing.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseFilter<M extends BaseFilter<M>> extends Model<M> implements IBean {

	public void setFilterId(java.lang.Integer filterId) {
		set("filterId", filterId);
	}

	public java.lang.Integer getFilterId() {
		return get("filterId");
	}

	public void setTimeUpdated(java.lang.Long timeUpdated) {
		set("timeUpdated", timeUpdated);
	}

	public java.lang.Long getTimeUpdated() {
		return get("timeUpdated");
	}

	public void setExpires(java.lang.Long expires) {
		set("expires", expires);
	}

	public java.lang.Long getExpires() {
		return get("expires");
	}

	public void setVersion(java.lang.String version) {
		set("version", version);
	}

	public java.lang.String getVersion() {
		return get("version");
	}

}