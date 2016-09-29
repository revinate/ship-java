package com.revinate.ship.gueststay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.revinate.ship.common.*;
import com.revinate.ship.profile.Profile;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
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
public class GuestStay {

    public enum Action {
        BOOK, ADD, WAITLIST, CONFIRM, DENY, CANCEL, CHECKIN, NOSHOW, CHECKOUT, EDIT, NA
    }

    public enum StatusCode {
        REQUESTED, RESERVED, WAITLISTED, REQUESTDENIED, INHOUSE, CANCELED, NOSHOW, CHECKEDOUT
    }

    @JsonProperty(required = true)
    private Action action;
    @JsonProperty(required = true)
    private String property;
    private String interfaceType;
    private String remoteSystemName;
    @JsonProperty(required = true)
    private String confirmationCode;
    @JsonProperty(required = true)
    private StatusCode statusCode;
    private String guaranteeCode;
    private OffsetDateTime lastUpdatedAt;
    private String lastUpdatedBy;
    private String bookingNumber;
    private OffsetDateTime bookingDate;
    private String bookedBy;
    private String reservationSource;
    private String cancellationNumber;
    private OffsetDateTime cancellationDate;
    private String canceledBy;
    @JsonProperty(required = true)
    private LocalDate checkinDate;
    private OffsetDateTime actualCheckinDate;
    private String checkedInBy;
    @JsonProperty(required = true)
    private LocalDate checkoutDate;
    private OffsetDateTime actualCheckoutDate;
    private String checkedOutBy;
    private StayLength stayLength;
    private Integer numberOfAdults;
    private Integer numberOfChildren;
    private String roomNumber;
    private String roomType;
    private String roomTypeChargeCode;
    private String blockCode;
    private Integer numberOfRooms;
    private String market;
    private String purposeOfStay;
    @Deprecated
    private CompanyInfo travelAgency;
    private MonetaryAmount totalRoomRevenue;
    private MonetaryAmount totalFoodAndBeverageRevenue;
    private MonetaryAmount totalLuggageRevenue;
    private MonetaryAmount totalOtherRevenue;
    @Deprecated
    private MonetaryValue totalTaxes;
    private MonetaryValue totalRemainingBalance;
    private MonetaryValue totalDepositRequired;
    private LocalDate depositRequiredDate;
    private List<RatePlan> ratePlans;
    private List<Service> services;
    @JsonProperty(required = true)
    private List<Profile> profiles;
    private List<GuestNote> guestNotes;
    private List<UserDefinedField> pmsDefinedFields;
    private List<UserDefinedField> propertyDefinedFields;

    public void addRatePlan(RatePlan ratePlan) {
        if (Objects.isNull(this.ratePlans)) {
            this.ratePlans = new ArrayList<>();
        }
        this.ratePlans.add(ratePlan);
    }

    public void addService(Service service) {
        if (Objects.isNull(this.services)) {
            this.services = new ArrayList<>();
        }
        this.services.add(service);
    }

    public void addProfile(Profile profile) {
        if (Objects.isNull(this.profiles)) {
            this.profiles = new ArrayList<>();
        }
        this.profiles.add(profile);
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
