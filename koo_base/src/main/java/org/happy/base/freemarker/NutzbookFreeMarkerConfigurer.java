package org.happy.base.freemarker;

import javax.servlet.ServletContext;

import org.nutz.plugins.view.freemarker.FreeMarkerConfigurer;
import org.nutz.plugins.view.freemarker.FreemarkerDirectiveFactory;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateModelException;
import org.happy.base.util.Toolkit;

public class NutzbookFreeMarkerConfigurer extends FreeMarkerConfigurer {

	public NutzbookFreeMarkerConfigurer() {
		super();
	}

	public NutzbookFreeMarkerConfigurer(Configuration configuration, ServletContext sc, String prefix, String suffix, FreemarkerDirectiveFactory freemarkerDirectiveFactory) {
		super(configuration, sc, prefix, suffix, freemarkerDirectiveFactory);
		try {
			configuration.setAllSharedVariables(new SimpleHash(Toolkit.getTemplateShareVars()));
		} catch (TemplateModelException e) {
			throw new RuntimeException(e);
		}
	}

	
}
