package org.happy.wx.api;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class TimeOut implements MethodInterceptor {
	@Override
	public void filter(InterceptorChain chain) throws Throwable {
		try {
			chain.doChain();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
