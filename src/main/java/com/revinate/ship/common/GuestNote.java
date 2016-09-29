package com.revinate.ship.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuestNote {

    private String title;

    @JsonProperty(required = true)
    @NotNull
    private String text;

    private String type;

    private OffsetDateTime time;

    @JsonIgnore
    public GuestNote(String text) {
        this.text = text;
    }
}
