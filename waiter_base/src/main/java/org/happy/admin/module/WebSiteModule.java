package org.happy.admin.module;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/admin/WebSite")
@IocBean
@RequiresAuthentication
public class WebSiteModule {
	@At("/SettingAccount")
	@Ok("beetl:admin/WebSite/SettingAccount.html")
	public void SettingAccount() {

	}
}
