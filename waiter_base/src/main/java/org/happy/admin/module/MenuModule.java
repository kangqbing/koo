package org.happy.admin.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.happy.admin.model.Account;
import org.happy.admin.model.Attri;
import org.happy.admin.model.Attribute;
import org.happy.admin.model.GoodInfo;
import org.happy.admin.model.Store;
import org.happy.admin.model.StoreType;
import org.happy.admin.model.TypeList;
import org.nutz.castor.Castors;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.util.Context;
import org.nutz.mvc.Scope;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

@IocBean
@At("/admin/Menu")
@RequiresAuthentication
public class MenuModule {
	@At("/Menu")
	@Ok("beetl:admin/menu/menu.html")
	public Context menu(@Attr(scope = Scope.SESSION, value = "account") Account acc) {
		return Lang.context().set("storelist", dao.query(Store.class, Cnd.where("accountid", "=", acc.accountid)))
				.set("typelist", dao.query(TypeList.class, Cnd.where("accountid", "=", acc.accountid)));

	}

	@At("/Attribute")
	@Ok("beetl:admin/menu/attribute.html")
	public void Attribute() {

	}

	@At("/MenuList")
	@Ok("beetl:admin/menu/menulist.html")
	public Context MenuList(@Attr(scope = Scope.SESSION, value = "account") Account acc) {
		return Lang.context().set("storelist", dao.query(Store.class, Cnd.where("accountid", "=", acc.accountid)));
	}

	@At("/MenuPackage")
	@Ok("beetl:admin/menu/menupackage.html")
	public void MenuPackage() {

	}

	// SelectStoreByTypeId

	@At("/MenuAddPackage")
	@Ok("beetl:admin/menu/menuaddpackage.html")
	public Context MenuAddPackage(@Attr(scope = Scope.SESSION, value = "account") Account acc) {
		return Lang.context().set("typelist", dao.query(TypeList.class, Cnd.where("accountid", "=", acc.accountid)));

	}

	@At
	@Ok("beetl:admin/menu/MenuFoodAddGoodInfo.html")
	public Context MenuFoodAddGoodInfo(@Attr(scope = Scope.SESSION, value = "account") Account acc) {
		return Lang.context().set("storelist", dao.query(Store.class, Cnd.where("accountid", "=", acc.accountid)))
				.set("typelist", dao.query(TypeList.class, Cnd.where("accountid", "=", acc.accountid)));

	}

	// "{\"result\":\"Unknown\",\"msg\":\"添加失败，未添加菜品！\"}"
	// goodcode: ""
	// name: "威威企鹅"
	// price: "111"
	// url: "/wx/admin/KindEditor/img?id=8"
	// goodinfoType: "1"
	// storeList: [2, 3]
	@SuppressWarnings("null")
	@At
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public Record MenuFoodAddGoodInfo_POST(@Attr(scope = Scope.SESSION, value = "account") Account acc,
			@Param("..") Map<?, ?> mp) {
		Record ok = new Record();

		List<?> goodinfoList = (List<?>) mp.get("goodinfoList");
		@SuppressWarnings("unchecked")
		List<Integer> storeList = (List<Integer>) mp.get("storeList");
		if(storeList.size()==1){
			Integer storeid=storeList.get(0);
			if(storeid==0){
				List<Store> ls=dao.query(Store.class, Cnd.where("accountid", "=", acc.accountid));
				storeList=new ArrayList<Integer>();
				for (int i = 0; i < ls.size(); i++) {
					storeList.add(ls.get(i).id);
				}
			}
		}
		
		if (goodinfoList == null && goodinfoList.size() == 0) {
			ok.set("result", "Unknown");
			ok.set("msg", "添加失败，未添加菜品！");
		} else {
			for (int i = 0; i < goodinfoList.size(); i++) {
				Map<?, ?> info = (Map<?, ?>) goodinfoList.get(i);
				for (int j = 0; j < storeList.size(); j++) {
					GoodInfo goodinfo = new GoodInfo();
					goodinfo.setImage_url((String) info.get("url"));
					goodinfo.setGood_code((String) info.get("goodcode"));
					goodinfo.setName((String) info.get("name"));
					goodinfo.setPrice(Double.parseDouble((String) info.get("price")));
					goodinfo.setType_id(Integer.parseInt((String) mp.get("goodinfoType")));
					goodinfo.setStore_id(storeList.get(j));
					goodinfo.setManager_id(acc.managerid);
					goodinfo.setAccountid(acc.accountid);
					goodinfo.setCreated_at(new Date());
					dao.insert(goodinfo);
				}
			}
			ok.set("result", "Ok");
			ok.set("msg", "添加菜品成功！");
		}
		return ok;
	}

	@At
	@Ok("beetl:admin/menu/MenuFoodEdit.html")
	public Context MenuFoodEdit(int id, @Attr(scope = Scope.SESSION, value = "account") Account acc) {
		Attribute attribute = dao.fetch(Attribute.class, Cnd.where("accountid", "=", acc.accountid));
		dao.fetchLinks(attribute, "tastelist");
		dao.fetchLinks(attribute, "sizelist");

		return Lang.context().set("attri", attribute)
				.set("storelist", dao.query(Store.class, Cnd.where("accountid", "=", acc.accountid)))
				.set("typelist", dao.query(TypeList.class, Cnd.where("accountid", "=", acc.accountid)));
	}
	
	
	
	@At
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public Record MenuFoodDel(@Attr(scope = Scope.SESSION, value = "account") Account acc,
			@Param("..") Map<?, ?> mp){
		dao.clear(GoodInfo.class, Cnd.where("id", "=", mp.get("id")));
		Record ok = new Record();
		ok.set("result", "Ok");
		ok.set("msg", "删除成功！");
		return ok;
	}
	
	

	@At
	@Ok("json")
	public Record SelectStoreByTypeId(int typeId) {
		Cnd cnd = Cnd.where("id", "=", typeId);
		TypeList ls = dao.fetch(TypeList.class, cnd);
		Record ok = new Record();
		ok.set("result", "Ok");
		ok.set("msg", ls.store_id);
		return ok;
	}

	@At
	@Ok("json")
	public Record SelectGoodType(int storeId) {

		Cnd cnd = Cnd.where("1", "=", 1);
		if (storeId == 0) {
			cnd.and("store_id", "=", storeId);
		} else {
			cnd.and("store_id", "<>", storeId);
		}
		List<TypeList> ls = dao.query(TypeList.class, cnd);
		Record ok = new Record();
		ok.set("result", "Ok");
		ok.set("msg", ls);
		return ok;
	}

	@At
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public Record MenuFoodEdit_POST(@Attr(scope = Scope.SESSION, value = "account") Account acc,
			@Param("..") Map<?, ?> mp) {

		System.err.println(mp);

		Record ok = new Record();
		ok.set("result", "Ok");
		ok.set("msg", "添加成功！");
		return ok;
	}

	@SuppressWarnings("unchecked")
	@At
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public Record AttributeAdd(@Attr(scope = Scope.SESSION, value = "account") Account acc, @Param("..") Map<?, ?> mp) {
		System.out.println(mp);
		boolean isnew = false;
		boolean issizelist = false;
		boolean istastelist = false;
		Attribute attribute = dao.fetch(Attribute.class, Cnd.where("accountid", "=", acc.accountid));
		if (attribute == null) {
			isnew = true;
			attribute = new Attribute();
			attribute.setAccountid(acc.accountid);
		}
		if (!mp.get("strSize").equals("[]")) {
			if (!isnew)
				dao.clearLinks(attribute, "sizelist");
			attribute.setStrSize(Json.toJson(mp.get("strSize")));
			List<String> ls = (List<String>) mp.get("strSize");
			List<Attri> sizelist = new ArrayList<Attri>();
			for (int i = 0; i < ls.size(); i++) {
				Attri atr = dao.fetch(Attri.class, Cnd.where("name", "=", ls.get(i)));
				if (atr == null) {
					atr = new Attri();
					atr.setName(ls.get(i));
					dao.insert(atr);
				}
				sizelist.add(atr);
			}
			attribute.setSizelist(sizelist);
			issizelist = true;
		}
		if (!mp.get("strTaste").equals("[]")) {
			if (!isnew)
				dao.clearLinks(attribute, "tastelist");
			attribute.setStrTaste(Json.toJson(mp.get("strTaste")));
			List<String> ls = (List<String>) mp.get("strTaste");
			List<Attri> tastelist = new ArrayList<Attri>();
			for (int i = 0; i < ls.size(); i++) {
				Attri atr = dao.fetch(Attri.class, Cnd.where("name", "=", ls.get(i)));
				if (atr == null) {
					atr = new Attri();
					atr.setName(ls.get(i));
					dao.insert(atr);
				}
				tastelist.add(atr);
			}
			attribute.setTastelist(tastelist);
			istastelist = true;
		}
		if (isnew) {
			dao.insert(attribute);
			if (istastelist)
				dao.insertRelation(attribute, "tastelist");
			if (issizelist)
				dao.insertRelation(attribute, "sizelist");
		} else {
			dao.update(attribute);
			if (istastelist)
				dao.insertRelation(attribute, "tastelist");
			if (issizelist)
				dao.insertRelation(attribute, "sizelist");
		}
		Record ok = new Record();
		ok.set("result", "Ok");
		ok.set("msg", "添加成功！");
		return ok;

	}

	@At
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public Record MenuListAdd(@Attr(scope = Scope.SESSION, value = "account") Account acc, @Param("..") Map<?, ?> mp) {
		@SuppressWarnings("unchecked")
		List<Integer> storeList = (List<Integer>) mp.get("storeList");
		for (int i = 0; i < storeList.size(); i++) {
			TypeList tp = new TypeList();
			tp.setStore_id(storeList.get(i));
			tp.setName((String) mp.get("typeName"));
			tp.setAccountid(acc.accountid);
			tp.setManagerid(acc.managerid);
			tp.setStoreName(storeList.get(i) == 0 ? "所有门店" : dao.fetch(Store.class, storeList.get(i)).getName());
			try {
				tp.setSort((Integer) mp.get("typeSort"));
			} catch (Exception e) {
			}
			dao.insert(tp);
		}
		Record ok = new Record();
		ok.set("result", "Ok");
		ok.set("msg", "添加成功！");
		return ok;
	}

	@At
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public Record MenuListDel(@Attr(scope = Scope.SESSION, value = "account") Account acc, @Param("..") Map<?, ?> mp) {
		dao.clear(TypeList.class, Cnd.where("id", "=", mp.get("typeid")));
		Record ok = new Record();
		ok.set("result", "Ok");
		ok.set("msg", "删除成功！");
		return ok;
	}

	@At
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public Record MenuListUpdate(@Attr(scope = Scope.SESSION, value = "account") Account acc,
			@Param("..") Map<?, ?> mp) {
		Record ok = new Record();
		TypeList tp = dao.fetch(TypeList.class, Cnd.where("id", "=", mp.get("typeid")));
		tp.setName((String) mp.get("typeName"));
		tp.setAccountid(acc.accountid);
		tp.setManagerid(acc.managerid);
		tp.setStore_id(Integer.parseInt((String) mp.get("storeId")));
		tp.setStoreName(mp.get("storeId").equals("0") ? "所有门店"
				: dao.fetch(Store.class, Cnd.where("id", "=", mp.get("storeId"))).getName());
		try {
			tp.setSort((Integer) mp.get("typeSort"));
		} catch (Exception e) {
		}

		dao.update(tp);

		ok.set("result", "Ok");
		ok.set("msg", "修改成功！");
		return ok;
	}

	@At
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public Record AttributeEdit(@Attr(scope = Scope.SESSION, value = "account") Account acc,
			@Param("..") Map<?, ?> mp) {
		System.out.println(mp);
		Attribute ab = dao.fetch(Attribute.class, Cnd.where("accountid", "=", acc.accountid));
		boolean issizelist = false;
		boolean istastelist = false;

		if (mp.get("id").equals("2")) {
			dao.clearLinks(ab, "sizelist");
			ab.setStrSize(Json.toJson(mp.get("attr")));
			@SuppressWarnings("unchecked")
			List<String> ls = (List<String>) mp.get("attr");
			List<Attri> sizelist = new ArrayList<Attri>();
			for (int i = 0; i < ls.size(); i++) {
				Attri atr = dao.fetch(Attri.class, Cnd.where("name", "=", ls.get(i)));
				if (atr == null) {
					atr = new Attri();
					atr.setName(ls.get(i));
					dao.insert(atr);
				}
				sizelist.add(atr);
			}
			ab.setSizelist(sizelist);
			issizelist = true;
		} else if (mp.get("id").equals("1")) {
			dao.clearLinks(ab, "tastelist");
			ab.setStrTaste(Json.toJson(mp.get("attr")));
			@SuppressWarnings("unchecked")
			List<String> ls = (List<String>) mp.get("attr");
			List<Attri> tastelist = new ArrayList<Attri>();
			for (int i = 0; i < ls.size(); i++) {
				Attri atr = dao.fetch(Attri.class, Cnd.where("name", "=", ls.get(i)));
				if (atr == null) {
					atr = new Attri();
					atr.setName(ls.get(i));
					dao.insert(atr);
				}
				tastelist.add(atr);
			}
			ab.setTastelist(tastelist);
			istastelist = true;
		}

		dao.update(ab);
		if (istastelist)
			dao.insertRelation(ab, "tastelist");
		if (issizelist)
			dao.insertRelation(ab, "sizelist");
		Record ok = new Record();
		ok.set("result", "Ok");
		ok.set("msg", "添加成功！");
		return ok;

	}

	@At
	@Ok("json")
	public Record MenuAttributeDel(int id, @Attr(scope = Scope.SESSION, value = "account") Account acc) {
		Attribute ab = dao.fetch(Attribute.class, Cnd.where("accountid", "=", acc.accountid));
		if (id == 1) {
			ab.setStrTaste(null);
			dao.clearLinks(ab, "tastelist");
		}
		if (id == 2) {
			ab.setStrSize(null);
			dao.clearLinks(ab, "sizelist");
		}
		dao.update(ab);
		Record ok = new Record();
		ok.set("result", "Ok");
		ok.set("msg", "删除成功！");
		return ok;
	}

	@At
	@Ok("json")
	public Record SearchAttribute(@Attr(scope = Scope.SESSION, value = "account") Account acc) {
		Record ok = new Record();
		ok.set("result", "Ok");
		Record ret = new Record();
		Attribute attribute = dao.fetch(Attribute.class, Cnd.where("accountid", "=", acc.accountid));
		ret.set("tasteList", attribute.strTaste == null ? Json.fromJson("[]") : Json.fromJson(attribute.strTaste));
		ret.set("sizeList", attribute.strSize == null ? Json.fromJson("[]") : Json.fromJson(attribute.strSize));
		ok.set("msg", ret);
		return ok;
	}

	@At
	@Ok("json")
	public Record MenuList_List(@Attr(scope = Scope.SESSION, value = "account") Account acc, int pageIndex,
			int storeId) {
		Record ok = new Record();
		ok.set("result", "Ok");
		Pager pager = dao.createPager(pageIndex, 20);
		Cnd cnd = Cnd.where("1", "=", "1");
		if (storeId != 0) {
			cnd.and("store_id", "=", storeId);
		}
		List<TypeList> list = dao.query(TypeList.class, cnd, pager);
		pager.setRecordCount(dao.count(TypeList.class, cnd));

		Record ret = new Record();
		ret.set("pno", pageIndex);
		ret.set("total", pager.getPageCount());
		ret.set("totalRecords", pager.getRecordCount());
		ret.set("isSuperAdmin", true);

		List<Record> typeList = new ArrayList<Record>();
		for (int i = 0; i < list.size(); i++) {
			Record retx = Castors.me().castTo(list.get(i), Record.class);
			retx.set("isEditMenuFood", true);
			retx.set("isDelMenuFood", true);
			typeList.add(retx);
		}
		ret.set("typeList", typeList);
		ok.set("msg", ret);
		return ok;
	}

	@Inject
	Dao dao;

	@At("/SelectMenu")
	@Ok("json")
	public Record SelectMenu(@Attr(scope = Scope.SESSION, value = "account") Account acc, int pageIndex, int storeId,
			int goodinfoType, String name) {

		Pager pager = dao.createPager(pageIndex, 20);
		Cnd cnd = Cnd.where("1", "=", "1");
		if (storeId != 0) {
			cnd.and("store_id", "=", storeId);
		}
		if (goodinfoType != 0) {
			cnd.and("type_id", "=", goodinfoType);
		}
		if (!Lang.isEmpty(name)) {
			cnd.and("name", " like ", "%" + name + "%");
		}
		List<GoodInfo> list = dao.query(GoodInfo.class, cnd, pager);
		pager.setRecordCount(dao.count(GoodInfo.class, cnd));
		Record ok = new Record();
		ok.set("result", "Ok");

		Record ret = new Record();
		ret.set("pno", pageIndex);
		ret.set("total", pager.getPageCount());
		ret.set("totalRecords", pager.getRecordCount());
		ret.set("isSuperAdmin", true);
		List<Record> goodinfoList = new ArrayList<Record>();
		for (int i = 0; i < list.size(); i++) {
			Record retx = new Record();
			retx.put("goodinfo", list.get(i));
			retx.put("goodstock", null);
			retx.put("isEditMenuFood", true);
			retx.put("isDelMenuFood", true);
			retx.put("isCloud", 0);
			retx.put("store", dao.fetch(Store.class, list.get(i).store_id).getName());
			retx.put("type", dao.fetch(StoreType.class, list.get(i).type_id).getName());
			goodinfoList.add(retx);
		}
		ret.set("goodinfoList", goodinfoList);
		ok.set("msg", ret);

		return ok;
	}
}
