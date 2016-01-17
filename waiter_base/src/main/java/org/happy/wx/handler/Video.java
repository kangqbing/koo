package org.happy.wx.handler;

import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.handler.MessageHandlerAdapter;
import com.foxinmy.weixin4j.message.VideoMessage;
import com.foxinmy.weixin4j.request.WeixinRequest;
import com.foxinmy.weixin4j.response.WeixinResponse;

@IocBean
public class Video extends MessageHandlerAdapter<VideoMessage>{

	@Override
	public WeixinResponse doHandle0(WeixinRequest request, VideoMessage message) throws WeixinException {
		// TODO Auto-generated method stub
		return null;
	}

}
