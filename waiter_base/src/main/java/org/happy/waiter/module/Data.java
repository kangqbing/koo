package org.happy.waiter.module;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.happy.admin.model.Account;
import org.happy.admin.model.Store;
import org.happy.base.shiro.realm.WxToken;
import org.happy.wx.api.WxProxy;
import org.happy.wx.model.Wx_User;
import org.happy.zbus.LoginTokenStorager;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Scope;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.mp.model.QRParameter;
import com.foxinmy.weixin4j.mp.model.QRResult;
import com.foxinmy.weixin4j.mp.model.User;

@IocBean
@At({ "/data" })
public class Data {
	@Inject
	WxProxy api;

	@Inject
	Dao dao;

	@Inject
	LoginTokenStorager loginTokenStorager;

	@At
	@Ok("json")
	public Record login_ticket() throws Exception {
		int t = (int) (9999 + Math.random() * (99999 - 9999 + 9999));
		QRParameter qr = QRParameter.createTemporary(92000, t);
		QRResult out = api.createQR(qr);
		Record context = new Record();
		context.set("t", t);
		context.set("ticket", out.getTicket());
		return context;
	}

	@At
	@Ok("json")
	public Record checkLogin(String ek) {
		Record rt = new Record();
		try {
			Token tk = loginTokenStorager.lookup(ek);
			if (tk != null) {
				rt.set("success", true);
				String openid = tk.getAccessToken();
				Account sm = dao.fetch(Account.class, Cnd.where("openid", "=", openid));
				if (sm == null) {
					Wx_User wx = dao.fetch(Wx_User.class, Cnd.where("openid", "=", openid));
					if (wx != null) {
					} else {
						User user = api.getUser(openid);
						wx = new Wx_User();
						try {
							BeanUtils.copyProperties(wx, user);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
						wx = dao.insert(wx);
					}
					sm = new Account();
					sm.setManagerid(wx.getId());
					sm.setOpenid(openid);
					sm = dao.insert(sm);
				}
				try {
					WxToken ut = new WxToken();
					ut.setUsername(String.valueOf(sm.managerid));
					Subject subject = SecurityUtils.getSubject();
					ThreadContext.bind(subject);
					subject.login(ut);
				} catch (Exception e) {
				}
				Subject subject = SecurityUtils.getSubject();
				subject.getSession().setAttribute("account", sm);
			} else {
				rt.set("success", false);
			}
		} catch (WeixinException e) {
			e.printStackTrace();
		}
		return rt;

	}
	
	@At
	@Ok("json")
	public Object user(@Attr(scope = Scope.SESSION, value = "account") Account acc) {
		return dao.fetch(Wx_User.class,acc.managerid);
	}

}
