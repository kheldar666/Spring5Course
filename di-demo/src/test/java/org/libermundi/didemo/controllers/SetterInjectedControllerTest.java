package org.libermundi.didemo.controllers;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.didemo.services.GreetingServiceImpl;

import static org.junit.Assert.assertEquals;

public class SetterInjectedControllerTest {
    private SetterInjectedController injectedController;

    @Before
    public void setUp() throws Exception {
        this.injectedController = new SetterInjectedController();
        this.injectedController.setGreetingService(new GreetingServiceImpl());
    }

    @Test
    public void testGreeting()throws Exception {
        assertEquals(GreetingServiceImpl.HELLO_GURUS, injectedController.sayHello());
    }
}
