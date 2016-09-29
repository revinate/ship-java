package com.revinate.ship.simplegueststay;

import com.revinate.ship.TestUtils;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleGuestStayDeserializationTest {

    @Test
    public void shouldDeserialize() throws Exception {
        String json = TestUtils.classpathResourceAsString("samples/simple-guest-stays/simple-guest-stay-full.json");

        SimpleGuestStay stay = TestUtils.MAPPER.readValue(json, SimpleGuestStay.class);

        assertThat(stay).isNotNull();
        assertThat(stay.getAccountName()).isEqualTo("AVERTINE");
        assertThat(stay.getInterfaceType()).isEqualTo("SHIP");
        assertThat(stay.getConfirmationNumber()).isEqualTo("10003");
        assertThat(stay.getCheckInDate()).isEqualTo("2015-06-28");
        assertThat(stay.getCheckedInBy()).isEqualTo("Test User 1");
        assertThat(stay.getCheckOutDate()).isEqualTo("2015-07-01");
        assertThat(stay.getCheckedOutBy()).isEqualTo("Test User 2");
        assertThat(stay.getRoomNumber()).isEqualTo("C124A");
        assertThat(stay.getRoomType()).isEqualTo("KING");
        assertThat(stay.getChannel()).isEqualTo("SALE");
        assertThat(stay.getRatePlanCode()).isEqualTo("BAR");
        assertThat(stay.getRate()).isEqualTo(new BigDecimal("99.95"));
        assertThat(stay.getTitle()).isEqualTo("Dr");
        assertThat(stay.getFirstName()).isEqualTo("Mary");
        assertThat(stay.getLastName()).isEqualTo("Claire");
        assertThat(stay.getEmail()).isEqualTo("mary@mailinator.com");
        assertThat(stay.getPhone()).isEqualTo("555-123-4567");
        assertThat(stay.getAddressLine1()).isEqualTo("1234 Jones St");
        assertThat(stay.getAddressLine2()).isEqualTo("Apt 4C");
        assertThat(stay.getCity()).isEqualTo("San Francisco");
        assertThat(stay.getState()).isEqualTo("CA");
        assertThat(stay.getCountry()).isEqualTo("US");
        assertThat(stay.getPostalCode()).isEqualTo("94108");
        assertThat(stay.getLoyaltyNumber()).isEqualTo("12345");
        assertThat(stay.getGroupName()).isEqualTo("Nov Conference");
    }
}
