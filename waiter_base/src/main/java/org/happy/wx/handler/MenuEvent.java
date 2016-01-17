package org.happy.wx.handler;

import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.handler.MessageHandlerAdapter;
import com.foxinmy.weixin4j.message.event.MenuEventMessage;
import com.foxinmy.weixin4j.request.WeixinRequest;
import com.foxinmy.weixin4j.response.WeixinResponse;
@IocBean
public class MenuEvent extends MessageHandlerAdapter<MenuEventMessage>{
	@Override
	public WeixinResponse doHandle0(WeixinRequest request, MenuEventMessage message) throws WeixinException {
		System.out.println(message);
		return null;
	}
}
