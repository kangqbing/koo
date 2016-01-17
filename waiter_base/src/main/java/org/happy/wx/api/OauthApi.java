package org.happy.wx.api;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.model.WeixinAccount;

@IocBean(create = "init")
public class OauthApi extends com.foxinmy.weixin4j.mp.api.OauthApi {
	@Inject
	private WeixinAccount weixinAccount;
	public WeixinAccount DEFAULT_WEIXIN_ACCOUNT;
	public void init(){
		DEFAULT_WEIXIN_ACCOUNT=weixinAccount;
	}
}
