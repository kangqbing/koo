package org.happy.taobao;

public enum SmsType {
	CARD("身份验证", "SMS_4730287"), 
	RIGER("注册验证", "SMS_4730284"), 
	LOGIN("登录验证", "SMS_4730286"), 
	LOGIN_ERR("登录验证","SMS_4730285"),
	CHANGE("变更验证", "SMS_4730281"), 
	PASSWD("变更验证", "SMS_4730282"), 
	ACTION("活动验证", "SMS_4730283");

	private String smsFreeSignName;
	private String smsTemplateCode;

	SmsType(String type, String tmp) {
		this.setSmsFreeSignName(type);
		this.setSmsTemplateCode(tmp);
	}

	public String getSmsTemplateCode() {
		return smsTemplateCode;
	}

	public void setSmsTemplateCode(String smsTemplateCode) {
		this.smsTemplateCode = smsTemplateCode;
	}

	public String getSmsFreeSignName() {
		return smsFreeSignName;
	}

	public void setSmsFreeSignName(String smsFreeSignName) {
		this.smsFreeSignName = smsFreeSignName;
	}

}
