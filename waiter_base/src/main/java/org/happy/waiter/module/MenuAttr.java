package org.happy.waiter.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.happy.admin.model.Account;
import org.happy.admin.model.Attri;
import org.happy.admin.model.Attribute;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.Scope;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

@IocBean
@At("attr")
public class MenuAttr {
	@Inject
	Dao dao;

	@At
	@Ok("json:full")
	public Object get(@Attr(scope = Scope.SESSION, value = "account") Account acc) {
		Attribute attribute = dao.fetch(Attribute.class, Cnd.where("accountid", "=", acc.accountid));
		dao.fetchLinks(attribute, "sizelist");
		dao.fetchLinks(attribute, "tastelist");
		return attribute;
	}

	@At
	@Ok("json:full")
	@AdaptBy(type = JsonAdaptor.class)
	public Object save(@Attr(scope = Scope.SESSION, value = "account") Account acc,
			@Param("..") Map<String, List<Attri>> mp) {
		List<Attri> list_t = (List<Attri>) mp.get("t");
		List<Attri> list_s = (List<Attri>) mp.get("s");
		Attribute attribute = dao.fetch(Attribute.class, Cnd.where("accountid", "=", acc.accountid));
		List<String> tstr = new ArrayList<String>();
		List<String> sstr = new ArrayList<String>();

		List<Attri> t = new ArrayList<Attri>();
		List<Attri> s = new ArrayList<Attri>();

		for (int i = 0; i < list_t.size(); i++) {
			Attri ar = list_t.get(i);
			if (ar.getName() != null) {
				Attri atr = dao.fetch(Attri.class, Cnd.where("name", "=", ar.getName()));
				if (atr == null) {
					atr = dao.insert(ar);
				}
				t.add(atr);
				tstr.add(ar.getName());
			}
		}
		for (int i = 0; i < list_s.size(); i++) {
			Attri ar = list_s.get(i);
			if (ar.getName() != null) {
				Attri atr = dao.fetch(Attri.class, Cnd.where("name", "=", ar.getName()));
				if (atr == null) {
					atr = dao.insert(ar);
				}
				s.add(atr);
				sstr.add(ar.getName());
			}
		}
		if (attribute == null) {
			attribute = new Attribute();
			attribute.setAccountid(acc.accountid);
			attribute.setStrSize(Json.toJson(sstr));
			attribute.setStrTaste(Json.toJson(tstr));
			attribute = dao.insert(attribute);
		} else {
			attribute.setStrSize(Json.toJson(sstr));
			attribute.setStrTaste(Json.toJson(tstr));
			dao.update(attribute);
		}

		dao.clearLinks(attribute, "tastelist");
		attribute.setTastelist(t);

		dao.clearLinks(attribute, "sizelist");
		attribute.setSizelist(s);

		if (t.size() > 0)
			dao.insertRelation(attribute, "tastelist");
		if (s.size() > 0)
			dao.insertRelation(attribute, "sizelist");

		return attribute;

	}

}
