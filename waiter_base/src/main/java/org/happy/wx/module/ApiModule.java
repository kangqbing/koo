package org.happy.wx.module;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.happy.wx.api.OauthApi;
import org.happy.wx.api.WxApi;
import org.happy.wx.api.WxProxy;
import org.happy.wx.js.JsTokenHolder;
import org.happy.wx.shorturl.ShortUrl;
import org.nutz.dao.Dao;
import org.nutz.img.Images;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.util.Context;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.RequestPath;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Button;
import com.foxinmy.weixin4j.mp.model.OauthToken;
import com.foxinmy.weixin4j.mp.model.QRParameter;
import com.foxinmy.weixin4j.mp.model.QRResult;
import com.foxinmy.weixin4j.mp.model.User;
import com.foxinmy.weixin4j.type.ButtonType;

@IocBean
@At("api")
public class ApiModule {

	@Inject
	Dao dao;
	@Inject
	WxApi wxApi;

	@Inject
	WxProxy api;
	@Inject
	ShortUrl shortUrl;

	@Inject
	OauthApi oauthApi;
	@Inject
	JsTokenHolder jsTokenHolder;

	@At
	@Ok("raw:png")
	public BufferedImage qr(int t) throws WeixinException {
		QRParameter qr = QRParameter.createTemporary(2592000, t);
		QRResult out = api.createQR(qr);
		if (out != null) {
			BufferedImage rt = Images.read(new ByteArrayInputStream(out.getContent()));
			return rt;
		}
		return null;
	}

	@At
	@Ok("raw")
	public QRResult qrstr(String t) throws WeixinException {
		QRParameter qr = QRParameter.createPermanenceStr(t);
		QRResult out = api.createQR(qr);
		return out;
	}

	
	
	
	@At
	@Ok("json")
	public Object menu() throws WeixinException {
		List<Button> btnList = new ArrayList<Button>();
		Button bnt = new Button("扫描下单", "scan_table", ButtonType.scancode_push);
		btnList.add(bnt);
		Button wo = new Button("我的", shortUrl.getWo(), ButtonType.view);
		btnList.add(wo);

		Button about = new Button("关于", shortUrl.getAbout(), ButtonType.view);
		btnList.add(about);

		return api.createMenu(btnList);
	}

	@At
	@Ok("beetl:wo.html")
	public Context wo(String code, HttpServletRequest request) throws WeixinException {
		RequestPath rp = Mvcs.getRequestPathObject(request);
		String url = "http://kangqbing.ngrok.wendal.cn/wx" + rp.getUrl() + "?" + request.getQueryString();
		OauthToken token = oauthApi.getOauthToken(code);
		User user = api.getUser(token.getOpenId());
		String JsSDKConfig = wxApi.JsSDKConfig(url);
		return Lang.context().set("user", user).set("JsSDKConfig", JsSDKConfig);
	}

	@At
	@Ok("beetl:about.html")
	public Context about(String code, HttpServletRequest request) throws WeixinException {
		RequestPath rp = Mvcs.getRequestPathObject(request);
		String url = "http://kangqbing.ngrok.wendal.cn/wx" + rp.getUrl() + "?" + request.getQueryString();
		OauthToken token = oauthApi.getOauthToken(code);
		User user = api.getUser(token.getOpenId());
		String JsSDKConfig = wxApi.JsSDKConfig(url);
		return Lang.context().set("user", user).set("JsSDKConfig", JsSDKConfig);
	}

	@At("/table/?/?")
	@Ok("beetl:table.html")
	public Context table(String tableid, String openid) throws WeixinException {
		return Lang.context().set("tableid", tableid).set("openid", openid);
	}

}
