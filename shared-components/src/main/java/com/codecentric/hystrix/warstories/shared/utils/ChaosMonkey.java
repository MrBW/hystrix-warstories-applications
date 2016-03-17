package com.codecentric.hystrix.warstories.shared.utils;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Component
public class ChaosMonkey {

    private static final Log LOGGER = LogFactory.getLog(ChaosMonkey.class);

    public void iWantTrouble() {
        // Trouble?
        int troubleRand = RandomUtils.nextInt(0, 10);
        int exceptionRand = RandomUtils.nextInt(0, 10);

        if (troubleRand > 3) {
            LOGGER.debug("Chaos Monkey - generates trouble");
            // Timeout or Exception?
            if (exceptionRand < 5) {
                LOGGER.debug("Chaos Monkey - timeout");
                // Timeout
                generateTimeout();
            } else {
                LOGGER.debug("Chaos Monkey - exception");
                // Exception
                throw new RuntimeException("Chaos Monkey - RuntimeException");
            }
        }
    }

    /***
     * Generates a timeout exception, hard codeed 3000ms
     */
    private void generateTimeout() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            LOGGER.debug("Thread sleep interrupt exception");
        }
    }
}
