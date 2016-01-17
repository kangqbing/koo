package org.happy.base.beetl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.beetl.core.Context;
import org.beetl.core.Function;
import org.happy.admin.model.Account;
import org.happy.admin.model.Store;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.mvc.Mvcs;

public class Isbill implements Function {
	@Override
	public Object call(Object[] paras, Context ctx) {
		HttpServletRequest request = (HttpServletRequest) ctx.getGlobal("request");
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("account");
		Dao dao = Mvcs.ctx().getDefaultIoc().get(Dao.class, "dao");
		List<Store> list = dao.query(Store.class, Cnd.where("accountid", "=", acc.accountid));
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

}
