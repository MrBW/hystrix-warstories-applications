package com.codecentric.hystrix.warstories;

import com.codecentric.hystrix.warstories.shared.configuration.ArchaiusTestConfiguration;
import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Benjamin Wilms (xd98870)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ArchaiusTestConfiguration.class)
@WebAppConfiguration
public class TransportServiceApplicationTest {

    @Test
    public void contextLoads() throws Exception {

    }

    @Test
    public void getConfigValue() throws Exception {

        DynamicBooleanProperty archaiusTestProperty =
                DynamicPropertyFactory.getInstance().getBooleanProperty("archaius.test.property", false);

        // if context loads correct, property value = true
        assertTrue(archaiusTestProperty.get());

    }
}