package com.revinate.ship.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Membership {

    @JsonProperty(required = true)
    private String loyaltyNumber;
    private String programCode;
    private String levelCode;
    private LocalDate expireDate;
}
