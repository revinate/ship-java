package com.revinate.ship.gueststay;

import com.revinate.ship.common.MonetaryValue;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Rate {

    public enum TimeUnitType {
        DAY, HOUR
    }

    private MonetaryValue amount;
    private OffsetDateTime startTime;
    private Integer timeUnits;
    private TimeUnitType timeUnitType;
}
