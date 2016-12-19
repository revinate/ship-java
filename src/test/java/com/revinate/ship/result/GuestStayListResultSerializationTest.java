package com.revinate.ship.result;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.revinate.ship.TestUtils;
import com.revinate.ship.gueststay.GuestStaySerializationTest;
import org.junit.Test;

import static com.revinate.assertj.json.JsonPathAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class GuestStayListResultSerializationTest {

    public static final GuestStayListResult GUEST_STAY_LIST_RESULT;
    static {
        GuestStayListResult guestStayListResult = new GuestStayListResult();
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
        guestStayResult.setGuestStay(GuestStaySerializationTest.GUEST_STAY);
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

        GUEST_STAY_LIST_RESULT = guestStayListResult;
    }

    @Test
    public void serialize_shouldContainValues() throws Exception {
        String guestStayListResultString = TestUtils.MAPPER.writeValueAsString(GUEST_STAY_LIST_RESULT);
        DocumentContext context = JsonPath.parse(guestStayListResultString);

        assertThat(context).jsonPathAsString("$.status").isEqualTo("SUCCESS");
        assertThat(context).jsonPathAsString("$.message").isEqualTo("This was a triumph");
        assertThat(context).jsonPathAsString("$.sourceField").isEqualTo("table");
        assertThat(context).jsonPathAsString("$.sourceValue").isEqualTo("the entire table");
        assertThat(context).jsonPathAsString("$.field").isEqualTo("guestStayList#");
    }

    @Test
    public void serialize_shouldConformToSchema() throws Exception {
        JsonNode JsonNode = TestUtils.MAPPER.valueToTree(GUEST_STAY_LIST_RESULT);
        JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(
                TestUtils.classpathResourceAsUrlString("schemata/guest-stay-list-result-schema.json"));
        ProcessingReport report = schema.validate(JsonNode);
        assertThat(report.isSuccess()).isTrue();
    }
}
