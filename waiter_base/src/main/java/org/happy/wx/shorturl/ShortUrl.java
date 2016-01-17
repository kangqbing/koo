package org.happy.wx.shorturl;

import java.nio.charset.Charset;

import org.happy.wx.api.OauthApi;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import com.foxinmy.weixin4j.http.HttpClient;
import com.foxinmy.weixin4j.http.HttpClientException;
import com.foxinmy.weixin4j.http.HttpMethod;
import com.foxinmy.weixin4j.http.HttpRequest;
import com.foxinmy.weixin4j.http.HttpResponse;
import com.foxinmy.weixin4j.http.factory.HttpClientFactory;
import com.foxinmy.weixin4j.util.StringUtil;
import com.foxinmy.weixin4j.util.URLEncodingUtil;

@IocBean
public class ShortUrl {
	@Inject
	OauthApi oauthApi;
	String base = "http://api.t.sina.com.cn/short_url/shorten.json?source=%s&url_long=%s";
	private HttpClient httpClient = HttpClientFactory.getInstance();

	public ShortResult shorturl(String url) {
		url = URLEncodingUtil.encoding(url, Charset.forName("UTF-8"), false);
		url = String.format(base, "1681459862", url);
		HttpRequest request = new HttpRequest(HttpMethod.GET, url);
		try {
			HttpResponse response = httpClient.execute(request);
			String s = StringUtil.newStringUtf8(response.getContent());
			return Json.fromJsonAsList(ShortResult.class, s).get(0);
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getWo() {
		String url = oauthApi.getAuthorizeURL(oauthApi.DEFAULT_WEIXIN_ACCOUNT.getId(),
				"http://kangqbing.ngrok.wendal.cn/wx/api/wo", "state", new String[] { "snsapi_base" });
		return shorturl(url).getUrl_short();
	}
	public String getAbout() {
		String url = oauthApi.getAuthorizeURL(oauthApi.DEFAULT_WEIXIN_ACCOUNT.getId(),
				"http://kangqbing.ngrok.wendal.cn/wx/api/about", "state", new String[] { "snsapi_base" });
		return shorturl(url).getUrl_short();
	}
	public String getTableUrl(String tableid, String OpenId) {
		return "http://kangqbing.ngrok.wendal.cn/wx/api/table/" + tableid+"/"+OpenId;
	}
	public String getPicUrl(String tableid) {
		return "http://kangqbing.ngrok.wendal.cn/wx/www/img/1.jpg";
	}
}
