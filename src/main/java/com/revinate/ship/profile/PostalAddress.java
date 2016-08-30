package com.revinate.ship.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
public class PostalAddress {

    public enum AddressType {
        HOME, BUSINESS, MAILING, SHIPPING, BILLING, OTHER
    }

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    @JsonProperty(required = true)
    private AddressType addressType;
    @JsonProperty(required = true)
    private Boolean primary;
    private OffsetDateTime inactiveDate;
}
