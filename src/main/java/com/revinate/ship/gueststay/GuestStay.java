package com.revinate.ship.gueststay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.revinate.ship.common.*;
import com.revinate.ship.profile.Profile;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    private Action action;

    @JsonProperty(required = true)
    @NotNull
    private String property;

    private String interfaceType;

    private String remoteSystemName;

    private String accountId;

    @JsonProperty(required = true)
    @NotNull
    private String confirmationCode;

    @JsonProperty(required = true)
    @NotNull
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
    @NotNull
    private LocalDate checkinDate;

    private OffsetDateTime actualCheckinDate;

    private String checkedInBy;

    @JsonProperty(required = true)
    @NotNull
    private LocalDate checkoutDate;

    private OffsetDateTime actualCheckoutDate;

    private String checkedOutBy;

    @Valid
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

    @Valid
    private MonetaryAmount totalRoomRevenue;

    @Valid
    @Deprecated
    private MonetaryAmount totalFoodAndBeverageRevenue;

    @Valid
    @Deprecated
    private MonetaryAmount totalLuggageRevenue;

    @Valid
    @Deprecated
    private MonetaryAmount totalOtherRevenue;

    @Deprecated
    private MonetaryValue totalTaxes;

    @Valid
    private MonetaryValue totalRemainingBalance;

    @Valid
    private MonetaryValue totalDepositRequired;

    private LocalDate depositRequiredDate;

    @Valid
    private List<RatePlan> ratePlans;

    @Valid
    private List<Service> services;

    @JsonProperty(required = true)
    @NotNull
    @Size(min = 1)
    @Valid
    private List<Profile> profiles;

    @Valid
    private List<GuestNote> guestNotes;

    @Valid
    private List<UserDefinedField> pmsDefinedFields;

    @Valid
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
