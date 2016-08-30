package com.revinate.ship.simplegueststay;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.revinate.ship.TestUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.revinate.assertj.json.JsonPathAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleGuestStaySerializationTest {

    public static final SimpleGuestStay SIMPLE_GUEST_STAY = SimpleGuestStay
            .builder()
            .accountName("Avertine")
            .interfaceType("SHIP")
            .confirmationNumber("10003")
            .checkInDate(LocalDate.of(2015, 6, 28))
            .checkedInBy("Test User 1")
            .checkOutDate(LocalDate.of(2015, 7, 1))
            .checkedOutBy("Test User 2")
            .roomNumber("C124A")
            .roomType("KING")
            .channel("SALE")
            .ratePlanCode("BAR")
            .rate(BigDecimal.valueOf(99.95))
            .title("Dr")
            .firstName("Mary")
            .lastName("Claire")
            .email("mary@mailinator.com")
            .phone("555-123-4567")
            .addressLine1("1234 Jones St")
            .addressLine2("Apt 4C")
            .city("San Francisco")
            .state("CA")
            .country("US")
            .postalCode("94108")
            .loyaltyNumber("12345")
            .groupName("Nov Conference")
            .build();

    @Test
    public void serialize_shouldContainValues() throws Exception {
        String jsonString = TestUtils.MAPPER.writeValueAsString(SIMPLE_GUEST_STAY);
        DocumentContext context = JsonPath.parse(jsonString);

        assertThat(context).jsonPathAsString("$.accountName").isEqualTo("Avertine");
        assertThat(context).jsonPathAsString("$.interfaceType").isEqualTo("SHIP");
        assertThat(context).jsonPathAsString("$.confirmationNumber").isEqualTo("10003");
        assertThat(context).jsonPathAsString("$.checkInDate").isEqualTo("2015-06-28");
    }

    @Test
    public void serialize_shouldConformToSchema() throws Exception {
        JsonNode JsonNode = TestUtils.MAPPER.valueToTree(SIMPLE_GUEST_STAY);
        JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(
                TestUtils.classpathResourceAsUrlString("schemata/simple-guest-stay-schema.json"));
        ProcessingReport report = schema.validate(JsonNode);
        assertThat(report.isSuccess()).isTrue();
    }
}
