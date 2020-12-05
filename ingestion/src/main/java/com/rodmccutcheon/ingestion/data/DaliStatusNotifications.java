package com.rodmccutcheon.ingestion.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DaliStatusNotifications implements GatewayNotification {

    public enum DaliStatus {
        BALLAST_FAILURE,
        LAMP_FAILURE,
        POWER_FAILURE
    }

    private String gatewayAddress;
    private Set<DaliStatus> values;

    public DaliStatusNotifications(String gatewayAddress) {
        this(gatewayAddress, new HashSet<>());
    }
}
