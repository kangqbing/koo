package org.happy.zbus;

import java.io.IOException;

import org.nutz.integration.zbus.annotation.ZBusConsumer;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.zbus.net.core.Session;
import org.zbus.net.http.Message;
import org.zbus.net.http.Message.MessageHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;

@ZBusConsumer(mq = "login")
@IocBean
public class LoginBus implements MessageHandler {

	@Inject
	LoginTokenStorager loginTokenStorager;
	@Override
	public void handle(Message msg, Session sess) throws IOException {
		JSONObject eventMessage = JSON.parseObject(msg.getBodyString());
		System.err.println(Json.toJson(eventMessage));
		try {
			Token tk = loginTokenStorager.lookup(eventMessage.getString("ek"));
			if (tk != null && !tk.getAccessToken().equals(eventMessage.getString("openid"))) {
				tk = new Token();
				tk.setTime(-1);
				tk.setAccessToken(eventMessage.getString("openid"));
				loginTokenStorager.caching(eventMessage.getString("ek"), tk);
			} else {
				tk = new Token();
				tk.setTime(-1);
				tk.setAccessToken(eventMessage.getString("openid"));
				loginTokenStorager.caching(eventMessage.getString("ek"), tk);
			}
		} catch (WeixinException e) {
			e.printStackTrace();
		}
	}

}
