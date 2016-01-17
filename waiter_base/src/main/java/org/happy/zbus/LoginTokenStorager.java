package org.happy.zbus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.nutz.ioc.loader.annotation.IocBean;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.token.TokenStorager;
import com.foxinmy.weixin4j.xml.XmlStream;

@IocBean(args = { "java:$conf.get('login.token.path','e:/token')" })
public class LoginTokenStorager implements TokenStorager {
	private final String cachePath;

	public LoginTokenStorager(String cachePath) {
		this.cachePath = cachePath;
	}

	public Token lookup(String cacheKey) throws WeixinException {
		File token_file = new File(String.format("%s/%s.xml", new Object[] { cachePath, cacheKey }));
		try {
			if (token_file.exists()) {
				Token token = (Token) XmlStream.fromXML(new FileInputStream(token_file), Token.class);

				if (token.getTime() < 0L) {
					return token;
				}
				if (token.getTime() + token.getExpiresIn() * 1000L - 2L > System.currentTimeMillis()) {
					return token;
				}
			}
			return null;
		} catch (IOException e) {
			throw new WeixinException(e);
		}
	}

	public void caching(String cacheKey, Token token) throws WeixinException {
		try {
			XmlStream.toXML(token,
					new FileOutputStream(new File(String.format("%s/%s.xml", new Object[] { cachePath, cacheKey }))));

		} catch (IOException e) {
			throw new WeixinException(e);
		}
	}

	public void del(String cacheKey) throws WeixinException {
		File token_file = new File(String.format("%s/%s.xml", new Object[] { cachePath, cacheKey }));
		if (token_file.exists()) {
			token_file.delete();
		}
	}

}
