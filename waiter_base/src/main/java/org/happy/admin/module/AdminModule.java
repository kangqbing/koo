package org.happy.admin.module;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.happy.admin.model.Account;
import org.happy.admin.model.Store;
import org.happy.admin.model.StoreType;
import org.happy.base.shiro.realm.WxToken;
import org.happy.wx.model.Wx_User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

@IocBean
@At("admin")
public class AdminModule {

	@Inject
	Dao dao;

	@At
	@Ok("json")
	public Record checkRegister(String openid) {
		Account sm = dao.fetch(Account.class, Cnd.where("openid", "=", openid));
		Record rcd = new Record();
		rcd.set("success", true);
		if (sm == null) {
			rcd.set("step", 1);
			Wx_User wx = dao.fetch(Wx_User.class, Cnd.where("openid", "=", openid));
			sm = new Account();
			sm.setManagerid(wx.getId());
			sm.setOpenid(openid);
			sm = dao.insert(sm);
			rcd.set("managerid", sm.managerid);
			rcd.set("accountid", sm.accountid);
			
		} else {
			rcd.set("step", 0);
			try {
				WxToken ut = new WxToken();
				ut.setUsername(String.valueOf(sm.managerid));
				Subject subject = SecurityUtils.getSubject();
				ThreadContext.bind(subject);
				subject.login(ut);
			} catch (Exception e) {
			}
			rcd.set("managerid", sm.managerid);
			rcd.set("accountid", sm.accountid);
			
			Subject subject = SecurityUtils.getSubject();
			subject.getSession().setAttribute("account", sm);
			
		}

		return rcd;
	}

	@At
	@Ok("json")
	public Object GetStoreType() {
		return dao.query(StoreType.class, null);
	}

	@At
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public Object SaveStore(@Param("..") Account aa, @Param("..") Store store) {
		System.out.println(Json.toJson(aa));
		store.setAccountid(aa.accountid);
		store.setManagerid(aa.managerid);
		dao.insert(store);
		try {
			WxToken ut = new WxToken();
			ut.setUsername(String.valueOf(aa.managerid));
			Subject subject = SecurityUtils.getSubject();
			ThreadContext.bind(subject);
			subject.login(ut);
		} catch (Exception e) {
		}
		
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("account", aa);
		
		Record rcd = new Record();
		rcd.set("success", true);
		return rcd;
	}

}
