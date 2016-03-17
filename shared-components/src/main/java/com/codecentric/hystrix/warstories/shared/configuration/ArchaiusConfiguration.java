package com.codecentric.hystrix.warstories.shared.configuration;

import java.net.URI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.boon.etcd.ClientBuilder;
import org.boon.etcd.Etcd;
import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicWatchedConfiguration;
import com.netflix.config.source.EtcdConfigurationSource;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class ArchaiusConfiguration {

    private static final Log LOGGER = LogFactory.getLog(ArchaiusConfiguration.class);

    public ArchaiusConfiguration() {
        // Config fallback (config.properties) and Etcd configuration
        ConcurrentCompositeConfiguration compositeConfig = new ConcurrentCompositeConfiguration();

        // CoresOS Etcd service configuration
        DynamicWatchedConfiguration etcdConfiguration = createEtcdConfiguration();

        if (etcdConfiguration != null)
            compositeConfig.addConfiguration(etcdConfiguration, "etcd dynamic override configuration");

        ConfigurationManager.install(compositeConfig);

    }

    private DynamicWatchedConfiguration createEtcdConfiguration() {
        ClientBuilder clientBuilder = null;
        try {
            clientBuilder = ClientBuilder.builder().hosts(URI.create("http://192.168.99.100:2379")).timeOutInMilliseconds(1000);
            Etcd etcd = clientBuilder.createClient();

            EtcdConfigurationSource etcdConfigurationSource = new EtcdConfigurationSource(etcd, "/hystrix/");
            DynamicWatchedConfiguration etcdConfiguration = new DynamicWatchedConfiguration(etcdConfigurationSource);

            return etcdConfiguration;

        } catch (Exception e) {
            LOGGER.error("CoresOS ETCD Service not reachable...");
            return null;
        }
    }
}
