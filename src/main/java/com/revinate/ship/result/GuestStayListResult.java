package com.revinate.ship.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuestStayListResult extends Result {

    @Valid
    private Summary summary;

    @Valid
    private List<GuestStayResult> guestStays;

    @Valid
    @Deprecated
    private List<Result> errors;

    @Valid
    @Deprecated
    private List<Result> warnings;

    public void addGuestStay(GuestStayResult guestStay) {
        if (Objects.isNull(this.guestStays)) {
            this.guestStays = new ArrayList<>();
        }
        this.guestStays.add(guestStay);
    }

    public void addGuestStays(Collection<? extends GuestStayResult> guestStays) {
        if (Objects.isNull(this.guestStays)) {
            this.guestStays = new ArrayList<>();
        }
        this.guestStays.addAll(guestStays);
    }
}
