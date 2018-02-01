package com.revinate.ship.gueststay;

import com.revinate.ship.profile.EmailAddress;
import com.revinate.ship.profile.Profile;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class GuestStayValidationTest {

    private static Validator validator;

    private GuestStay guestStay;

    @BeforeClass
    public static void staticSetUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Before
    public void setUp() throws Exception {
        guestStay = GuestStay
                .builder()
                .action(GuestStay.Action.BOOK)
                .property("Avertine")
                .confirmationCode("12345")
                .statusCode(GuestStay.StatusCode.RESERVED)
                .checkinDate(LocalDate.of(2016, 8, 29))
                .checkoutDate(LocalDate.of(2016, 9, 4))
                .profiles(Collections.singletonList(Profile
                        .builder()
                        .action(Profile.Action.ADD)
                        .property("Avertine")
                        .profileType(Profile.ProfileType.GUEST)
                        .lastName("Person")
                        .build()))
                .build();
    }

    @Test
    public void validateValidGuestStay_shouldPass() throws Exception {
        Set<ConstraintViolation<GuestStay>> violations = validator.validate(guestStay);

        assertThat(violations).isEmpty();
    }

    @Test
    public void validateGuestStayWithBadEmail_shouldPass() throws Exception {
        guestStay.getProfiles().get(0).addEmailAddress(new EmailAddress("invalid", true));
        Set<ConstraintViolation<GuestStay>> violations = validator.validate(guestStay);

        assertThat(violations).isEmpty();
    }

    @Test
    public void validateGuestStayWithNullField_shouldFail() throws Exception {
        guestStay.setAction(null);
        Set<ConstraintViolation<GuestStay>> violations = validator.validate(guestStay);

        assertThat(violations)
                .hasSize(1)
                .first()
                .satisfies(violation -> {
                    assertThat(violation.getPropertyPath()).hasToString("action");
                    assertThat(violation.getMessage()).isEqualTo("may not be null");
                });
    }

    @Test
    public void validateGuestStayWithNestedNullField_shouldFail() throws Exception {
        guestStay.addRatePlan(new RatePlan());
        Set<ConstraintViolation<GuestStay>> violations = validator.validate(guestStay);

        assertThat(violations)
                .hasSize(1)
                .first()
                .satisfies(violation -> {
                    assertThat(violation.getPropertyPath()).hasToString("ratePlans[0].rateCode");
                    assertThat(violation.getMessage()).isEqualTo("may not be null");
                });
    }

    @Test
    public void validateGuestStayWithNoProfiles_shouldFail() throws Exception {
        guestStay.setProfiles(Collections.emptyList());
        Set<ConstraintViolation<GuestStay>> violations = validator.validate(guestStay);

        assertThat(violations)
                .hasSize(1)
                .first()
                .satisfies(violation -> {
                    assertThat(violation.getPropertyPath()).hasToString("profiles");
                    assertThat(violation.getMessage()).startsWith("size must be between 1 and");
                });
    }
}
