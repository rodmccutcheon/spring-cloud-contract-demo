package com.rodmccutcheon.ingestion.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SceneNotification implements GatewayNotification {
    private String gatewayAddress;
    private String value;
}
