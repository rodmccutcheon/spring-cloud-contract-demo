package com.rodmccutcheon.clock.config;

import com.rodmccutcheon.clock.client.WebClient;
import com.rodmccutcheon.clock.data.ClockNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Configuration
public class ClockConfig {

    private WebClient webClient;

    @Autowired
    public ClockConfig(WebClient webClient) {
        this.webClient = webClient;
    }

    @Bean
    public Supplier<Flux<ClockNotification>> timeoutApplierClockSupplier() {
        return () -> Flux.interval(Duration.ofSeconds(60)).flatMap(l -> {
            return Flux.fromIterable(webClient.getBuildings().stream()
                    .map(building -> new ClockNotification("TIMEOUT_APPLIER", building.getId(), Instant.now()))
                    .collect(Collectors.toSet()));
        });
    }
}
