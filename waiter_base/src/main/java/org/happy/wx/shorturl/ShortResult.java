package org.happy.wx.shorturl;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class ShortResult implements Serializable{
	private static final long serialVersionUID = 1L;
	@JSONField(name="url_short")
	private String url_short;
	public String getUrl_short() {
		return url_short;
	}
	public void setUrl_short(String url_short) {
		this.url_short = url_short;
	}
	public String getUrl_long() {
		return url_long;
	}
	public void setUrl_long(String url_long) {
		this.url_long = url_long;
	}
	@JSONField(name="url_long")
	private String url_long;
}