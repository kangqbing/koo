package org.happy.admin.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("m_goodinfo")
public class GoodInfo  extends BaseObject {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	public int id;
	@Column
	public int type_id;
	@Column
	public int manager_id;
	
	@Column
	public int accountid;
	
	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	@Column
	public int store_id;
	
	@Column
	public String unicode;
	
	@Column
	public String name;
	@Column
	public int sku_id;
	
	@Column
	public double price;
	
	
	@Column
	public String description;
	
	
	@Column
	public String image_url;
	
	
	@Column
	public int is_opened;
	
	
	@Column
	public String is_examined;
	
	@Column
	public int is_recommend;
	
	
	@Column
	public String is_index;
	
	@Column
	public String sort;
	
	@Column
	public String from_user_id;
	
	@Column
	public String status;
	
	@Column
	public Date created_at;
	@Column
	public Date updated_at;
	@Column
	public Date deleted_at;
	
	@Column
	public int is_sold_out;
	
	@Column
	public String stock;
	
	@Column
	public int is_use_stock;
	
	
	@Column
	public String is_special;
	
	@Column
	public int number;
	
	@Column
	public String short_code;
	
	@Column
	public String tag;
	
	@Column
	public String is_in_eat;
	
	@Column
	public String unit;
	
	@Column
	public String good_code;
	
	@Column
	public String by_order_or_people;
	
	@Column
	public boolean required;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public String getUnicode() {
		return unicode;
	}

	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSku_id() {
		return sku_id;
	}

	public void setSku_id(int sku_id) {
		this.sku_id = sku_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public int getIs_opened() {
		return is_opened;
	}

	public void setIs_opened(int is_opened) {
		this.is_opened = is_opened;
	}

	public String getIs_examined() {
		return is_examined;
	}

	public void setIs_examined(String is_examined) {
		this.is_examined = is_examined;
	}

	public int getIs_recommend() {
		return is_recommend;
	}

	public void setIs_recommend(int is_recommend) {
		this.is_recommend = is_recommend;
	}

	public String getIs_index() {
		return is_index;
	}

	public void setIs_index(String is_index) {
		this.is_index = is_index;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getFrom_user_id() {
		return from_user_id;
	}

	public void setFrom_user_id(String from_user_id) {
		this.from_user_id = from_user_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Date getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	public int getIs_sold_out() {
		return is_sold_out;
	}

	public void setIs_sold_out(int is_sold_out) {
		this.is_sold_out = is_sold_out;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public int getIs_use_stock() {
		return is_use_stock;
	}

	public void setIs_use_stock(int is_use_stock) {
		this.is_use_stock = is_use_stock;
	}

	public String getIs_special() {
		return is_special;
	}

	public void setIs_special(String is_special) {
		this.is_special = is_special;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getShort_code() {
		return short_code;
	}

	public void setShort_code(String short_code) {
		this.short_code = short_code;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getIs_in_eat() {
		return is_in_eat;
	}

	public void setIs_in_eat(String is_in_eat) {
		this.is_in_eat = is_in_eat;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getGood_code() {
		return good_code;
	}

	public void setGood_code(String good_code) {
		this.good_code = good_code;
	}

	public String getBy_order_or_people() {
		return by_order_or_people;
	}

	public void setBy_order_or_people(String by_order_or_people) {
		this.by_order_or_people = by_order_or_people;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

}
