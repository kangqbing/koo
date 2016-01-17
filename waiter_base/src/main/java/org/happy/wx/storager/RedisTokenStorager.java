package org.happy.wx.storager;

import org.happy.base.util.RedisInterceptor;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.token.TokenStorager;
import com.foxinmy.weixin4j.util.StringUtil;

@IocBean
public class RedisTokenStorager implements TokenStorager {

	@Override
	@Aop("redis")
	public Token lookup(String cacheKey) throws WeixinException {
		String accessToken = RedisInterceptor.jedis().get(cacheKey);
		if (!StringUtil.isBlank(accessToken)) {
			return new Token(accessToken);
		}
		return null;
	}
	@Override
	@Aop("redis")
	public void caching(String cacheKey, Token token) throws WeixinException {
		if (token.getExpiresIn() > 0) {
			RedisInterceptor.jedis().setex(cacheKey, token.getExpiresIn(), token.getAccessToken());
		} else {
			RedisInterceptor.jedis().set(cacheKey, token.getAccessToken());
		}
	}

}
