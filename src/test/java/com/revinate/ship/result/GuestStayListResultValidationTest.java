package com.revinate.ship.result;

import com.revinate.ship.gueststay.GuestStay;
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

public class GuestStayListResultValidationTest {

    private static Validator validator;

    private GuestStayListResult guestStayListResult;

    @BeforeClass
    public static void staticSetUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Before
    public void setUp() throws Exception {
        guestStayListResult = new GuestStayListResult();
        guestStayListResult.setStatus(Result.Status.SUCCESS);
        guestStayListResult.setMessage("This was a triumph");
        guestStayListResult.setSourceField("table");
        guestStayListResult.setSourceValue("the entire table");
        guestStayListResult.setField("guestStayList#");

        GuestStayResult guestStayResult = new GuestStayResult();
        guestStayResult.setStatus(Result.Status.SUCCESS);
        guestStayResult.setMessage("This was a triumph");
        guestStayResult.setSourceField("row 0");
        guestStayResult.setSourceValue("the entire row");
        guestStayResult.setField("guestStay#");
        guestStayResult.setGuestStay(GuestStay
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
                .build());
        guestStayResult.addError(Result
                .builder()
                .status(Result.Status.FAILED)
                .message("This was a failure")
                .sourceField("cell 0")
                .sourceValue("The entire cell")
                .field("guestStay#/action")
                .build());
        guestStayResult.addWarning(Result
                .builder()
                .status(Result.Status.FAILED)
                .message("This was a failure")
                .sourceField("cell 1")
                .sourceValue("The entire cell")
                .field("guestStay#/statusCode")
                .build());
        guestStayListResult.addGuestStay(guestStayResult);
    }

    @Test
    public void validateValidGuestStayListResult_shouldPass() throws Exception {
        Set<ConstraintViolation<GuestStayListResult>> violations =
                validator.validate(guestStayListResult);

        assertThat(violations).isEmpty();
    }
}
