package com.codecentric.hystrix.warstories.shared.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import com.netflix.config.ClasspathPropertiesConfiguration;
import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConfigurationManager;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Configuration
public class ArchaiusTestConfiguration {

    private static final Log LOGGER = LogFactory.getLog(ArchaiusTestConfiguration.class);

    public ArchaiusTestConfiguration() {

        LOGGER.debug(LOGGER.isDebugEnabled() ? "### Archaius init: " + this.toString() : null);

        // Config fallback (config.properties) and Etcd configuration
        ConcurrentCompositeConfiguration compositeConfig = new ConcurrentCompositeConfiguration();

        // File based configuration
        ClasspathPropertiesConfiguration.initialize();

        ConfigurationManager.install(compositeConfig);

        LOGGER.debug(
            LOGGER.isDebugEnabled() ? "is Configuration installed: " + ConfigurationManager.isConfigurationInstalled() : null);

    }

}
