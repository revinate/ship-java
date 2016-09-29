package com.revinate.ship.gueststay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.revinate.ship.common.MonetaryAmount;
import com.revinate.ship.common.TimeSpan;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @JsonIgnore
    public MonetaryAmount getTotalPrice() {
        return pricePerUnit.multiply(BigDecimal.valueOf(numberOfUnits));
    }
}
