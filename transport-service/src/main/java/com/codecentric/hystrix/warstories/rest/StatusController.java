package com.codecentric.hystrix.warstories.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.config.DynamicPropertyFactory;

/**
 * @author Benjamin Wilms (xd98870)
 */
@RestController
public class StatusController {

    @RequestMapping("status")
    public String status() {

        return "ETCD Status: " + DynamicPropertyFactory.getInstance().getStringProperty("etcd.status", "value from code").get();

    }
}
