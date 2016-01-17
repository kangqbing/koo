package org.happy.admin.module;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/admin/Order")
@IocBean
@RequiresAuthentication
public class OrderModule {
	@At("/OrderManagement")
	@Ok("beetl:admin/Order/OrderManagement.html")
	public void OrderManagement() {

	}
}
