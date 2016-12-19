package com.revinate.ship.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.revinate.ship.gueststay.GuestStay;
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
public class GuestStayResult extends Result {

    // guestStay is not necessarily valid
    private GuestStay guestStay;

    @Valid
    private List<Result> errors;

    @Valid
    private List<Result> warnings;

    public void addError(Result error) {
        if (Objects.isNull(this.errors)) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }

    public void addErrors(Collection<? extends Result> errors) {
        if (Objects.isNull(this.errors)) {
            this.errors = new ArrayList<>();
        }
        this.errors.addAll(errors);
    }

    public void addWarning(Result warning) {
        if (Objects.isNull(this.warnings)) {
            this.warnings = new ArrayList<>();
        }
        this.warnings.add(warning);
    }

    public void addWarnings(Collection<? extends Result> warnings) {
        if (Objects.isNull(this.warnings)) {
            this.warnings = new ArrayList<>();
        }
        this.warnings.addAll(warnings);
    }
}
