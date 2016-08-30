package com.revinate.ship.gueststay;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revinate.ship.common.MonetaryAmount;
import com.revinate.ship.common.TimeSpan;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public class Service {

    @JsonProperty(required = true)
    private String inventoryCode;
    @JsonProperty(required = true)
    private String rateCode;
    private TimeSpan timeSpan;
    @JsonProperty(required = true)
    private MonetaryAmount pricePerUnit;
    @JsonProperty(required = true)
    private Integer numberOfUnits;
}
