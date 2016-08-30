package com.revinate.ship.gueststay;

import com.revinate.ship.TestUtils;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class GuestStayDeserializationTest {

    @Test
    public void shouldDeserialize() throws Exception {
        String json = TestUtils.classpathResourceAsString("samples/guest-stays/guest-stay-full.json");

        GuestStay stay = TestUtils.MAPPER.readValue(json, GuestStay.class);

        assertThat(stay).isNotNull();
        assertThat(stay.getAction()).isEqualTo(GuestStay.Action.ADD);
        assertThat(stay.getProperty()).isEqualTo("AVERTINE");
        assertThat(stay.getInterfaceType()).isEqualTo("SHIP");
        assertThat(stay.getRemoteSystemName()).isEqualTo("avertine_ship");
        assertThat(stay.getConfirmationCode()).isEqualTo("38001");
        assertThat(stay.getStatusCode()).isEqualTo(GuestStay.StatusCode.RESERVED);

        assertThat(stay.getLastUpdatedAt()).isEqualTo("2009-08-15T16:52:36.000-07:00");
        assertThat(stay.getLastUpdatedBy()).isEqualTo("System Agent");

        assertThat(stay.getBookingDate()).isEqualTo("2009-08-15T16:52:36.000-07:00");
        assertThat(stay.getBookedBy()).isEqualTo("Sample User");

        assertThat(stay.getCancellationNumber()).isEqualTo("12345");
        assertThat(stay.getCancellationDate()).isEqualTo("2009-08-15T16:52:37.000-07:00");
        assertThat(stay.getCanceledBy()).isEqualTo("Sample User 2");

        assertThat(stay.getCheckinDate()).isEqualTo("2007-07-19");
        assertThat(stay.getActualCheckinDate()).isEqualTo("2007-07-19T15:23:00.000-07:00");
        assertThat(stay.getCheckedInBy()).isEqualTo("Ms. Green");

        assertThat(stay.getCheckoutDate()).isEqualTo("2007-07-22");
        assertThat(stay.getActualCheckoutDate()).isEqualTo("2007-07-22T08:10:40.000-07:00");
        assertThat(stay.getCheckedOutBy()).isEqualTo("AJP");

        assertThat(stay.getPurposeOfStay()).isEqualTo("BUSINESS");

        assertThat(stay.getTotalRoomRevenue())
                .isNotNull()
                .satisfies(roomRevenue -> {
                    assertThat(roomRevenue.getAmountBeforeTax())
                            .isNotNull()
                            .satisfies(amountBeforeTax -> {
                                assertThat(amountBeforeTax.getValue()).isEqualTo(BigDecimal.valueOf(404.5));
                                assertThat(amountBeforeTax.getCurrency()).isEqualTo("USD");
                            });
                    assertThat(roomRevenue.getTaxAmount())
                            .isNotNull()
                            .satisfies(taxAmount -> {
                                assertThat(taxAmount.getValue()).isEqualTo(BigDecimal.valueOf(35.39));
                                assertThat(taxAmount.getCurrency()).isEqualTo("USD");
                            });
                });

        assertThat(stay.getDepositRequiredDate()).isEqualTo("2009-08-15");

        assertThat(stay.getRatePlans())
                .hasSize(1)
                .first()
                .satisfies(plan -> {
                    assertThat(plan).isNotNull();
                    assertThat(plan.getRateCode()).isEqualTo("SECRET_RATE");
                    assertThat(plan.getRates())
                            .hasSize(4)
                            .first()
                            .satisfies(rate -> {
                                assertThat(rate.getAmount().getValue()).isEqualTo(BigDecimal.valueOf(100L));
                                assertThat(rate.getAmount().getCurrency()).isEqualTo("USD");
                                assertThat(rate.getStartTime()).isEqualTo("2007-07-19T00:00:00.000-07:00");
                            });
                });

        assertThat(stay.getServices())
                .hasSize(1)
                .first()
                .satisfies(service -> {
                    assertThat(service).isNotNull();
                    assertThat(service.getInventoryCode()).isEqualTo("SPA");
                    assertThat(service.getRateCode()).isEqualTo("STD_SPA");
                    assertThat(service.getPricePerUnit())
                            .isNotNull()
                            .satisfies(price -> assertThat(price.getAmountBeforeTax())
                                    .isNotNull()
                                    .satisfies(amountBeforeTax -> {
                                        assertThat(amountBeforeTax.getValue()).isEqualTo(BigDecimal.valueOf(25.9));
                                        assertThat(amountBeforeTax.getCurrency()).isEqualTo("USD");
                                    }));
                });

        assertThat(stay.getProfiles())
                .hasSize(2)
                .first()
                .satisfies(profile -> {
                    assertThat(profile).isNotNull();
                    assertThat(profile.getProfileId()).isEqualTo("28002");
                    assertThat(profile.getDateOfBirth()).isEqualTo("1975-07-18");
                });

        assertThat(stay.getGuestNotes())
                .hasSize(3)
                .first()
                .hasFieldOrPropertyWithValue("title", "Background Note")
                .hasFieldOrPropertyWithValue("text", "Global Background Note")
                .hasFieldOrPropertyWithValue("type", "Background Notes")
                .satisfies(note -> assertThat(note.getTime()).isEqualTo("2014-01-16T11:30:38.000-08:00"));

        assertThat(stay.getPmsDefinedFields())
                .hasSize(2)
                .first()
                .satisfies(userDefinedField -> {
                    assertThat(userDefinedField.getName()).isEqualTo("UDF01");
                    assertThat(userDefinedField.getValue()).isEqualTo("A");
                });

        assertThat(stay.getPropertyDefinedFields())
                .hasSize(2)
                .first()
                .satisfies(userDefinedField -> {
                    assertThat(userDefinedField.getName()).isEqualTo("PUDF01");
                    assertThat(userDefinedField.getValue()).isEqualTo("A1");
                });
    }
}
