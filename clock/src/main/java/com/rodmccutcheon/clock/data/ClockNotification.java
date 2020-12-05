package com.rodmccutcheon.clock.data;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClockNotification implements Serializable {
    private String key;
    private Long buildingId;
    private Instant startTime;
    private Instant endTime;

    public ClockNotification(String key, Long buildingId, Instant startTime) {
        this.key = key;
        this.buildingId = buildingId;
        this.startTime = startTime;
    }
}
