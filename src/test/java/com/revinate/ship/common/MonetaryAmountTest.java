package com.revinate.ship.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MonetaryAmountTest {

    @Test
    public void addWithNoValue_shouldAddOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount();
        MonetaryAmount b = new MonetaryAmount();
        MonetaryAmount sum = a.add(b);

        assertThat(sum.getAmountBeforeTax()).isNull();
        assertThat(sum.getTaxAmount()).isNull();
    }

    @Test
    public void addWithLeftPretax_shouldAddOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount(new MonetaryValue("19.95", "USD"));
        MonetaryAmount b = new MonetaryAmount();
        MonetaryAmount sum = a.add(b);

        assertThat(sum.getAmountBeforeTax()).isEqualTo(new MonetaryValue("19.95", "USD"));
        assertThat(sum.getTaxAmount()).isNull();
    }

    @Test
    public void addWithRightPretax_shouldAddOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount();
        MonetaryAmount b = new MonetaryAmount(new MonetaryValue("20", "USD"));
        MonetaryAmount sum = a.add(b);

        assertThat(sum.getAmountBeforeTax()).isEqualTo(new MonetaryValue("20", "USD"));
        assertThat(sum.getTaxAmount()).isNull();
    }

    @Test
    public void addWithBothPretaxSameCurrency_shouldAddOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount(new MonetaryValue("10.5", "USD"));
        MonetaryAmount b = new MonetaryAmount(new MonetaryValue("5.2", "USD"));
        MonetaryAmount sum = a.add(b);

        assertThat(sum.getAmountBeforeTax()).isEqualTo(new MonetaryValue("15.7", "USD"));
        assertThat(sum.getTaxAmount()).isNull();
    }

    @Test
    public void addWithBothPretaxDifferentCurrency_shouldThrowException() throws Exception {
        MonetaryAmount a = new MonetaryAmount(new MonetaryValue("10.5", "USD"));
        MonetaryAmount b = new MonetaryAmount(new MonetaryValue("5.2", "GBP"));

        assertThatThrownBy(() -> a.add(b)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void addWithLeftTax_shouldAddOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount(null, new MonetaryValue("19.95", "USD"));
        MonetaryAmount b = new MonetaryAmount();
        MonetaryAmount sum = a.add(b);

        assertThat(sum.getAmountBeforeTax()).isNull();
        assertThat(sum.getTaxAmount()).isEqualTo(new MonetaryValue("19.95", "USD"));
    }

    @Test
    public void addWithRightTax_shouldAddOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount();
        MonetaryAmount b = new MonetaryAmount(null, new MonetaryValue("19.95", "USD"));
        MonetaryAmount sum = a.add(b);

        assertThat(sum.getAmountBeforeTax()).isNull();
        assertThat(sum.getTaxAmount()).isEqualTo(new MonetaryValue("19.95", "USD"));
    }

    @Test
    public void addWithBothTax_shouldAddOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount(null, new MonetaryValue("19.95", "USD"));
        MonetaryAmount b = new MonetaryAmount(null, new MonetaryValue("19.95", "USD"));
        MonetaryAmount sum = a.add(b);

        assertThat(sum.getAmountBeforeTax()).isNull();
        assertThat(sum.getTaxAmount()).isEqualTo(new MonetaryValue("39.90", "USD"));
    }

    @Test
    public void addWithLeftBoth_shouldAddOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount(new MonetaryValue("19.95", "USD"),
                new MonetaryValue("4", "USD"));
        MonetaryAmount b = new MonetaryAmount();
        MonetaryAmount sum = a.add(b);

        assertThat(sum.getAmountBeforeTax()).isEqualTo(new MonetaryValue("19.95", "USD"));
        assertThat(sum.getTaxAmount()).isEqualTo(new MonetaryValue("4", "USD"));
    }

    @Test
    public void addWithRightBoth_shouldAddOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount();
        MonetaryAmount b = new MonetaryAmount(new MonetaryValue("19.95", "USD"),
                new MonetaryValue("4", "USD"));
        MonetaryAmount sum = a.add(b);

        assertThat(sum.getAmountBeforeTax()).isEqualTo(new MonetaryValue("19.95", "USD"));
        assertThat(sum.getTaxAmount()).isEqualTo(new MonetaryValue("4", "USD"));
    }

    @Test
    public void addWithLeftPretaxRightTax_shouldAddOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount(new MonetaryValue("19.95", "USD"));
        MonetaryAmount b = new MonetaryAmount(null, new MonetaryValue("4", "USD"));
        MonetaryAmount sum = a.add(b);

        assertThat(sum.getAmountBeforeTax()).isEqualTo(new MonetaryValue("19.95", "USD"));
        assertThat(sum.getTaxAmount()).isEqualTo(new MonetaryValue("4", "USD"));
    }

    @Test
    public void addWithAll_shouldAddOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount(new MonetaryValue("19.95", "USD")
                , new MonetaryValue("4", "USD"));
        MonetaryAmount b = new MonetaryAmount(new MonetaryValue("101.05", "USD")
                , new MonetaryValue("4", "USD"));
        MonetaryAmount sum = a.add(b);

        assertThat(sum.getAmountBeforeTax()).isEqualTo(new MonetaryValue("121.00", "USD"));
        assertThat(sum.getTaxAmount()).isEqualTo(new MonetaryValue("8", "USD"));
    }

    @Test
    public void multipleWithNoValue_shouldMultiplyOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount();
        MonetaryAmount product = a.multiply("2");

        assertThat(product.getAmountBeforeTax()).isNull();
        assertThat(product.getTaxAmount()).isNull();
    }

    @Test
    public void multipleWithPretax_shouldMultiplyOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount(new MonetaryValue("19.95", "USD"));
        MonetaryAmount product = a.multiply("2");

        assertThat(product.getAmountBeforeTax()).isEqualTo(new MonetaryValue("39.90", "USD"));
        assertThat(product.getTaxAmount()).isNull();
    }

    @Test
    public void multipleWithTax_shouldMultiplyOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount(null, new MonetaryValue("2.5", "USD"));
        MonetaryAmount product = a.multiply("2");

        assertThat(product.getAmountBeforeTax()).isNull();
        assertThat(product.getTaxAmount()).isEqualTo(new MonetaryValue("5.0", "USD"));
    }

    @Test
    public void multipleWithBoth_shouldMultiplyOperands() throws Exception {
        MonetaryAmount a = new MonetaryAmount(new MonetaryValue("19.95", "USD"),
                new MonetaryValue("2.5", "USD"));
        MonetaryAmount product = a.multiply("2");

        assertThat(product.getAmountBeforeTax()).isEqualTo(new MonetaryValue("39.90", "USD"));
        assertThat(product.getTaxAmount()).isEqualTo(new MonetaryValue("5.0", "USD"));
    }
}
