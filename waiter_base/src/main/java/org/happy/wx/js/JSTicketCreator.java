package org.happy.wx.js;

import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.mp.token.WeixinJSTicketCreator;
import com.foxinmy.weixin4j.token.TokenHolder;

@IocBean(args={"java:$conf.get('wx.appid')","java:$wxApi.api.getTokenHolder()"})
public class JSTicketCreator extends WeixinJSTicketCreator{

	public JSTicketCreator(String appid, TokenHolder weixinTokenHolder) {
		super(appid, weixinTokenHolder);
	}
}
