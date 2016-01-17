package org.happy.waiter.module;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.happy.base.util.MyUtils;
import org.happy.wx.api.WxProxy;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.util.Context;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.mp.model.QRParameter;
import com.foxinmy.weixin4j.mp.model.QRResult;

@IocBean
public class Index {
	@Inject
	MyUtils utils;
	@Inject
	WxProxy api;

	@Inject
	Dao dao;

	@At({ "/", "/index" })
	@Ok("beetl:index.html")
	@RequiresAuthentication
	public Context index() {
		return Lang.context().set("ctx", utils.getScheme());
	}

	@At("/admin/login")
	@Ok("beetl:admin/login.html")
	public Context login() throws WeixinException {
		int t = (int) (9999 + Math.random() * (99999 - 9999 + 9999));
		QRParameter qr = QRParameter.createTemporary(92000, t);
		QRResult out = api.createQR(qr);
		Context context = Lang.context().set("t", t).set("ticket", out.getTicket()).set("ctx", utils.getScheme());
		return context;
	}
}
