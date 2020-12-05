package com.rodmccutcheon.ingestion;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = { "spring.config.location=classpath:application.yml" })
@AutoConfigureMessageVerifier
@Tag("Contract")
public class BaseContractTestMessaging {

//    @Autowired
//    private Function<Collection<GatewayNotification>, Collection<Message<GatewayNotification>>> route;
//
//    public void physicalMapping() {
//        System.out.println("before");
//        route.apply(Collections.singletonList(new PhysicalMappingNotification(1, "00000000-0000-0000-0000-000000000001", 123)));
//        System.out.println("after");
//    }
}