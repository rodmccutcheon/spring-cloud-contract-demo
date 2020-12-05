package com.rodmccutcheon.ingestion.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InputPacket {
    private int opCode;
    private String gatewayAddress;
    private String packet;
}
