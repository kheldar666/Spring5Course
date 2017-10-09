package org.libermundi.didemo.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("de")
@Primary
public class GermanGreetingServiceImpl implements GreetingService {
    public static final String HELLO_GURUS = "Hello Gurus !!!";

    @Override
    public String sayGreetings() {
        return HELLO_GURUS + " - from German Greeting Service";
    }
}
