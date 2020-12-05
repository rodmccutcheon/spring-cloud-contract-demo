package com.rodmccutcheon.clock;

import com.rodmccutcheon.clock.config.ClockConfig;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = ClockApplication.class)
@TestPropertySource(properties = { "spring.config.location=classpath:application.yml" })
@AutoConfigureMessageVerifier
@Tag("Contract")
public class BaseContractTestMessaging {

    @Autowired
    private ClockConfig clockConfig;

    public void timeoutApplierClockSupplier() {
        clockConfig.timeoutApplierClockSupplier();
//        StepVerifier
//                .withVirtualTime(clockConfig.elmtTimeoutApplierClockSupplier())
//                .expectSubscription()
//                .thenAwait(Duration.ofSeconds(60))
//                .expectNextCount(2)
//                .thenCancel()
//                .verify();
    }
}
