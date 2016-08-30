package com.revinate.ship.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
public class EmailAddress {

    @JsonProperty(required = true)
    private String emailAddress;
    @JsonProperty(required = true)
    private Boolean primary;
    private OffsetDateTime inactiveDate;
}
