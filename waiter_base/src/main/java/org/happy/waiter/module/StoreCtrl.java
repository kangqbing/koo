package org.happy.waiter.module;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.happy.admin.model.Account;
import org.happy.admin.model.Store;
import org.happy.waiter.module.pojo.JDataGrid;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.cri.Static;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.mvc.Scope;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

@IocBean
@At("/store")
@RequiresAuthentication
public class StoreCtrl {

	public void setRecordCount(Pager pager, Dao dao, Sql newSql) {
		pager.setRecordCount(dao.count("(" + newSql.toString() + ") _table_"));
	}

	@Inject
	Dao dao;

	@At("/id/?")
	@Ok("json")
	public Object storeid(int id) {
		return dao.fetch(Store.class, id);
	}
	
	
	
	

	@At
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public Object save(@Param("..") Store store,@Attr(scope = Scope.SESSION, value = "account") Account acc) {
		if (store.getId() == 0) {
			store.setAccountid(acc.accountid);
			store.setManagerid(acc.managerid);
			store = dao.insert(store);
		}
		return store;
	}
	
	
	@At
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public Object update(@Param("..") Store store,@Attr(scope = Scope.SESSION, value = "account") Account acc) {
		dao.update(store);
		return store;
	}
	
	@At
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public Object del(@Param("..") Store store,@Attr(scope = Scope.SESSION, value = "account") Account acc) {
		dao.delete(dao.fetch(Store.class, store.getId()));
		return store;
	}
	
	
	
	@At
	@Ok("json")
	public Object list(@Attr(scope = Scope.SESSION, value = "account") Account acc){
		Cnd cnd = Cnd.where("accountid", "=", acc.accountid);
		List<?> aaData = dao.query(Store.class, cnd);
		return aaData;
	}
	
	
	@At("/list_goodid/?")
	@Ok("json")
	public Object list_goodid(int id,@Attr(scope = Scope.SESSION, value = "account") Account acc){
		Cnd cnd = Cnd.where("accountid", "=", acc.accountid);
		if(id!=0){
			cnd.and(new Static("id in (select store_id from m_typelist where id="+id+")"));
		}
		List<?> aaData = dao.query(Store.class, cnd);
		return aaData;
	}
	
	

	@At
	@Ok("json")
	public JDataGrid list_grid(@Attr(scope = Scope.SESSION, value = "account") Account acc,
			@Param("::columns") List<?> columns, @Param("::search") Map<?, ?> search, int draw, int start, int length) {
		start = start / length;
		Pager pager = new Pager();
		pager.setPageNumber(start + 1);
		pager.setPageSize(length);

		Cnd cnd = Cnd.where("accountid", "=", acc.accountid);

		if (!Lang.isEmpty(search.get("value")) && !search.get("value").toString().equals("")) {
			cnd.and("name", "like", search.get("value"));
		}

		List<?> aaData = dao.query(Store.class, cnd);
		pager.setRecordCount(dao.count(Store.class, cnd));

		JDataGrid ret = new JDataGrid();
		ret.draw = draw;
		ret.data = aaData;
		ret.recordsTotal = pager.getRecordCount();
		ret.recordsFiltered = pager.getRecordCount();
		return ret;

	}

}
