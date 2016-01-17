package org.happy.base.shiro.freemarker;

import java.io.IOException;
import java.util.Map;

import org.apache.shiro.subject.Subject;
import org.nutz.lang.Lang;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

/**
 * <p>
 * Equivalent to {@link org.apache.shiro.web.tags.PermissionTag}
 * </p>
 */
public abstract class PermissionTag extends SecureTag {

	@SuppressWarnings("rawtypes")
	String getName(Map params) {
		return getParam(params, "name");
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void verifyParameters(Map params) throws TemplateModelException {
		String permission = getName(params);

		if (permission == null || permission.length() == 0) {
			throw new TemplateModelException("The 'name' tag attribute must be set.");
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
		String p = getName(params);

		boolean show = showTagBody(p);
		if (show) {
			renderBody(env, body);
		}
	}

	protected boolean isPermitted(String p) {
		Subject subject = getSubject();
		if (Lang.isEmpty(subject)) {
			return false;
		}
		return getSubject().isPermitted(p);
	}

	protected abstract boolean showTagBody(String p);
}
