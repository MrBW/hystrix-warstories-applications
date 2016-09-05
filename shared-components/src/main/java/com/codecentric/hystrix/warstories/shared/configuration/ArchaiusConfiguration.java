package com.codecentric.hystrix.warstories.shared.configuration;

import java.net.URI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.boon.etcd.ClientBuilder;
import org.boon.etcd.Etcd;
import org.springframework.context.annotation.Configuration;
import com.netflix.config.ClasspathPropertiesConfiguration;
import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.config.DynamicWatchedConfiguration;
import com.netflix.config.source.EtcdConfigurationSource;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Configuration
public class ArchaiusConfiguration {

    private static final Log LOGGER = LogFactory.getLog(ArchaiusConfiguration.class);

    private DynamicStringProperty etcdServerPort =
        DynamicPropertyFactory.getInstance().getStringProperty("server.etcd.baseurl", "http://etcd:2379");

    public ArchaiusConfiguration() {

        LOGGER.debug(LOGGER.isDebugEnabled() ? "### Archaius init: " + this.toString() : null);

        // Config fallback (config.properties) and Etcd configuration
        ConcurrentCompositeConfiguration compositeConfig = new ConcurrentCompositeConfiguration();

        // File based configuration
        ClasspathPropertiesConfiguration.initialize();

        // CoresOS Etcd service configuration
        DynamicWatchedConfiguration etcdConfiguration = createEtcdConfiguration();

        if (etcdConfiguration != null)
            compositeConfig.addConfiguration(etcdConfiguration, "etcd dynamic override configuration");
        else {
            LOGGER.debug(LOGGER.isDebugEnabled() ? "etcdConfigurartion == null" : null);
        }
        ConfigurationManager.install(compositeConfig);

        LOGGER.debug(
            LOGGER.isDebugEnabled() ? "is Configuration installed: " + ConfigurationManager.isConfigurationInstalled() : null);

    }

    private DynamicWatchedConfiguration createEtcdConfiguration() {
        try {
            Etcd etcd = createEtcdClient();

            LOGGER.debug(LOGGER.isDebugEnabled() ? "Etcd Client created: " + (etcd != null) : null);

            EtcdConfigurationSource etcdConfigurationSource = new EtcdConfigurationSource(etcd, "/hystrix/");
            return new DynamicWatchedConfiguration(etcdConfigurationSource);

        } catch (Exception e) {
            LOGGER.error("CoresOS ETCD Service not reachable, Server: " + etcdServerPort, e);
            return null;
        }
    }

    /***
     * Create and initials etcd client
     * @return
     */
    private Etcd createEtcdClient() {
        LOGGER.debug(LOGGER.isDebugEnabled() ? "Etcd server baseurl: " + etcdServerPort.get() : null);

        ClientBuilder clientBuilder;
        clientBuilder = ClientBuilder.builder().hosts(URI.create(etcdServerPort.get())).timeOutInMilliseconds(5000);

        return clientBuilder.createClient();

    }
}
