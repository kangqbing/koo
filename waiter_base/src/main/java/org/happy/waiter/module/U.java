package org.happy.waiter.module;

import javax.servlet.http.HttpServletResponse;

import org.happy.base.util.MyUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.util.Context;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@IocBean
public class U {
	
	@Inject
	MyUtils utils;
	
	@At("/partials/?")
	@Ok("beetl:partials/${obj}.html")
	public String index(String url) {
		return url;
	}

	@At("/goto")
	@Ok("beetl:${p.t}")
	public Context _goto(String t,HttpServletResponse resp) {
		if(t.endsWith(".css")){
			resp.setContentType("text/plain");
		}
		if(t.endsWith(".js")){
			resp.setContentType("application/javascript");
		}
		
		return Lang.context().set("ctx", utils.getScheme());
	}
}
