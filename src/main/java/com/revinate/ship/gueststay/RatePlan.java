package com.revinate.ship.gueststay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.revinate.ship.common.TimeSpan;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RatePlan {

    @JsonProperty(required = true)
    private String rateCode;
    private TimeSpan timeSpan;
    private Boolean confidential;
    private List<Rate> rates;

    @JsonIgnore
    public RatePlan(String rateCode) {
        this.rateCode = rateCode;
    }

    public void addRate(Rate rate) {
        if (Objects.isNull(this.rates)) {
            this.rates = new ArrayList<>();
        }
        this.rates.add(rate);
    }
}
