package com.revinate.ship.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RateListResult extends Result {

    @Valid
    private Summary summary;

    @Valid
    private List<RateResult> rates;

    public void addRate(RateResult rate) {
        if (Objects.isNull(this.rates)) {
            this.rates = new ArrayList<>();
        }
        this.rates.add(rate);
    }

    public void addRate(List<RateResult> rates) {
        if (Objects.isNull(this.rates)) {
            this.rates = new ArrayList<>();
        }
        this.rates.addAll(rates);
    }

    @Override
    public String toString() {
        return summary.toString();
    }
}
