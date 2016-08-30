package com.revinate.ship.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
public class PhoneNumber {

    public enum PhoneNumberType {
        HOME, BUSINESS, MOBILE, HOMEFAX, BUSINESSFAX, PAGER, TELEX, TTY, OTHER
    }

    @JsonProperty(required = true)
    private String phoneNumber;
    @JsonProperty(required = true)
    private PhoneNumberType phoneNumberType;
    @JsonProperty(required = true)
    private Boolean primary;
    private OffsetDateTime inactiveDate;
}
