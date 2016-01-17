package org.happy.wx.handler;

import java.util.HashMap;
import java.util.Map;

import org.happy.wx.api.WxProxy;
import org.happy.wx.shorturl.ShortUrl;
import org.happy.wx.storager.TableIdStorager;
import org.nutz.dao.Dao;
import org.nutz.integration.zbus.ZBusProducer;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.handler.MessageHandlerAdapter;
import com.foxinmy.weixin4j.mp.event.ScanEventMessage;
import com.foxinmy.weixin4j.request.WeixinRequest;
import com.foxinmy.weixin4j.response.TextResponse;
import com.foxinmy.weixin4j.response.WeixinResponse;

@IocBean
public class ScanEvent extends MessageHandlerAdapter<ScanEventMessage> {
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
	public WeixinResponse doHandle0(WeixinRequest request, ScanEventMessage message) throws WeixinException {
//		if (message.getEventKey() != null) {
//			tableIdStorager.caching(message.getEventKey(), 60 * 5, message.getFromUserName());
//		}
//		String openid = tableIdStorager.lookup(message.getEventKey());
//		if (!StringUtil.isBlank(openid)) {
//			Article ac = new Article();
//			ac.setUrl(shortUrl.getTableUrl(message.getEventKey(), openid));
//			ac.setTitle("桌号");
//			ac.setPicUrl(shortUrl.getPicUrl(message.getEventKey()));
//			ac.setDesc("开始点餐");
//			NewsResponse ns = new NewsResponse(ac);
//			return ns;
//		}
		if (message.getEventType().equals("SCAN")) {
			String EventKey = message.getEventKey();
			int ek = 0;
			try {
				ek = Integer.parseInt(EventKey);
			} catch (Exception e) {
			}
			if (ek >= 9999 && ek <= 99999) {
				Map<String, Object> ret=new HashMap<String, Object>();
				ret.put("ek", ek);
				ret.put("openid", message.getFromUserName());
				loginProducer.async(ret);
				return new TextResponse("成功登陆");
			}
		}
		return null;
	}

}
