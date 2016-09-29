package com.revinate.ship.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Membership {

    @JsonProperty(required = true)
    private String loyaltyNumber;
    private String programCode;
    private String levelCode;
    private LocalDate expireDate;

    @JsonIgnore
    public Membership(String loyaltyNumber) {
        this.loyaltyNumber = loyaltyNumber;
    }
}
