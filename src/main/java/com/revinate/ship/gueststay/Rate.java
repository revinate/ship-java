package com.revinate.ship.gueststay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.revinate.ship.common.MonetaryValue;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rate {

    public enum TimeUnitType {
        DAY, HOUR, MINUTE
    }

    private MonetaryValue amount;
    private OffsetDateTime startTime;
    private Integer timeUnits;
    private TimeUnitType timeUnitType;

    @JsonIgnore
    public Rate(MonetaryValue amount) {
        this.amount = amount;
    }
}
