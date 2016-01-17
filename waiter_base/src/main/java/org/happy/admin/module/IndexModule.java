package org.happy.admin.module;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.happy.admin.model.Account;
import org.happy.wx.api.WxProxy;
import org.happy.wx.model.Wx_User;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.util.Context;
import org.nutz.mvc.Scope;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Ok;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.mp.model.QRParameter;
import com.foxinmy.weixin4j.mp.model.QRResult;

@IocBean
public class IndexModule {

	@Inject
	WxProxy api;

	@Inject
	Dao dao;

	@At("/admin")
	@Ok("beetl:admin/index.html")
	@RequiresAuthentication
	public Context admin(@Attr(scope = Scope.SESSION, value = "account") Account acc) throws WeixinException {
		Context context = Lang.context();
		context.set("user", dao.fetch(Wx_User.class, acc.getManagerid()));
		return context;
	}




	@At("/admin/Logout")
	@Ok(">>:/admin/login")
	@RequiresAuthentication
	public void Logout() throws WeixinException {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}

	@At
	@Ok("beetl:${p.t}")
	public void loadfile() {

	}

}
