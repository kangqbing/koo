package org.happy.wx.module;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.happy.base.annotation.SLog;
import org.happy.wx.api.WxApi;
import org.nutz.dao.Dao;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.request.WeixinMessage;
import com.foxinmy.weixin4j.request.WeixinRequest;
import com.foxinmy.weixin4j.type.EncryptType;
import com.foxinmy.weixin4j.util.AesToken;
import com.foxinmy.weixin4j.util.MessageUtil;
import com.foxinmy.weixin4j.util.StringUtil;
import com.foxinmy.weixin4j.xml.EncryptMessageHandler;

@IocBean
@SLog(tag = "微信", msg = "")
public class WxModeule {
	@Inject
	Dao dao;
	@Inject
	PropertiesProxy conf;
	@Inject
	WxApi wxApi;
	@Inject
	AesToken aesToken;

	@At({ "/wapi", "/wapi/?" })
	@Ok("void")
	@SLog(tag = "wapi", msg = "[${return}]")
	public WeixinMessage wapi(String token, HttpServletRequest request, HttpServletResponse response,
			String encrypt_type, String echostr, String timestamp, String nonce, String signature, String msgSignature)
					throws WeixinException, UnsupportedEncodingException, IOException {
		WeixinMessage message = null;
		EncryptType encryptType = encrypt_type != null ? EncryptType.valueOf(encrypt_type.toUpperCase())
				: EncryptType.RAW;
		String messageContent = wxApi.inputStream2String(request.getInputStream());
		String methodName = request.getMethod().toUpperCase();
		String encryptContent = null;
		if (!StringUtil.isBlank(messageContent) && encryptType == EncryptType.AES) {
			if (StringUtil.isBlank(aesToken.getAesKey())) {
				throw new WeixinException("AESEncodingKey not be null in AES mode");
			}
			EncryptMessageHandler encryptHandler = EncryptMessageHandler.parser(messageContent);
			encryptContent = encryptHandler.getEncryptContent();
			if (aesToken.getWeixinId().startsWith("tj")) {
				aesToken = new AesToken(encryptHandler.getToUserName(), aesToken.getToken(), aesToken.getAesKey());
			}
			messageContent = MessageUtil.aesDecrypt(aesToken.getWeixinId(), aesToken.getAesKey(), encryptContent);
		}
		WeixinRequest req = new WeixinRequest(methodName, encryptType, echostr, timestamp, nonce, signature,
				msgSignature, messageContent, encryptContent, aesToken, null);
		try {
			message = wxApi.handler(req, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
}
