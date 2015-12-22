package com.codecentric.hystrix.config.testing;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.Future;

import org.junit.Test;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class HelloWordTest {

    @Test
    public void runSynchron() throws Exception {

        String name = "Bob";
        String s = new HelloWord(name).execute();
        assertThat(s, is("Hello " + name + "!"));

    }

    @Test
    public void runAsynchron() throws Exception {

        String name = "Bob";
        Future<String> s = new HelloWord("Bob").queue();
        assertThat(s.get(), is("Hello " + name + "!"));

    }
}