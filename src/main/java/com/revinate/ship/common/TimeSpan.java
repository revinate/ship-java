package com.revinate.ship.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeSpan {

    public enum TimeUnitType {
        DAY, HOUR, MINUTE
    }

    @JsonProperty(required = true)
    @NotNull
    private OffsetDateTime startTime;

    @JsonProperty(required = true)
    @NotNull
    private Integer timeUnits;

    @JsonProperty(required = true)
    @NotNull
    private TimeUnitType timeUnitType;

    @JsonIgnore
    public TimeSpan(OffsetDateTime startTime, Integer timeUnits, TimeUnitType timeUnitType) {
        this.startTime = startTime;
        this.timeUnits = timeUnits;
        this.timeUnitType = timeUnitType;
    }
}
