package org.happy.wx.js;

import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.token.TokenCreator;
import com.foxinmy.weixin4j.token.TokenHolder;
import com.foxinmy.weixin4j.token.TokenStorager;
@IocBean(args={"refer:jSTicketCreator","refer:redisTokenStorager"})
public class JsTokenHolder extends TokenHolder{
	
	public JsTokenHolder(TokenCreator tokenCreator, TokenStorager tokenStorager) {
		super(tokenCreator, tokenStorager);
	}

	

}
