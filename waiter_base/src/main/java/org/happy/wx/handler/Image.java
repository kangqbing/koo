package org.happy.wx.handler;

import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.handler.MessageHandlerAdapter;
import com.foxinmy.weixin4j.message.ImageMessage;
import com.foxinmy.weixin4j.request.WeixinRequest;
import com.foxinmy.weixin4j.response.WeixinResponse;
@IocBean
public class Image extends MessageHandlerAdapter<ImageMessage>{

	@Override
	public WeixinResponse doHandle0(WeixinRequest request, ImageMessage message) throws WeixinException {
		return null;
	}

}
