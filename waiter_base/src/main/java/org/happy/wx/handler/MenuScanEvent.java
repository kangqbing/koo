package org.happy.wx.handler;

import org.happy.wx.api.WxProxy;
import org.happy.wx.shorturl.ShortUrl;
import org.happy.wx.storager.TableIdStorager;
import org.nutz.dao.Dao;
import org.nutz.integration.zbus.ZBusProducer;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.handler.MessageHandlerAdapter;
import com.foxinmy.weixin4j.message.event.MenuScanEventMessage;
import com.foxinmy.weixin4j.request.WeixinRequest;
import com.foxinmy.weixin4j.response.NewsResponse;
import com.foxinmy.weixin4j.response.WeixinResponse;
import com.foxinmy.weixin4j.response.NewsResponse.Article;
import com.foxinmy.weixin4j.util.StringUtil;

@IocBean
public class MenuScanEvent extends MessageHandlerAdapter<MenuScanEventMessage> {
	@Inject
	Dao dao;

	@Inject
	WxProxy api;

	@Inject
	ShortUrl shortUrl;

	@Inject
	TableIdStorager tableIdStorager;

	@Inject("java:$zbus.getProducer('login')")
	protected ZBusProducer loginProducer;

	@Override
	public WeixinResponse doHandle0(WeixinRequest request, MenuScanEventMessage message) throws WeixinException {
		if (message.getEventKey() != null) {
			tableIdStorager.caching(message.getEventKey(), 60 * 5, message.getFromUserName());
		}
		String openid = tableIdStorager.lookup(message.getEventKey());
		if (!StringUtil.isBlank(openid)) {
			Article ac = new Article();
			ac.setUrl(shortUrl.getTableUrl(message.getEventKey(), openid));
			ac.setTitle("桌号");
			ac.setPicUrl(shortUrl.getPicUrl(message.getEventKey()));
			ac.setDesc("开始点餐");
			NewsResponse ns = new NewsResponse(ac);
			return ns;
		}
		
		
		
		return null;
	}

}
