package com.revinate.ship.gueststay;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.revinate.ship.TestUtils;
import com.revinate.ship.profile.ProfileSerializationTest;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;

import static com.revinate.assertj.json.JsonPathAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class GuestStaySerializationTest {

    public static final GuestStay GUEST_STAY = GuestStay
            .builder()
            .action(GuestStay.Action.ADD)
            .property("Avertine")
            .interfaceType("SHIP")
            .remoteSystemName("avertine_ship")
            .confirmationCode("12345")
            .statusCode(GuestStay.StatusCode.RESERVED)
            .checkinDate(LocalDate.of(2016, 8, 29))
            .checkoutDate(LocalDate.of(2016, 9, 4))
            .profiles(Collections.singletonList(ProfileSerializationTest.PROFILE))
            .build();

    @Test
    public void serialize_shouldContainValues() throws Exception {
        String guestStayString = TestUtils.MAPPER.writeValueAsString(GUEST_STAY);
        DocumentContext context = JsonPath.parse(guestStayString);

        assertThat(context).jsonPathAsString("$.action").isEqualTo("ADD");
        assertThat(context).jsonPathAsString("$.property").isEqualTo("Avertine");
        assertThat(context).jsonPathAsString("$.interfaceType").isEqualTo("SHIP");
        assertThat(context).jsonPathAsString("$.remoteSystemName").isEqualTo("avertine_ship");
        assertThat(context).jsonPathAsString("$.confirmationCode").isEqualTo("12345");
        assertThat(context).jsonPathAsString("$.statusCode").isEqualTo("RESERVED");
    }

    @Test
    public void serialize_shouldConformToSchema() throws Exception {
        JsonNode JsonNode = TestUtils.MAPPER.valueToTree(GUEST_STAY);
        JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(
                TestUtils.classpathResourceAsUrlString("schemata/guest-stay-schema.json"));
        ProcessingReport report = schema.validate(JsonNode);
        assertThat(report.isSuccess()).isTrue();
    }
}
