package com.revinate.ship.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CreditCard {

    private String creditCardLast4;
    private LocalDate creditCardExpirationDate;
    private String creditCardType;
    @JsonProperty(required = true)
    private Boolean primary;
}
