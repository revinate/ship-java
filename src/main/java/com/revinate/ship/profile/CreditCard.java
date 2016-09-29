package com.revinate.ship.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCard {

    private String creditCardLast4;

    private LocalDate creditCardExpirationDate;

    private String creditCardType;

    @JsonProperty(required = true)
    @NotNull
    private Boolean primary;

    @JsonIgnore
    public CreditCard(String creditCardLast4, Boolean primary) {
        this.creditCardLast4 = creditCardLast4;
        this.primary = primary;
    }
}
