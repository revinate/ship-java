package com.revinate.ship.profile;

import com.revinate.ship.common.CompanyInfo;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileValidationTest {

    private static Validator validator;

    private Profile profile;

    @BeforeClass
    public static void staticSetUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Before
    public void setUp() throws Exception {
        profile = Profile
                .builder()
                .action(Profile.Action.ADD)
                .property("Avertine")
                .profileType(Profile.ProfileType.GUEST)
                .lastName("Person")
                .build();
    }

    @Test
    public void validateValidProfile_shouldPass() throws Exception {
        Set<ConstraintViolation<Profile>> violations = validator.validate(profile);

        assertThat(violations).isEmpty();
    }

    @Test
    public void validateProfileWithNullField_shouldFail() throws Exception {
        profile.setAction(null);
        Set<ConstraintViolation<Profile>> violations = validator.validate(profile);

        assertThat(violations)
                .hasSize(1)
                .first()
                .satisfies(violation -> {
                    assertThat(violation.getPropertyPath()).hasToString("action");
                    assertThat(violation.getMessage()).isEqualTo("may not be null");
                });
    }

    @Test
    public void validateProfileWithNestedNullField_shouldFail() throws Exception {
        profile.setCompanyInfo(new CompanyInfo());
        Set<ConstraintViolation<Profile>> violations = validator.validate(profile);

        assertThat(violations)
                .hasSize(1)
                .first()
                .satisfies(violation -> {
                    assertThat(violation.getPropertyPath()).hasToString("companyInfo.name");
                    assertThat(violation.getMessage()).isEqualTo("may not be null");
                });
    }

    @Test
    public void validateProfileWithBadEmail_shouldFail() throws Exception {
        profile.addEmailAddress(new EmailAddress("invalid", true));
        Set<ConstraintViolation<Profile>> violations = validator.validate(profile);

        assertThat(violations)
                .hasSize(1)
                .first()
                .satisfies(violation -> {
                    assertThat(violation.getPropertyPath()).hasToString("emailAddresses[0].emailAddress");
                    assertThat(violation.getMessage()).isEqualTo("not a well-formed email address");
                });
    }
}
