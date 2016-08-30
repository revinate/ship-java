package com.revinate.ship.profile;

import com.revinate.ship.TestUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileDeserializationTest {

    @Test
    public void shouldDeserialize() throws Exception {
        String json = TestUtils.classpathResourceAsString("samples/profiles/profile-full.json");

        Profile profile = TestUtils.MAPPER.readValue(json, Profile.class);

        assertThat(profile).isNotNull();
        assertThat(profile.getAction()).isEqualTo(Profile.Action.ADD);
        assertThat(profile.getProperty()).isEqualTo("AVERTINE");
        assertThat(profile.getInterfaceType()).isEqualTo("SHIP");
        assertThat(profile.getRemoteSystemName()).isEqualTo("avertine_ship");
        assertThat(profile.getProfileId()).isEqualTo("28001");
        assertThat(profile.getProfileType()).isEqualTo(Profile.ProfileType.GUEST);

        assertThat(profile.getCompanyInfo())
                .isNotNull()
                .satisfies(companyInfo -> assertThat(companyInfo.getName()).isEqualTo("Avertine"));

        assertThat(profile.getEmailAddresses())
                .isNotEmpty()
                .satisfies(emails -> {
                    assertThat(emails).first()
                            .satisfies(email -> {
                                assertThat(email).isNotNull();
                                assertThat(email.getEmailAddress()).isEqualTo("info@mailinator.com");
                                assertThat(email.getPrimary()).isTrue();
                                assertThat(email.getInactiveDate()).isNull();
                            });
                    assertThat(emails).element(1)
                            .satisfies(email -> {
                                assertThat(email).isNotNull();
                                assertThat(email.getEmailAddress()).isEqualTo("inactive@mailinator.com");
                                assertThat(email.getPrimary()).isFalse();
                                assertThat(email.getInactiveDate()).isEqualTo("2007-07-19T00:00:00-07:00");
                            });
                });
    }
}
