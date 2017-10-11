package org.libermundi.didemo.services;

public class SpanishGreetingServiceImpl implements GreetingService {

    private GreetingRepository greetingRepository;

    public SpanishGreetingServiceImpl(GreetingRepository greetingRepository) {
        this.greetingRepository=greetingRepository;
    }

    @Override
    public String sayGreetings() {
        return this.greetingRepository.getSpanishGreeting();
    }
}
