package org.happy.wx.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.happy.wx.Service.TableService;
import org.happy.wx.api.WxApi;
import org.happy.wx.api.WxProxy;
import org.happy.wx.model.Wx_User;
import org.happy.wx.shorturl.ShortUrl;
import org.happy.wx.storager.TableIdStorager;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.integration.zbus.ZBusProducer;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.handler.MessageHandlerAdapter;
import com.foxinmy.weixin4j.mp.event.ScribeEventMessage;
import com.foxinmy.weixin4j.mp.model.User;
import com.foxinmy.weixin4j.request.WeixinRequest;
import com.foxinmy.weixin4j.response.NewsResponse;
import com.foxinmy.weixin4j.response.NewsResponse.Article;
import com.foxinmy.weixin4j.response.TextResponse;
import com.foxinmy.weixin4j.response.WeixinResponse;
import com.foxinmy.weixin4j.type.EventType;
import com.foxinmy.weixin4j.util.ClassUtil;
import com.foxinmy.weixin4j.util.StringUtil;

@IocBean(create = "init")
public class ScribeEvent_Mp extends MessageHandlerAdapter<ScribeEventMessage> {

	@Inject
	WxApi wxApi;

	@Inject
	Dao dao;

	@Inject
	WxProxy api;
	@Inject
	ShortUrl shortUrl;
	@Inject
	TableIdStorager tableIdStorager;
	@Inject
	TableService tableService;

	@Inject("java:$zbus.getProducer('login')")
	protected ZBusProducer loginProducer;

	@Override
	public WeixinResponse doHandle0(WeixinRequest request, ScribeEventMessage message) throws WeixinException {
		if (message.getEventType().equals(EventType.subscribe.name())) {
			User user = api.getUser(message.getFromUserName());
			String tableid = null;
			if (user != null) {
				Daos.createTablesInPackage(dao, Wx_User.class.getPackage().getName(), false);
				Wx_User t = dao.fetch(Wx_User.class, Cnd.where("openId", "=", message.getFromUserName()));
				if (t == null) {
					t = new Wx_User();
					try {
						BeanUtils.copyProperties(t, user);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					dao.insert(t);
				}
				if (message.getEventKey() != null && message.getEventKey().startsWith("qrscene_")) {
					tableid = message.getEventKey().replace("qrscene_", "");
					tableIdStorager.caching(tableid, 60 * 5, t.getOpenId());
					int ek = Integer.parseInt(tableid);
					if (ek >= 9999 && ek <= 99999) {
						Map<String, Object> ret=new HashMap<String, Object>();
						ret.put("ek", ek);
						ret.put("openid", message.getFromUserName());
						loginProducer.async(ret);
						
						
						
						
						
						
						return new TextResponse("成功登陆");
					} else {
						String OpenId = tableIdStorager.lookup(tableid);
						if (!StringUtil.isBlank(OpenId)) {
							Article ac = new Article();
							ac.setUrl(shortUrl.getTableUrl(tableid, OpenId));
							ac.setTitle("桌号");
							ac.setPicUrl(shortUrl.getPicUrl(tableid));
							ac.setDesc("开始点餐");
							NewsResponse ns = new NewsResponse(ac);
							return ns;
						}
					}
				}
				return new TextResponse(t.getNickName() + "童鞋，欢迎你的光临");
			}
		} else if (message.getEventType().equals(EventType.unsubscribe.name())) {
		}
		return null;
	}

	public void init() {
		System.err.println(ClassUtil.getGenericType(this));
	}
}
