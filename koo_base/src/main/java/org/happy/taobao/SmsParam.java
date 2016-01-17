package org.happy.taobao;

import org.nutz.json.JsonIgnore;

public class SmsParam {
	public String getPhone() {
		return phone;
	}

	public SmsParam setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	@JsonIgnore
	private String phone;

	private String code;
	private String product;

	public String getCode() {
		return code;
	}

	public SmsParam setCode(String code) {
		this.code = code;
		return this;
	}

	public String getProduct() {
		return product;
	}

	public SmsParam setProduct(String product) {
		this.product = product;
		return this;
	}

}
