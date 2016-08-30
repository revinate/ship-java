package com.revinate.ship.profile;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.revinate.ship.TestUtils;
import org.junit.Test;

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
            .firstName("Test")
            .lastName("Person")
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
        assertThat(context).jsonPathAsString("$.firstName").isEqualTo("Test");
        assertThat(context).jsonPathAsString("$.lastName").isEqualTo("Person");
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
