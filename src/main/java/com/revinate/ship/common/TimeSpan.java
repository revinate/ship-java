package com.revinate.ship.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class TimeSpan {

    public enum TimeUnitType {
        DAY, HOUR, MINUTE
    }

    @JsonProperty(required = true)
    private OffsetDateTime startTime;
    @JsonProperty(required = true)
    private Integer timeUnits;
    @JsonProperty(required = true)
    private TimeUnitType timeUnitType;
}
