package org.happy.wx.handler;

import org.happy.wx.api.OauthApi;
import org.happy.wx.shorturl.ShortUrl;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.handler.MessageHandlerAdapter;
import com.foxinmy.weixin4j.message.TextMessage;
import com.foxinmy.weixin4j.request.WeixinRequest;
import com.foxinmy.weixin4j.response.TextResponse;
import com.foxinmy.weixin4j.response.WeixinResponse;

@IocBean
public class Text extends MessageHandlerAdapter<TextMessage> {
	@Inject
	OauthApi oauthApi;
	@Inject
	ShortUrl shortUrl;

	@Override
	public WeixinResponse doHandle0(WeixinRequest request, TextMessage message) throws WeixinException {
		System.out.println(message);
		String url = oauthApi.getAuthorizeURL(oauthApi.DEFAULT_WEIXIN_ACCOUNT.getId(),
				"http://kangqbing.ngrok.wendal.cn/wx/api/wo", "state", new String[] { "snsapi_base" });
		return new TextResponse(shortUrl.shorturl(url).getUrl_short());
	}

}
