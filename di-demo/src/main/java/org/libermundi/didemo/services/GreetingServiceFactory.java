package org.libermundi.didemo.services;

public class GreetingServiceFactory {
    private GreetingRepository greetingrepository;

    public GreetingServiceFactory(GreetingRepository greetingrepository) {
        this.greetingrepository = greetingrepository;
    }

    public GreetingService createGreetingService(String lang) {
        switch (lang) {
            case "en":
                return new PrimaryGreetingService(greetingrepository);
            case "de":
                return new GermanGreetingServiceImpl(greetingrepository);
            case "es":
                return new SpanishGreetingServiceImpl(greetingrepository);
            default:
                return new PrimaryGreetingService(greetingrepository);

        }
    }
}
