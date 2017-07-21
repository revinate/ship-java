package com.revinate.ship.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.revinate.ship.common.CompanyInfo;
import com.revinate.ship.common.GuestNote;
import com.revinate.ship.common.UserDefinedField;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Profile {

    public enum Action {
        ADD, DELETE
    }

    public enum ProfileType {
        NA, GUEST, CORPORATE, TRAVEL, WHOLESALER, GROUP, TOUR, CRO, CONTACT,
        AIRLINE, REPCOMPANY, INTERNET
    }

    public enum Gender {
        MALE, FEMALE, UNKNOWN
    }

    @JsonProperty(required = true)
    @NotNull
    private Action action;

    @JsonProperty(required = true)
    @NotNull
    private String property;

    private String interfaceType;

    private String remoteSystemName;

    private String accountId;

    private String profileId;

    @JsonProperty(required = true)
    @NotNull
    private ProfileType profileType;

    private String title;

    private String firstName;

    private String middleName;

    @JsonProperty(required = true)
    @NotNull
    private String lastName;

    private Gender gender;

    private LocalDate dateOfBirth;

    private String vipStatus;

    private String primaryLanguage;

    @Valid
    private CompanyInfo companyInfo;

    private Boolean emailOptOut;

    private Boolean mailOptOut;

    @Valid
    private List<EmailAddress> emailAddresses;

    @Valid
    private List<PostalAddress> postalAddresses;

    @Valid
    private List<PhoneNumber> phoneNumbers;

    @Valid
    private List<Membership> memberships;

    @Valid
    private List<CreditCard> creditCards;

    @Valid
    private List<GuestNote> guestNotes;


    @Valid
    private List<UserDefinedField> pmsDefinedFields;

    @Valid
    private List<UserDefinedField> propertyDefinedFields;

    public void addEmailAddress(EmailAddress emailAddress) {
        if (Objects.isNull(this.emailAddresses)) {
            this.emailAddresses = new ArrayList<>();
        }
        this.emailAddresses.add(emailAddress);
    }

    public void addPostalAddress(PostalAddress postalAddress) {
        if (Objects.isNull(this.postalAddresses)) {
            this.postalAddresses = new ArrayList<>();
        }
        this.postalAddresses.add(postalAddress);
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        if (Objects.isNull(this.phoneNumbers)) {
            this.phoneNumbers = new ArrayList<>();
        }
        this.phoneNumbers.add(phoneNumber);
    }

    public void addMembership(Membership membership) {
        if (Objects.isNull(this.memberships)) {
            this.memberships = new ArrayList<>();
        }
        this.memberships.add(membership);
    }

    public void addCreditCard(CreditCard creditCard) {
        if (Objects.isNull(this.creditCards)) {
            this.creditCards = new ArrayList<>();
        }
        this.creditCards.add(creditCard);
    }

    public void addGuestNote(GuestNote guestNote) {
        if (Objects.isNull(this.guestNotes)) {
            this.guestNotes = new ArrayList<>();
        }
        this.guestNotes.add(guestNote);
    }

    public void addPmsDefinedField(UserDefinedField userDefinedField) {
        if (Objects.isNull(this.pmsDefinedFields)) {
            this.pmsDefinedFields = new ArrayList<>();
        }
        this.pmsDefinedFields.add(userDefinedField);
    }

    public void addPropertyDefinedField(UserDefinedField userDefinedField) {
        if (Objects.isNull(this.propertyDefinedFields)) {
            this.propertyDefinedFields = new ArrayList<>();
        }
        this.propertyDefinedFields.add(userDefinedField);
    }
}
