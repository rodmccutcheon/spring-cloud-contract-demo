package com.rodmccutcheon.ingestion.config;

import com.rodmccutcheon.ingestion.data.GatewayNotification;
import com.rodmccutcheon.ingestion.data.InputPacket;
import com.rodmccutcheon.ingestion.parsing.SimpleJsonGatewayMessageParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class IngestionConfig {

    private SimpleJsonGatewayMessageParser simpleJsonGatewayMessageParser;

    @Autowired
    public IngestionConfig(SimpleJsonGatewayMessageParser simpleJsonGatewayMessageParser) {
        this.simpleJsonGatewayMessageParser = simpleJsonGatewayMessageParser;
    }

    @Bean
    public Function<InputPacket, Collection<GatewayNotification>> parse() {
        return simpleJsonGatewayMessageParser;
    }

    @Bean
    public Function<Collection<GatewayNotification>, Collection<Message<GatewayNotification>>> route() {
        return gatewayNotifications -> gatewayNotifications.stream().map(gatewayNotification -> MessageBuilder
                .withPayload(gatewayNotification)
                .setHeader("spring.cloud.stream.sendto.destination", convertCamelCaseToSnakeCase(gatewayNotification.getClass().getSimpleName()))
                .build()).collect(Collectors.toUnmodifiableList());
    }

    private String convertCamelCaseToSnakeCase(String input) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return input.replaceAll(regex, replacement).toLowerCase();
    }
}
