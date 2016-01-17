package org.happy.wx.handler;

import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.handler.MessageHandlerAdapter;
import com.foxinmy.weixin4j.qy.event.ScribeEventMessage;
import com.foxinmy.weixin4j.request.WeixinRequest;
import com.foxinmy.weixin4j.response.WeixinResponse;

@IocBean
public class ScribeEvent_Qy extends MessageHandlerAdapter<ScribeEventMessage>{

	@Override
	public WeixinResponse doHandle0(WeixinRequest request, ScribeEventMessage message) throws WeixinException {
		return null;
	}

}
