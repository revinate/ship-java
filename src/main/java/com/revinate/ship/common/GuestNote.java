package com.revinate.ship.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
public class GuestNote {

    private String title;
    @JsonProperty(required = true)
    private String text;
    private String type;
    private OffsetDateTime time;
}
