package org.happy.waiter.module;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@IocBean
@At({ "/bill" })
public class Bill {
	@At
	@Ok("beetl:bill/index.html")
	public void index() {

	}

}
