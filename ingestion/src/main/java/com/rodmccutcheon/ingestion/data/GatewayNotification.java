package com.rodmccutcheon.ingestion.data;

import java.io.Serializable;

public interface GatewayNotification extends Serializable {
    String getGatewayAddress();
}
