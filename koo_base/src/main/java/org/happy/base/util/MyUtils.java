package org.happy.base.util;

import javax.servlet.ServletContext;

import org.nutz.mvc.Mvcs;

public class MyUtils {
	private ServletContext sc;

	public String getPath(String path) {
		return sc.getRealPath(path);
	}
	public String getScheme() {
		String basePath = Mvcs.getReq().getScheme() + "://"
				+ Mvcs.getReq().getServerName() + ":"
				+ Mvcs.getReq().getServerPort() + sc.getContextPath() + "/"; // 相对根路径
		return basePath;

	}
}
