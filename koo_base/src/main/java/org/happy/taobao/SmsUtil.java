package org.happy.taobao;

import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.TaobaoResponse;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@IocBean
public class SmsUtil {
	@Inject("java:$conf.get('taobaodayu.serverUrl')")
	public String serverUrl; 
	@Inject("java:$conf.get('taobaodayu.appKey')")
	public String appKey; 
	@Inject("java:$conf.get('taobaodayu.appSecret')")
	public String appSecret;
	
	public TaobaoResponse post(String extend,SmsType smsType,SmsParam smsParam) throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(extend);
		req.setSmsType("normal");
		req.setSmsFreeSignName(smsType.getSmsFreeSignName());
		req.setSmsParamString(Json.toJson(smsParam));
		req.setRecNum(smsParam.getPhone());
		req.setSmsTemplateCode(smsType.getSmsTemplateCode());
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		return rsp;
	}
	
	
	

}
