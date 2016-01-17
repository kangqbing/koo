package org.nutz.http.face;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.nutz.lang.Strings;
import org.nutz.mvc.ActionInfo;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.impl.Loadings;

/**
 * Created by wendal on 06/11/2015.
 */
public class FaceBuilder<T> implements InvocationHandler {

    protected Class<T> klass;
    protected String endpoint;
    protected Map<Method, ActionInfo> maps = new HashMap<>();

    public FaceBuilder(Class<T> klass) {
        this.klass = klass;
    }

    protected void buildConfigure() {
        ActionInfo klassActionInfo = Loadings.createInfo(klass);
        for (Method method: klass.getMethods()) {
            At at = method.getAnnotation(At.class);
            if (at == null)
                continue;
            ActionInfo methodActionInfo = Loadings.createInfo(method);
            methodActionInfo = methodActionInfo.mergeWith(klassActionInfo);
            maps.put(method, methodActionInfo);
        }
    }

    public FaceBuilder<T> setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    @SuppressWarnings("unchecked")
	public T build() {
        if (this.klass == null || this.endpoint == null)
            throw new RuntimeException("klass and endpoint must set");
        buildConfigure();
        return (T)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{klass}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ActionInfo ai = maps.get(method);
        String url = this.endpoint + Strings.join("/", ai.getPaths());
        System.out.println("URL=" + url);
        if (ai.getHttpMethods().contains("GET")) {
            System.out.println("GET");

        }
        return null;
    }

}
