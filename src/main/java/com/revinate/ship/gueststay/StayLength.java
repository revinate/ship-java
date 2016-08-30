package com.revinate.ship.gueststay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StayLength {

    public enum StayLengthUnits {
        DAY
    }

    @JsonProperty(required = true)
    private Integer stayLength;
    private StayLengthUnits stayLengthUnits;
}
