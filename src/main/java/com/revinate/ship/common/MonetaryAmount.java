package com.revinate.ship.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonetaryAmount {

    private MonetaryValue amountBeforeTax;
    private MonetaryValue taxAmount;
    private BigDecimal value;
    private String currency;
}
