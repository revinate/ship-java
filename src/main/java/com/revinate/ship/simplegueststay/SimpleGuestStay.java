package com.revinate.ship.simplegueststay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleGuestStay {

    @JsonProperty(required = true)
    @NotNull
    private String accountName;

    private String interfaceType;

    @JsonProperty(required = true)
    @NotNull
    private String confirmationNumber;

    @JsonProperty(required = true)
    @NotNull
    private LocalDate checkInDate;

    private String checkedInBy;

    @JsonProperty(required = true)
    @NotNull
    private LocalDate checkOutDate;

    private String checkedOutBy;

    private String roomNumber;

    private String roomType;

    private String channel;

    private String ratePlanCode;

    private BigDecimal rate;

    private String title;

    @JsonProperty(required = true)
    @NotNull
    private String firstName;

    @JsonProperty(required = true)
    @NotNull
    private String lastName;

    @JsonProperty(required = true)
    @NotNull
    @Email
    private String email;

    private String phone;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String loyaltyNumber;

    private String groupName;
}
