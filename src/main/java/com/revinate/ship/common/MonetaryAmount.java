package com.revinate.ship.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonetaryAmount {

    public static final MonetaryAmount ZERO = new MonetaryAmount();

    @Valid
    private MonetaryValue amountBeforeTax;

    @Valid
    private MonetaryValue taxAmount;

    @Deprecated
    private BigDecimal value;

    @Deprecated
    private String currency;

    @Builder
    @JsonIgnore
    public MonetaryAmount(MonetaryValue amountBeforeTax, MonetaryValue taxAmount) {
        this.amountBeforeTax = amountBeforeTax;
        this.taxAmount = taxAmount;
    }

    @JsonIgnore
    public MonetaryAmount(MonetaryValue amountBeforeTax) {
        this(amountBeforeTax, null);
    }

    public MonetaryAmount add(MonetaryAmount augend) {
        MonetaryValue amountBeforeTaxSum = Objects.isNull(this.amountBeforeTax) ?
                augend.amountBeforeTax :
                (Objects.isNull(augend.amountBeforeTax) ?
                        this.amountBeforeTax :
                        this.amountBeforeTax.add(augend.amountBeforeTax));

        MonetaryValue taxAmountSum = Objects.isNull(this.taxAmount) ?
                augend.taxAmount :
                (Objects.isNull(augend.taxAmount) ?
                        this.taxAmount :
                        this.taxAmount.add(augend.taxAmount));

        return new MonetaryAmount(amountBeforeTaxSum, taxAmountSum);
    }

    public MonetaryAmount multiply(BigDecimal multiplicand) {
        MonetaryValue amountBeforeTaxProduct = Objects.isNull(this.amountBeforeTax) ?
                null : this.amountBeforeTax.multiply(multiplicand);

        MonetaryValue taxAmountProduct = Objects.isNull(this.taxAmount) ?
                null : this.taxAmount.multiply(multiplicand);

        return new MonetaryAmount(amountBeforeTaxProduct, taxAmountProduct);
    }

    public MonetaryAmount multiply(String multiplicand) {
        return multiply(new BigDecimal(multiplicand));
    }
}
