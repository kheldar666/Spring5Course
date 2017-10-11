package org.libermundi.didemo.services;

public class GermanGreetingServiceImpl implements GreetingService {

    private GreetingRepository greetingRepository;

    public GermanGreetingServiceImpl(GreetingRepository greetingRepository) {
        this.greetingRepository=greetingRepository;
    }

    @Override
    public String sayGreetings() {
        return this.greetingRepository.getGermanGreeting();
    }
}
