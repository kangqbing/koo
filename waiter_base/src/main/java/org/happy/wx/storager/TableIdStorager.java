package org.happy.wx.storager;

import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.mp.api.MpApi;

@IocBean
public class TableIdStorager {
	
	public String lookup(String cacheKey) throws WeixinException {
		Token accessToken = MpApi.DEFAULT_TOKEN_STORAGER.lookup(cacheKey);
		if (accessToken!=null) {
			return accessToken.getAccessToken();
		}
		return null;
	}
	public void caching(String cacheKey,int ex, String tableid) throws WeixinException {
		if (ex > 0) {
			Token accessToken =new Token();
			accessToken.setExpiresIn(ex);
			accessToken.setAccessToken(tableid);
			MpApi.DEFAULT_TOKEN_STORAGER.caching(cacheKey,accessToken);
		} else {
			Token accessToken =new Token();
			accessToken.setTime(-1);
			accessToken.setAccessToken(tableid);
			MpApi.DEFAULT_TOKEN_STORAGER.caching(cacheKey,accessToken);
		}
	}

}
