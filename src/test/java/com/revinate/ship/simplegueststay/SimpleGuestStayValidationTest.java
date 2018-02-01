package com.revinate.ship.simplegueststay;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleGuestStayValidationTest {

    private static Validator validator;

    private SimpleGuestStay simpleGuestStay;

    @BeforeClass
    public static void staticSetUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Before
    public void setUp() throws Exception {
        simpleGuestStay = SimpleGuestStay
                .builder()
                .accountName("Avertine")
                .confirmationNumber("10003")
                .checkInDate(LocalDate.of(2015, 6, 28))
                .checkOutDate(LocalDate.of(2015, 7, 1))
                .firstName("Mary")
                .lastName("Claire")
                .email("mary@mailinator.com")
                .build();
    }

    @Test
    public void validateValidSimpleGuestStay_shouldPass() throws Exception {
        Set<ConstraintViolation<SimpleGuestStay>> violations = validator.validate(simpleGuestStay);

        assertThat(violations).isEmpty();
    }

    @Test
    public void validateSimpleGuestStayWithBadEmail_shouldPass() throws Exception {
        simpleGuestStay.setEmail("invalid");
        Set<ConstraintViolation<SimpleGuestStay>> violations = validator.validate(simpleGuestStay);

        assertThat(violations).isEmpty();
    }

    @Test
    public void validateSimpleGuestStayWithNullField_shouldFail() throws Exception {
        simpleGuestStay.setAccountName(null);
        Set<ConstraintViolation<SimpleGuestStay>> violations = validator.validate(simpleGuestStay);

        assertThat(violations)
                .hasSize(1)
                .first()
                .satisfies(violation -> {
                    assertThat(violation.getPropertyPath()).hasToString("accountName");
                    assertThat(violation.getMessage()).isEqualTo("may not be null");
                });
    }
}
