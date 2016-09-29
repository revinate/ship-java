package com.revinate.ship.profile;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.revinate.ship.TestUtils;
import com.revinate.ship.common.CompanyInfo;
import org.junit.Test;

import java.time.LocalDate;

import static com.revinate.assertj.json.JsonPathAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class ProfileSerializationTest {

    public static final Profile PROFILE = Profile
            .builder()
            .action(Profile.Action.ADD)
            .property("Avertine")
            .interfaceType("SHIP")
            .remoteSystemName("avertine_ship")
            .profileId("12345")
            .profileType(Profile.ProfileType.GUEST)
            .title("Dr")
            .firstName("Test")
            .middleName("J")
            .lastName("Person")
            .gender(Profile.Gender.UNKNOWN)
            .dateOfBirth(LocalDate.of(2000, 10, 31))
            .vipStatus("GOLD")
            .primaryLanguage("EN")
            .companyInfo(new CompanyInfo("Avertine"))
            .emailOptOut(false)
            .mailOptOut(true)
            .build();

    @Test
    public void serialize_shouldContainValues() throws Exception {
        String jsonString = TestUtils.MAPPER.writeValueAsString(PROFILE);
        DocumentContext context = JsonPath.parse(jsonString);

        assertThat(context).jsonPathAsString("$.action").isEqualTo("ADD");
        assertThat(context).jsonPathAsString("$.property").isEqualTo("Avertine");
        assertThat(context).jsonPathAsString("$.interfaceType").isEqualTo("SHIP");
        assertThat(context).jsonPathAsString("$.remoteSystemName").isEqualTo("avertine_ship");
        assertThat(context).jsonPathAsString("$.profileId").isEqualTo("12345");
        assertThat(context).jsonPathAsString("$.profileType").isEqualTo("GUEST");
        assertThat(context).jsonPathAsString("$.title").isEqualTo("Dr");
        assertThat(context).jsonPathAsString("$.firstName").isEqualTo("Test");
        assertThat(context).jsonPathAsString("$.middleName").isEqualTo("J");
        assertThat(context).jsonPathAsString("$.lastName").isEqualTo("Person");
        assertThat(context).jsonPathAsString("$.gender").isEqualTo("UNKNOWN");
        assertThat(context).jsonPathAsString("$.dateOfBirth").isEqualTo("2000-10-31");
        assertThat(context).jsonPathAsString("$.vipStatus").isEqualTo("GOLD");
        assertThat(context).jsonPathAsString("$.primaryLanguage").isEqualTo("EN");
        assertThat(context).jsonPathAsString("$.companyInfo.name").isEqualTo("Avertine");
        assertThat(context).jsonPathAsString("$.emailOptOut").isEqualTo("false");
        assertThat(context).jsonPathAsString("$.mailOptOut").isEqualTo("true");
    }

    @Test
    public void serialize_shouldConformToSchema() throws Exception {
        JsonNode JsonNode = TestUtils.MAPPER.valueToTree(PROFILE);
        JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(
                TestUtils.classpathResourceAsUrlString("schemata/profile-schema.json"));
        ProcessingReport report = schema.validate(JsonNode);
        assertThat(report.isSuccess()).isTrue();
    }
}
