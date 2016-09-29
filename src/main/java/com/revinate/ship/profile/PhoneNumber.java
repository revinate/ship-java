package com.revinate.ship.profile;

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
public class PhoneNumber {

    public enum PhoneNumberType {
        HOME, BUSINESS, MOBILE, HOMEFAX, BUSINESSFAX, PAGER, TELEX, TTY, OTHER
    }

    @JsonProperty(required = true)
    @NotNull
    private String phoneNumber;

    @JsonProperty(required = true)
    @NotNull
    private PhoneNumberType phoneNumberType;

    @JsonProperty(required = true)
    @NotNull
    private Boolean primary;

    private OffsetDateTime inactiveDate;

    @JsonIgnore
    public PhoneNumber(String phoneNumber, PhoneNumberType phoneNumberType, Boolean primary) {
        this.phoneNumber = phoneNumber;
        this.phoneNumberType = phoneNumberType;
        this.primary = primary;
    }
}
