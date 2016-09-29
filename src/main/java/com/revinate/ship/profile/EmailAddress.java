package com.revinate.ship.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailAddress {

    @JsonProperty(required = true)
    private String emailAddress;
    @JsonProperty(required = true)
    private Boolean primary;
    private OffsetDateTime inactiveDate;

    @JsonIgnore
    public EmailAddress(String emailAddress, Boolean primary) {
        this.emailAddress = emailAddress;
        this.primary = primary;
    }
}
