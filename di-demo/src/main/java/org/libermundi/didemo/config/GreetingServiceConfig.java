package org.libermundi.didemo.config;

import org.libermundi.didemo.services.GreetingRepository;
import org.libermundi.didemo.services.GreetingService;
import org.libermundi.didemo.services.GreetingServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class GreetingServiceConfig {
    @Bean
    GreetingServiceFactory greetingServiceFactory(GreetingRepository repository){
        return new GreetingServiceFactory(repository);
    }

    @Bean
    @Primary
    @Profile({"default","en"})
    GreetingService primaryGreetingService(GreetingServiceFactory factory) {
        return factory.createGreetingService("en");
    }

    @Bean
    @Primary
    @Profile("de")
    GreetingService germanGreetingService(GreetingServiceFactory factory) {
        return factory.createGreetingService("de");
    }

    @Bean
    @Primary
    @Profile("es")
    GreetingService spanishGreetingService(GreetingServiceFactory factory) {
        return factory.createGreetingService("es");
    }
}
