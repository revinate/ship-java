package com.revinate.ship.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.revinate.ship.common.CompanyInfo;
import com.revinate.ship.common.GuestNote;
import lombok.*;

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
    private Action action;
    @JsonProperty(required = true)
    private String property;
    private String interfaceType;
    private String remoteSystemName;
    private String profileId;
    @JsonProperty(required = true)
    private ProfileType profileType;
    private String title;
    private String firstName;
    private String middleName;
    @JsonProperty(required = true)
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String vipStatus;
    private String primaryLanguage;
    private CompanyInfo companyInfo;
    private Boolean emailOptOut;
    private Boolean mailOptOut;
    private List<EmailAddress> emailAddresses;
    private List<PostalAddress> postalAddresses;
    private List<PhoneNumber> phoneNumbers;
    private List<Membership> memberships;
    private List<CreditCard> creditCards;
    private List<GuestNote> guestNotes;

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
}
