package com.revinate.ship.gueststay;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revinate.ship.common.TimeSpan;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RatePlan {

    @JsonProperty(required = true)
    private String rateCode;
    private TimeSpan timeSpan;
    private Boolean confidential;
    private List<Rate> rates;
}
