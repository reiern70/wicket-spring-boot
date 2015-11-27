package com.giffing.wicket.spring.boot.starter.configuration.extensions.devutils.statelesschecker;

import org.apache.wicket.devutils.stateless.StatelessChecker;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import com.giffing.wicket.spring.boot.starter.WicketProperties;

/**
 * Enables the states checker from the Wicket devutils. Its only enabled if the
 * following condition matches
 * 
 * 1. The {@link StatelessChecker} is present in the classpath
 * 
 * 2. the {@link WicketProperties}.statelessCheckerEnabled is set to true
 * (default=false)
 * 
 * @author Marc Giffing
 *
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = "wicket.devutils.statelesschecker", value = "enabled", matchIfMissing = false)
@ConditionalOnClass(value = org.apache.wicket.devutils.stateless.StatelessChecker.class)
@EnableConfigurationProperties({ StatelessCheckerProperties.class })
public class StatelessCheckerConfig implements WicketApplicationInitConfiguration {

	@Override
	public void init(WebApplication webApplication) {
		webApplication.getComponentPostOnBeforeRenderListeners().add(new StatelessChecker());
	}

}
