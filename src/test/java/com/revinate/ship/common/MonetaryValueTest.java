package com.revinate.ship.common;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MonetaryValueTest {

    @Test
    public void addWithNoCurrency_shouldAddOperands() throws Exception {
        MonetaryValue a = new MonetaryValue("19.95");
        MonetaryValue b = new MonetaryValue("5");
        MonetaryValue sum = a.add(b);

        assertThat(sum.getValue()).isEqualTo(new BigDecimal("24.95"));
        assertThat(sum.getCurrency()).isNull();
    }

    @Test
    public void addWithLeftCurrency_shouldAddOperands() throws Exception {
        MonetaryValue a = new MonetaryValue("19.95", "USD");
        MonetaryValue b = new MonetaryValue("5.05");
        MonetaryValue sum = a.add(b);

        assertThat(sum.getValue()).isEqualTo(new BigDecimal("25.00"));
        assertThat(sum.getCurrency()).isEqualTo("USD");
    }

    @Test
    public void addWithRightCurrency_shouldAddOperands() throws Exception {
        MonetaryValue a = new MonetaryValue("100");
        MonetaryValue b = new MonetaryValue("300.005", "CAD");
        MonetaryValue sum = a.add(b);

        assertThat(sum.getValue()).isEqualTo(new BigDecimal("400.005"));
        assertThat(sum.getCurrency()).isEqualTo("CAD");
    }

    @Test
    public void addWithSameCurrency_shouldAddOperands() throws Exception {
        MonetaryValue a = new MonetaryValue("10", "JPY");
        MonetaryValue b = new MonetaryValue("90", "JPY");
        MonetaryValue sum = a.add(b);

        assertThat(sum.getValue()).isEqualTo(new BigDecimal("100"));
        assertThat(sum.getCurrency()).isEqualTo("JPY");
    }

    @Test
    public void addWithDifferentCurrency_shouldThrowException() throws Exception {
        MonetaryValue a = new MonetaryValue("10", "JPY");
        MonetaryValue b = new MonetaryValue("90", "EUR");

        assertThatThrownBy(() -> a.add(b)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void multiplyWithNoCurrency_shouldMultiplyOperands() throws Exception {
        MonetaryValue a = new MonetaryValue("19.95");
        MonetaryValue product = a.multiply("10");

        assertThat(product.getValue()).isEqualTo(new BigDecimal("199.50"));
        assertThat(product.getCurrency()).isNull();
    }

    @Test
    public void multiplyWithCurrency_shouldMultiplyOperands() throws Exception {
        MonetaryValue a = new MonetaryValue("200.2", "USD");
        MonetaryValue product = a.multiply("2");

        assertThat(product.getValue()).isEqualTo(new BigDecimal("400.4"));
        assertThat(product.getCurrency()).isEqualTo("USD");
    }
}
