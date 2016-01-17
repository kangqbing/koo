package org.happy.waiter.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.happy.admin.model.Account;
import org.happy.admin.model.Attri;
import org.happy.admin.model.Store;
import org.happy.admin.model.TypeList;
import org.happy.waiter.module.pojo.JDataGrid;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Scope;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

@IocBean
@At("good")
public class MenuGood {

	@Inject
	Dao dao;

	@SuppressWarnings("unchecked")
	@At
	@Ok("json:full")
	@AdaptBy(type = JsonAdaptor.class)
	public void save(@Attr(scope = Scope.SESSION, value = "account") Account acc, @Param("..") Map<String, Object> mp) {
		List<Integer> storeids = (List<Integer>) mp.get("storeid");
		if (storeids.size() == 1) {
			if (storeids.get(0) == 0) {
				List<Store> ls = dao.query(Store.class, Cnd.where("accountid", "=", acc.accountid));
				storeids = new ArrayList<Integer>();
				for (int i = 0; i < ls.size(); i++) {
					storeids.add(ls.get(i).getId());
				}
			}
		}
		for (int i = 0; i < storeids.size(); i++) {
			TypeList tp = new TypeList();
			tp.setStore_id(storeids.get(i));
			tp.setName((String) mp.get("name"));
			tp.setAccountid(acc.accountid);
			tp.setManagerid(acc.managerid);
			tp.setStoreName(dao.fetch(Store.class, storeids.get(i)).getName());
			try {
				tp.setSort((Integer) mp.get("sort"));
			} catch (Exception e) {
			}
			dao.insert(tp);
		}

	}

	@At
	@Ok("json:full")
	@AdaptBy(type = JsonAdaptor.class)
	public void del(@Attr(scope = Scope.SESSION, value = "account") Account acc, @Param("..") TypeList mp) {
		dao.delete(mp);
	}
	
	
	@At
	@Ok("json")
	public JDataGrid names(@Attr(scope = Scope.SESSION, value = "account") Account acc, int storeid) {
		Cnd cnd = Cnd.where("accountid", "=", acc.accountid);
		if (storeid != 0) {
			cnd.and("store_id", "=", storeid);
		}
		cnd.groupBy("name");
		List<TypeList> list = dao.query(TypeList.class, cnd, null);
		
		JDataGrid ret = new JDataGrid();
		ret.data = list;
		ret.recordsTotal = list.size();
		return ret;
		
	}
	
	
	

	@At
	@Ok("json")
	public JDataGrid list(@Attr(scope = Scope.SESSION, value = "account") Account acc, int page, int count,
			int storeid) {
		Pager pager = new Pager();
		pager.setPageNumber(page);
		pager.setPageSize(count);

		Cnd cnd = Cnd.where("accountid", "=", acc.accountid);
		if (storeid != 0) {
			cnd.and("store_id", "=", storeid);
		}
		List<TypeList> list = dao.query(TypeList.class, cnd, pager);
		pager.setRecordCount(dao.count(TypeList.class, cnd));

		JDataGrid ret = new JDataGrid();
		ret.data = list;
		ret.recordsTotal = pager.getRecordCount();
		return ret;

	}

	@At
	@Ok("json")
	public JDataGrid list_good(@Attr(scope = Scope.SESSION, value = "account") Account acc,
			@Param("::columns") List<?> columns, @Param("::search") Map<?, ?> search, int draw, int start, int length,
			int storeid) {
		start = start / length;
		Pager pager = new Pager();
		pager.setPageNumber(start + 1);
		pager.setPageSize(length);

		Cnd cnd = Cnd.where("accountid", "=", acc.accountid);
		if (storeid != 0) {
			cnd.and("store_id", "=", storeid);
		}
		List<TypeList> list = dao.query(TypeList.class, cnd, pager);
		pager.setRecordCount(dao.count(TypeList.class, cnd));

		JDataGrid ret = new JDataGrid();
		ret.draw = draw;
		ret.data = list;
		ret.recordsTotal = pager.getRecordCount();
		ret.recordsFiltered = pager.getRecordCount();
		return ret;

	}
}
