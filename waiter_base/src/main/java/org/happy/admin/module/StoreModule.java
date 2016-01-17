package org.happy.admin.module;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.happy.admin.model.Account;
import org.happy.admin.model.Store;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.Scope;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

@IocBean
@At("/admin/store")
@RequiresAuthentication
public class StoreModule {

	@Inject
	Dao dao;

	@At
	@Ok("beetl:admin/store/storelist.html")
	public void storelist() {

	}

	@At
	@Ok("beetl:admin/store/storedetails.html")
	public void storedetails() {

	}
	
	@At
	@Ok("json")
	public Object StoreDel(int id){
		dao.delete(Store.class, id);
		Record rcd = new Record();
		rcd.set("result", "Ok");
		return rcd.set("msg", "删除成功！");
	}
	@At
	@Ok("json")
	public Object SaveBaseInfo(@Attr(scope = Scope.SESSION, value = "account") Account acc, @Param("..") Store store) {
		store.setAccountid(acc.accountid);
		store.setManagerid(acc.managerid);
		store = dao.insert(store);
		Record rcd = new Record();
		rcd.set("result", "Ok");
		rcd.set("msg", store.getId());
		return rcd;
	}

	@At
	@Ok("json")
	public Object StoreGrid(@Attr(scope = Scope.SESSION, value = "account") Account acc, int pageIndex) {
		Pager pager = dao.createPager(pageIndex, 20);
		List<Store> list = dao.query(Store.class, Cnd.where("accountid", "=", acc.accountid), pager);
		pager.setRecordCount(dao.count(Store.class, Cnd.where("accountid", "=", acc.accountid)));
		System.out.println(Json.toJson(acc));
		System.out.println(Json.toJson(list));
		Record rcd = new Record();
		rcd.set("pno", pageIndex);
		rcd.set("total", pager.getPageCount());
		rcd.set("totalRecords", pager.getRecordCount());
		rcd.set("data", list);
		rcd.set("storeedit", true);
		rcd.set("storedelete", true);
		rcd.set("issuperadmin", true);
		return rcd;
	}

	@At
	@Ok("json")
	public Object GetSelectedTags(@Attr(scope = Scope.SESSION, value = "account") Account acc, int id) {
		// {"msg":[{"id":141,"tag_name":"00","checked":false}],"result":"Ok"}

		return null;

	}
	// storeId:
	// tagId:0
	// tagName:wqewqeqw
	// {"msg":{"id":152,"tag_name":"wqewqeqw","manager_id":38541,"created_at":"\/Date(1451566048121)\/","updated_at":"\/Date(1451566048121)\/","deleted_at":null},"result":"Ok"}

}
