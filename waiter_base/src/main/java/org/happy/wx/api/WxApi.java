package org.happy.wx.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.happy.wx.handler.Text;
import org.happy.wx.js.JsTokenHolder;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.resource.Scans;

import com.foxinmy.weixin4j.dispatcher.DefaultMessageMatcher;
import com.foxinmy.weixin4j.dispatcher.WeixinMessageKey;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.handler.WeixinMessageHandler;
import com.foxinmy.weixin4j.model.WeixinAccount;
import com.foxinmy.weixin4j.request.WeixinMessage;
import com.foxinmy.weixin4j.request.WeixinRequest;
import com.foxinmy.weixin4j.response.BlankResponse;
import com.foxinmy.weixin4j.response.WeixinResponse;
import com.foxinmy.weixin4j.socket.WeixinMessageTransfer;
import com.foxinmy.weixin4j.type.AccountType;
import com.foxinmy.weixin4j.type.EncryptType;
import com.foxinmy.weixin4j.util.AesToken;
import com.foxinmy.weixin4j.util.Consts;
import com.foxinmy.weixin4j.util.MessageUtil;
import com.foxinmy.weixin4j.util.RandomUtil;
import com.foxinmy.weixin4j.util.StringUtil;
import com.foxinmy.weixin4j.xml.MessageTransferHandler;

import io.netty.handler.codec.http.HttpMethod;

@IocBean(create = "init")
public class WxApi {

	@Inject
	WeixinAccount weixinAccount;

	
	@Inject
	WxProxy api;

	@Inject
	JsTokenHolder jsTokenHolder;

	@Inject
	PropertiesProxy conf;

	@Inject
	public DefaultMessageMatcher messageMatcher;
	HashMap<Class<? extends WeixinMessage>, Unmarshaller> messageUnmarshaller = new HashMap<Class<? extends WeixinMessage>, Unmarshaller>();

	Map<Class<?>, Class<?>> clazz = new HashMap<Class<?>, Class<?>>();

	public Unmarshaller getUnmarshaller(Class<? extends WeixinMessage> clazz) throws WeixinException {
		Unmarshaller unmarshaller = messageUnmarshaller.get(clazz);
		if (unmarshaller == null) {
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
				unmarshaller = jaxbContext.createUnmarshaller();
				messageUnmarshaller.put(clazz, unmarshaller);
			} catch (JAXBException e) {
				throw new WeixinException(e);
			}
		}
		return unmarshaller;
	}

	public void init() {
		for (Class<?> classZ : Scans.me().scanPackage(Text.class.getPackage().getName()))
			addClass(classZ);
	}

	private static final Log log = Logs.get();

	private void addClass(Class<?> classZ) {
		if (classZ.isInterface() || classZ.isMemberClass() || classZ.isEnum() || classZ.isAnnotation()
				|| classZ.isAnonymousClass())
			return;
		int modify = classZ.getModifiers();
		if (Modifier.isAbstract(modify) || (!Modifier.isPublic(modify)))
			return;
		IocBean iocBean = classZ.getAnnotation(IocBean.class);
		if (iocBean != null) {
			if (WeixinMessageHandler.class.isAssignableFrom(classZ)) {
				if (this.clazz.containsKey(getGenericType(classZ))) {
					log.debugf("重复定义:" + getGenericType(classZ) + " MessageHandler");
				} else {
					log.debugf("定义,MessageHandler:" + getGenericType(classZ) + "->" + classZ);
					this.clazz.put(getGenericType(classZ), classZ);
				}
			}
		}
	}

	public Class<?> getGenericType(Class<?> classZ) {
		Class<?> clazz = null;
		Type type = classZ.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType ptype = ((ParameterizedType) type);
			Type[] args = ptype.getActualTypeArguments();
			clazz = (Class<?>) args[0];
		}
		return clazz;
	}

	public WeixinMessageKey defineMessageKey(String messageType, String eventType, AccountType accountType) {
		return new WeixinMessageKey(messageType, eventType, accountType);
	}

	public <M extends WeixinMessage> M messageRead(String message, Class<M> clazz) throws WeixinException {
		if (clazz == null) {
			return null;
		}
		try {
			Source source = new StreamSource(new ByteArrayInputStream(message.getBytes(Consts.UTF_8)));
			JAXBElement<M> jaxbElement = getUnmarshaller(clazz).unmarshal(source, clazz);
			return jaxbElement.getValue();
		} catch (JAXBException e) {
			throw new WeixinException(e);
		}
	}

	public WeixinResponse handler(WeixinRequest request, Object message, Set<String> nodeNames) {
		if (message != null) {
			try {
				log.debugf("message[class] : %s", message.getClass());
				log.debugf("message: %s", Json.toJson(message));
				@SuppressWarnings("unchecked")
				Class<WeixinMessageHandler> claz = (Class<WeixinMessageHandler>) clazz.get(message.getClass());
				WeixinMessageHandler wc = Mvcs.ctx().getDefaultIoc().get(claz);
				if (wc.canHandle(request, message, nodeNames)) {
					System.err.println(claz);
					WeixinResponse t = wc.doHandle(request, message, nodeNames);
					return t;
				}
			} catch (WeixinException e) {
				e.printStackTrace();
			}
			log.errorf("message[class] : %s", "未找到实现类:" + message.getClass());
			init();
			return handler(request, message, nodeNames);
		} else {
			return null;
		}
	}

	public WeixinResponse blankResponse(Object message) {
		return BlankResponse.global;
	}

	public String inputStream2String(InputStream in) throws UnsupportedEncodingException, IOException {
		if (in == null) {
			return "";
		}
		StringBuilder out = new StringBuilder();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n, "UTF-8"));
		}
		return out.toString();
	}

	public WeixinMessage handler(WeixinRequest request, HttpServletResponse response)
			throws Exception, WeixinException {
		final AesToken aesToken = request.getAesToken();
		if (request.getMethod().equals(HttpMethod.GET.name())) {
			if (!StringUtil.isBlank(request.getSignature())
					&& MessageUtil.signature(aesToken.getToken(), request.getTimeStamp(), request.getNonce())
							.equals(request.getSignature())) {

				response.getWriter().write(request.getEchoStr());
				return null;
			}
			if (!StringUtil.isBlank(request.getMsgSignature()) && MessageUtil
					.signature(aesToken.getToken(), request.getTimeStamp(), request.getNonce(), request.getEchoStr())
					.equals(request.getMsgSignature())) {
				response.getWriter().write(MessageUtil.aesDecrypt(null, aesToken.getAesKey(), request.getEchoStr()));
				return null;
			}
			return null;
		} else if (request.getMethod().equals(HttpMethod.POST.name())) {
			if (!StringUtil.isBlank(request.getSignature())
					&& !MessageUtil.signature(aesToken.getToken(), request.getTimeStamp(), request.getNonce())
							.equals(request.getSignature())) {
				return null;
			}
		}
		WeixinMessageTransfer messageTransfer = MessageTransferHandler.parser(request);
		WeixinMessageKey messageKey = defineMessageKey(messageTransfer.getMsgType(), messageTransfer.getEventType(),
				messageTransfer.getAccountType());
		Class<? extends WeixinMessage> targetClass = messageMatcher.match(messageKey);
		WeixinMessage message = messageRead(request.getOriginalContent(), targetClass);
		WeixinResponse wxresponse = handler(request, message, messageTransfer.getNodeNames());
		if (wxresponse == null)
			wxresponse = blankResponse(message);
		log.debugf("wxresponse : %s", wxresponse);
		writeAndFlush(messageTransfer, wxresponse, response);
		return message;
	}

	private void writeAndFlush(WeixinMessageTransfer messageTransfer, WeixinResponse wxresponse,
			HttpServletResponse response) throws WeixinException, IOException {
		if (wxresponse == BlankResponse.global) {
			response.getWriter().write("");
		} else {
			EncryptType encryptType = messageTransfer.getEncryptType();
			StringBuilder content = new StringBuilder();
			content.append("<xml>");
			content.append(String.format("<ToUserName><![CDATA[%s]]></ToUserName>", messageTransfer.getFromUserName()));
			content.append(
					String.format("<FromUserName><![CDATA[%s]]></FromUserName>", messageTransfer.getToUserName()));
			content.append(
					String.format("<CreateTime><![CDATA[%d]]></CreateTime>", System.currentTimeMillis() / 1000l));
			content.append(String.format("<MsgType><![CDATA[%s]]></MsgType>", wxresponse.getMsgType()));
			content.append(wxresponse.toContent());
			content.append("</xml>");
			if (encryptType == EncryptType.AES) {
				AesToken aesToken = messageTransfer.getAesToken();
				String nonce = RandomUtil.generateString(32);
				String timestamp = Long.toString(System.currentTimeMillis() / 1000l);
				String encrtypt = MessageUtil.aesEncrypt(aesToken.getWeixinId(), aesToken.getAesKey(),
						content.toString());
				String msgSignature = MessageUtil.signature(aesToken.getToken(), nonce, timestamp, encrtypt);
				content.delete(0, content.length());
				content.append("<xml>");
				content.append(String.format("<Nonce><![CDATA[%s]]></Nonce>", nonce));
				content.append(String.format("<TimeStamp><![CDATA[%s]]></TimeStamp>", timestamp));
				content.append(String.format("<MsgSignature><![CDATA[%s]]></MsgSignature>", msgSignature));
				content.append(String.format("<Encrypt><![CDATA[%s]]></Encrypt>", encrtypt));
				content.append("</xml>");
			}

			response.setContentType("application/xml");
			System.out.println(content.toString());
			response.getWriter().write(content.toString());
		}
	}

	public String genJsSDKConfig(String url, String... jsApiList) throws WeixinException {
		String jt = jsTokenHolder.getAccessToken();
		long timestamp = System.nanoTime();
		String timetamp = ("" + timestamp).substring(0, 10);
		String nonceStr = R.UU64();
		String str = String.format("jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s", jt, nonceStr, timetamp, url);
		String signature = Lang.sha1(str);
		NutMap map = new NutMap();
		map.put("debug", false);
		map.put("appId", weixinAccount.getId());
		map.put("timestamp", timetamp);
		map.put("nonceStr", nonceStr);
		map.put("signature", signature);
		map.put("jsApiList", jsApiList);
		return Json.toJson(map);
	}

	public String JsSDKConfig(String url) throws WeixinException {
		String mp = genJsSDKConfig(url, "checkJsApi", "onMenuShareTimeline", "onMenuShareAppMessage", "onMenuShareQQ",
				"onMenuShareWeibo", "onMenuShareQZone", "hideMenuItems", "showMenuItems", "hideAllNonBaseMenuItem",
				"showAllNonBaseMenuItem", "translateVoice", "startRecord", "stopRecord", "onVoiceRecordEnd",
				"playVoice", "onVoicePlayEnd", "pauseVoice", "stopVoice", "uploadVoice", "downloadVoice", "chooseImage",
				"previewImage", "uploadImage", "downloadImage", "getNetworkType", "openLocation", "getLocation",
				"hideOptionMenu", "showOptionMenu", "closeWindow", "scanQRCode", "chooseWXPay",
				"openProductSpecificView");
		return mp;
	}
}
