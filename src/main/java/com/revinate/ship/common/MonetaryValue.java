package com.revinate.ship.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonetaryValue {

    public static final MonetaryValue ZERO = new MonetaryValue(BigDecimal.ZERO);

    @JsonProperty(required = true)
    @NotNull
    private BigDecimal value;

    private String currency;

    @JsonIgnore
    public MonetaryValue(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    @JsonIgnore
    public MonetaryValue(String value, String currency) {
        this(new BigDecimal(value), currency);
    }

    @JsonIgnore
    public MonetaryValue(BigDecimal value) {
        this(value, null);
    }

    @JsonIgnore
    public MonetaryValue(String value) {
        this(new BigDecimal(value), null);
    }

    public MonetaryValue add(MonetaryValue augend) {
        String currency = this.currency;
        if (Objects.isNull(currency)) {
            currency = augend.currency;
        } else if (Objects.nonNull(augend.currency) && !currency.equals(augend.currency)) {
            throw new UnsupportedOperationException(
                    "Adding monetary values with different currencies is unsupported.");
        }

        return new MonetaryValue(this.value.add(augend.value), currency);
    }

    public MonetaryValue multiply(BigDecimal multiplicand) {
        return new MonetaryValue(this.value.multiply(multiplicand), this.currency);
    }

    public MonetaryValue multiply(String multiplicand) {
        return multiply(new BigDecimal(multiplicand));
    }
}
