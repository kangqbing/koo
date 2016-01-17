package org.happy.admin.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("m_store")
public class Store extends BaseObject {

	private static final long serialVersionUID = 1L;

	@Id
	public int id;
	@Column
	public int type_id;
	@Column
	public int managerid;
	
	@Column
	public int accountid;
	@Column
	public String name;
	@Column
	public String nickname;
	@Column
	public String phone;
	@Column
	public String printinfo;
	@Column
	public Date order_time;
	@Column
	public String avatar;
	@Column
	public String openid;
	@Column
	public String less_money;
	@Column
	public String delivery_charge;
	@Column
	public String radius;
	@Column
	public String price_type;
	@Column
	public String msg_id;
	@Column
	public int is_open_pay;
	@Column
	public String qrcode_id;
	@Column
	public String is_show_home;
	@Column
	public String is_show_img;
	@Column
	public int address_id;

	@Column
	public String site_info;
	@Column
	public Date created_at;
	@Column
	public Date updated_at;
	@Column
	public Date deleted_at;
	@Column
	public String business_hours_start;
	@Column
	public String business_hours_end;
	@Column
	public String business_hours_start2;
	@Column
	public String business_hours_end2;
	@Column
	public String discount;
	@Column
	public String is_invoice;
	@Column
	public String earnest_percent;
	@Column
	public boolean is_payment_after;
	@Column
	public boolean ineat_mode;

	@Column
	public String show_type;
	@Column
	public boolean open_phone_valid;
	@Column
	public int pay_mode;
	@Column
	public String store_discount_activity;
	@Column
	public String store_introduce;
	@Column
	public String tags;
	@Column
	public String status;
	@Column
	public String store_notice;
	@Column
	public String delivery_time;
	@Column
	public String delivery_interval;

	public int getId() {
		return id;
	}

	public int getType_id() {
		return type_id;
	}


	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPhone() {
		return phone;
	}

	public String getPrintinfo() {
		return printinfo;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getOpenid() {
		return openid;
	}

	public String getLess_money() {
		return less_money;
	}

	public String getDelivery_charge() {
		return delivery_charge;
	}

	public String getRadius() {
		return radius;
	}

	public String getPrice_type() {
		return price_type;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public int getIs_open_pay() {
		return is_open_pay;
	}

	public String getQrcode_id() {
		return qrcode_id;
	}

	public String getIs_show_home() {
		return is_show_home;
	}

	public String getIs_show_img() {
		return is_show_img;
	}

	public int getAddress_id() {
		return address_id;
	}

	public String getSite_info() {
		return site_info;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public Date getDeleted_at() {
		return deleted_at;
	}

	public String getBusiness_hours_start() {
		return business_hours_start;
	}

	public String getBusiness_hours_end() {
		return business_hours_end;
	}

	public String getBusiness_hours_start2() {
		return business_hours_start2;
	}

	public String getBusiness_hours_end2() {
		return business_hours_end2;
	}

	public String getDiscount() {
		return discount;
	}

	public String getIs_invoice() {
		return is_invoice;
	}

	public String getEarnest_percent() {
		return earnest_percent;
	}

	public boolean isIs_payment_after() {
		return is_payment_after;
	}

	public boolean isIneat_mode() {
		return ineat_mode;
	}

	public String getShow_type() {
		return show_type;
	}

	public boolean isOpen_phone_valid() {
		return open_phone_valid;
	}

	public int getPay_mode() {
		return pay_mode;
	}

	public String getStore_discount_activity() {
		return store_discount_activity;
	}

	public String getStore_introduce() {
		return store_introduce;
	}

	public String getTags() {
		return tags;
	}

	public String getStatus() {
		return status;
	}

	public String getStore_notice() {
		return store_notice;
	}

	public String getDelivery_time() {
		return delivery_time;
	}

	public String getDelivery_interval() {
		return delivery_interval;
	}

	public boolean isIs_ineat_open_pay() {
		return is_ineat_open_pay;
	}

	public boolean isIs_ineat_payment_after() {
		return is_ineat_payment_after;
	}

	public boolean isIs_bankcard_pay() {
		return is_bankcard_pay;
	}

	public boolean isIs_allow_cloud() {
		return is_allow_cloud;
	}

	public boolean isIs_paused() {
		return is_paused;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}


	public int getManagerid() {
		return managerid;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPrintinfo(String printinfo) {
		this.printinfo = printinfo;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public void setLess_money(String less_money) {
		this.less_money = less_money;
	}

	public void setDelivery_charge(String delivery_charge) {
		this.delivery_charge = delivery_charge;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public void setIs_open_pay(int is_open_pay) {
		this.is_open_pay = is_open_pay;
	}

	public void setQrcode_id(String qrcode_id) {
		this.qrcode_id = qrcode_id;
	}

	public void setIs_show_home(String is_show_home) {
		this.is_show_home = is_show_home;
	}

	public void setIs_show_img(String is_show_img) {
		this.is_show_img = is_show_img;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public void setSite_info(String site_info) {
		this.site_info = site_info;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	public void setBusiness_hours_start(String business_hours_start) {
		this.business_hours_start = business_hours_start;
	}

	public void setBusiness_hours_end(String business_hours_end) {
		this.business_hours_end = business_hours_end;
	}

	public void setBusiness_hours_start2(String business_hours_start2) {
		this.business_hours_start2 = business_hours_start2;
	}

	public void setBusiness_hours_end2(String business_hours_end2) {
		this.business_hours_end2 = business_hours_end2;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public void setIs_invoice(String is_invoice) {
		this.is_invoice = is_invoice;
	}

	public void setEarnest_percent(String earnest_percent) {
		this.earnest_percent = earnest_percent;
	}

	public void setIs_payment_after(boolean is_payment_after) {
		this.is_payment_after = is_payment_after;
	}

	public void setIneat_mode(boolean ineat_mode) {
		this.ineat_mode = ineat_mode;
	}

	public void setShow_type(String show_type) {
		this.show_type = show_type;
	}

	public void setOpen_phone_valid(boolean open_phone_valid) {
		this.open_phone_valid = open_phone_valid;
	}

	public void setPay_mode(int pay_mode) {
		this.pay_mode = pay_mode;
	}

	public void setStore_discount_activity(String store_discount_activity) {
		this.store_discount_activity = store_discount_activity;
	}

	public void setStore_introduce(String store_introduce) {
		this.store_introduce = store_introduce;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStore_notice(String store_notice) {
		this.store_notice = store_notice;
	}

	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
	}

	public void setDelivery_interval(String delivery_interval) {
		this.delivery_interval = delivery_interval;
	}

	public void setIs_ineat_open_pay(boolean is_ineat_open_pay) {
		this.is_ineat_open_pay = is_ineat_open_pay;
	}

	public void setIs_ineat_payment_after(boolean is_ineat_payment_after) {
		this.is_ineat_payment_after = is_ineat_payment_after;
	}

	public void setIs_bankcard_pay(boolean is_bankcard_pay) {
		this.is_bankcard_pay = is_bankcard_pay;
	}

	public void setIs_allow_cloud(boolean is_allow_cloud) {
		this.is_allow_cloud = is_allow_cloud;
	}

	public void setIs_paused(boolean is_paused) {
		this.is_paused = is_paused;
	}

	@Column
	public boolean is_ineat_open_pay;
	@Column
	public boolean is_ineat_payment_after;
	@Column
	public boolean is_bankcard_pay;
	@Column
	public boolean is_allow_cloud;

	@Column
	public boolean is_paused;

}
