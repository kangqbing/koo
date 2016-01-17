package org.happy.waiter.module;

import org.happy.base.util.MyUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.util.Context;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@IocBean
public class Wx {
	
	@Inject
	MyUtils utils;
	
	@At({"/wx"})
	@Ok("beetl:wx/index.html")
	public Context wx() {
		return Lang.context().set("ctx", utils.getScheme());
	}
}
