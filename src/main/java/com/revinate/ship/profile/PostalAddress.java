package com.revinate.ship.profile;

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
    @NotNull
    private AddressType addressType;

    @JsonProperty(required = true)
    @NotNull
    private Boolean primary;

    private OffsetDateTime inactiveDate;
}
