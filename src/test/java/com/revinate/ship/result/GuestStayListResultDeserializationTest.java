package com.revinate.ship.result;

import com.revinate.ship.TestUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GuestStayListResultDeserializationTest {

    @Test
    public void shouldDeserialize() throws Exception {
        String json = TestUtils.classpathResourceAsString("samples/guest-stay-list-results/guest-stay-list-result-full.json");

        GuestStayListResult result = TestUtils.MAPPER.readValue(json, GuestStayListResult.class);

        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(Result.Status.WARNING);
        assertThat(result.getMessage()).isEqualTo("Some guest-stay transformations failed");
        assertThat(result.getSourceField()).isEqualTo("table");
        assertThat(result.getField()).isEqualTo("guestStayList#");
        assertThat(result.getSummary())
                .isNotNull()
                .satisfies(summary -> {
                    assertThat(summary.getSuccessCount()).isEqualTo(1L);
                    assertThat(summary.getErrorCount()).isEqualTo(1L);
                    assertThat(summary.getTotalCount()).isEqualTo(2L);
                });
    }
}
