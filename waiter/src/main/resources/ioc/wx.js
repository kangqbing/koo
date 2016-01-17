var ioc = {
	messageMatcher : {
		type : 'com.foxinmy.weixin4j.dispatcher.DefaultMessageMatcher'
	},
	weixinAccount : {
		type : 'com.foxinmy.weixin4j.model.WeixinAccount',
		args : [ {
			java : "$conf.get('wx.appid')"
		}, {
			java : "$conf.get('wx.secret')"
		} ]
	},
	aesToken:{
		type:'com.foxinmy.weixin4j.util.AesToken',
		args : [ {
			java : "$conf.get('wx.appid')"
		}, {
			java : "$conf.get('wx.token')"
		} , {
			java : "$conf.get('wx.aesKey')"
		} ]
		
	}
}