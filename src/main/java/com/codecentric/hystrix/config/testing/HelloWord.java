package com.codecentric.hystrix.config.testing;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class HelloWord extends HystrixCommand<String> {

    private final String name;

    public HelloWord(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("config-testing"));
        this.name = name;
    }

    @Override
    protected String run() {
        // a real example would do work like a network call here
        return "Hello " + name + "!";
    }
}
